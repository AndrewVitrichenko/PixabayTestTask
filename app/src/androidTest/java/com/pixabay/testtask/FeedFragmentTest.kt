package com.pixabay.testtask

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import com.pixabay.testtask.actions.SearchViewActionExtension
import com.pixabay.testtask.ui.PixabayActivity
import com.pixabay.testtask.ui.feed.FeedFragment
import com.pixabay.testtask.ui.feed.list.FeedListAdapter
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Rule
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class FeedFragmentTest {

    @get:Rule
    var mActivityRule: ActivityTestRule<PixabayActivity> =
        ActivityTestRule(PixabayActivity::class.java)

    lateinit var idlingResource : CountingIdlingResource

    @Test
    fun fetchingImagesAndPopulateListWithItemsAndListIsNotEmpty(){
        val feedFragment = mActivityRule.activity
            .supportFragmentManager.findFragmentByTag(FeedFragment.TAG) as FeedFragment

        idlingResource = feedFragment.getViewModel().idlingResource
        IdlingRegistry.getInstance().register(idlingResource)

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.searchView)).perform(SearchViewActionExtension.submitText("sun"))

        onView(withId(R.id.recyclerView)).check { view, noViewFoundException ->
            assertTrue(view is RecyclerView &&
                    view.adapter != null && view.adapter?.itemCount?:-1 > 0
            )
        }

        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition
            <FeedListAdapter.PixabayImageItemViewHolder>(0, click()))

        onView(withId(R.id.pixabayImageView)).check(matches(isDisplayed()))

    }

    @After
    fun shutdown(){
        IdlingRegistry.getInstance().unregister(idlingResource)
    }
}
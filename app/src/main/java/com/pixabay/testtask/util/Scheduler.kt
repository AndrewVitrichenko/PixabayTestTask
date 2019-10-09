package com.pixabay.testtask.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Scheduler @Inject constructor(): SchedulerProvider {
    override fun trampoline(): Scheduler  = Schedulers.trampoline()

    override fun io()  = Schedulers.io()

    override fun ui()  = AndroidSchedulers.mainThread()

    override fun computation() = Schedulers.computation()
}
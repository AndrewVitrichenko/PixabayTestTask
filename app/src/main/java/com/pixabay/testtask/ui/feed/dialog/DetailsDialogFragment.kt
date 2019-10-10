package com.pixabay.testtask.ui.feed.dialog

import androidx.fragment.app.DialogFragment
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.pixabay.testtask.R
import com.pixabay.testtask.data.entity.PixabayImage


class DetailsDialogFragment : DialogFragment(), DialogInterface.OnClickListener {

    private var iDetailsDialogFragmentCallback: IDetailsDialogFragmentCallback? = null

    private var pixabayImage: PixabayImage? = null

    companion object {
        val TAG = DetailsDialogFragment::class.java.simpleName
        const val PIXABAY_IMAGE = "pixabay_image"

        fun newInstance(pixabayImage: PixabayImage): DetailsDialogFragment{
            val args = Bundle()
            args.putParcelable(PIXABAY_IMAGE,pixabayImage)
            val detailsDialogFragment = DetailsDialogFragment()
            detailsDialogFragment.arguments = args
            return detailsDialogFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null){
            pixabayImage = arguments?.getParcelable(PIXABAY_IMAGE)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (parentFragment is IDetailsDialogFragmentCallback) {
            iDetailsDialogFragmentCallback = parentFragment as IDetailsDialogFragmentCallback
        } else {
            throw Exception("Parent fragment didn't implement IDetailsDialogFragmentCallback")
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val adb = AlertDialog.Builder(context!!)
            .setTitle(getString(R.string.question_open_details))
            .setPositiveButton(android.R.string.yes, this)
            .setNegativeButton(android.R.string.no, this)
        return adb.create()
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        when (which) {
            Dialog.BUTTON_POSITIVE -> {
                dismiss()
                iDetailsDialogFragmentCallback?.onUserApproval(pixabayImage)
            }
            Dialog.BUTTON_NEGATIVE -> {
                dismiss()
                iDetailsDialogFragmentCallback?.onUserDenial(pixabayImage)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        iDetailsDialogFragmentCallback = null
    }
}
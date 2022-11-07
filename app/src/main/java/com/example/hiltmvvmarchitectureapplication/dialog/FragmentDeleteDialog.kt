package com.example.hiltmvvmarchitectureapplication.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.hiltmvvmarchitectureapplication.R
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

class FragmentDeleteDialog : DialogFragment() {


    private var mListener: DeleteListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = context as DeleteListener
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_delete_dialog_layout, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        val buttonConf = view.findViewById<MaterialButton>(R.id.buttonConfirm)
        val buttonCancel = view.findViewById<MaterialButton>(R.id.buttonCancel)
        buttonConf.setOnClickListener { v: View? ->
            mListener!!.deleteDismissDialog(
                true
            )
        }
        buttonCancel.setOnClickListener {
            mListener!!.deleteDismissDialog(
                false
            )
        }
        return view
    }


    interface DeleteListener {
        fun deleteDismissDialog(isCallApi: Boolean)
    }


    fun setListener(chooseImageBottomSheet: DeleteListener?) {
        mListener = chooseImageBottomSheet
    }

}
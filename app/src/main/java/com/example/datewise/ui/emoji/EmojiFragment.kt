package com.example.datewise.ui.emoji

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.datewise.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vanniktech.emoji.Emoji
import com.vanniktech.emoji.EmojiView
import com.vanniktech.emoji.listeners.OnEmojiBackspaceClickListener
import com.vanniktech.emoji.listeners.OnEmojiClickListener


class EmojiFragment : BottomSheetDialogFragment(),OnEmojiClickListener,
    OnEmojiBackspaceClickListener{
    lateinit var emojiView:EmojiView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // val dialog=BottomSheetDialog(requireContext(),theme)
        val dialog=BottomSheetDialog(requireContext(),theme)
         dialog.setOnShowListener {
             val bottomSheetDialog=it as BottomSheetDialog
             val parentLayout=bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
             parentLayout?.let{
                 val behavior=BottomSheetBehavior.from(it)
                 setupFullHeight(it)
                 behavior.state=BottomSheetBehavior.STATE_EXPANDED
             }
         }
         return dialog
     }

    private fun setupFullHeight(bottomSheet:View){
        val layoutParams=bottomSheet.layoutParams
        layoutParams.height=WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams=layoutParams
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emojiView=view.findViewById(R.id.emojiView)
        emojiView.setUp(
            rootView = view.rootView,
            onEmojiClickListener = this,
            onEmojiBackspaceClickListener = this,
            editText = null,
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =inflater.inflate(R.layout.fragment_emoji,container,false)


    override fun onDestroy() {
        super.onDestroy()
        emojiView.tearDown()
    }


    override fun onEmojiBackspaceClick() {}


    override fun onEmojiClick(emoji: Emoji) {
        setFragmentResult(KEY_REQUEST_EMOJI, bundleOf(KEY_BUNDLE_EMOJI to emoji.unicode))
        findNavController().popBackStack()
    }


    companion object{
        const val KEY_REQUEST_EMOJI = "Key_Emoji"
        const val KEY_BUNDLE_EMOJI = "Key_Bundle_Emoji"
    }


}
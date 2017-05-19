package com.khalid.codecracker.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.codecracker.R
import com.khalid.codecracker.util.subscribeText
import com.khalid.codecracker.utility.notNullAndObservable
import com.khalid.codecracker.viewmodel.NoteTypeRowViewModel
import kotlinx.android.synthetic.main.note_type_row.view.*

class NoteTypeRowView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.note_type_row, this)
        orientation = LinearLayout.VERTICAL
    }

    var viewModel: NoteTypeRowViewModel by notNullAndObservable {
        vm -> vm.noteObservable.subscribeText(noteDescription)
    }


}
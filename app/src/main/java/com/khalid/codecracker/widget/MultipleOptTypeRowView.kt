package com.khalid.codecracker.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.codecracker.R

class MultipleOptTypeRowView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        val view = View.inflate(context, R.layout.multiple_type_row, this)
        orientation = LinearLayout.VERTICAL

        viewSetup(view)
    }

     fun  viewSetup(view: View?){


     }

}


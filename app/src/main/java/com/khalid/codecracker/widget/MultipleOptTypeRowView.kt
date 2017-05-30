package com.khalid.codecracker.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.codecracker.R
import com.khalid.codecracker.util.subscribeText
import com.khalid.codecracker.utility.notNullAndObservable
import com.khalid.codecracker.viewmodel.MultipleOptTypeRowViewModel
import kotlinx.android.synthetic.main.multiple_type_row.view.*
import android.graphics.Typeface




class MultipleOptTypeRowView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        val view = View.inflate(context, R.layout.multiple_type_row, this)
        orientation = LinearLayout.VERTICAL
    }

    var viewModel: MultipleOptTypeRowViewModel by notNullAndObservable { vm ->
        vm.questionObservable.subscribeText(multipleTypeQuestion)
        vm.optionsObservable.subscribe { options ->
            optionsContainer.removeAllViews()
            options.forEach { option ->
                optionsContainer.addView(addOptionRow(option))
            }
        }

    }

    private fun addOptionRow(option: String): View {

        val optionBtn = RadioButton(context)
        val font = Typeface.createFromAsset(context.assets, "fonts/Roboto-Medium.ttf")
        val params = RadioGroup.LayoutParams(context, null)
        optionBtn.setPadding(5, 5, 5, 5)
        params.setMargins(20, 10, 5, 10)
        optionBtn.setLayoutParams(params)
        optionBtn.setTypeface(font)
        optionBtn.setTextSize(12f)
        optionBtn.text = option

        return optionBtn
    }

}


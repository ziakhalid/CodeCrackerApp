package com.khalid.codecracker.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.codecracker.R
import com.khalid.codecracker.util.AnimUtils
import com.khalid.codecracker.utility.bindView
import com.khalid.codecracker.utility.notNullAndObservable
import com.khalid.codecracker.viewmodel.SingleOptTypeRowViewModel
import com.khalid.codecracker.widget.animation.ResizeHeightAnimator

class SingleOptTypeRowView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    val row: ViewGroup by bindView(R.id.root)

    val solInfoChevron: ImageView by bindView(R.id.sol_info_chevron)
    val solInfoHeader: TextView by bindView(R.id.sol_info_header_text)
    val solInfoDivider: View by bindView(R.id.sol_info_divider)
    private val solInfoContainer: RelativeLayout by bindView(R.id.sol_info_container)
    private val solInfoDescriptionText: TextView by bindView(R.id.sol_info_description_text)

    var expandedMeasurementsDone = false
    private val ANIMATION_DURATION = 250L
    private val NO_ANIMATION_DURATION = 0L

    var solInfoHeaderTextHeight = -1
    var solInfoDividerHeight = -1
    var solInfoDescriptionTextHeight = -1
    var solInfoChevronHeight = -1

    var viewModel: SingleOptTypeRowViewModel by notNullAndObservable {

/*        solInfoContainer.setOnClickListener {
            if (solInfoDescriptionText.visibility == View.GONE) {
                expandSolInformation()
            } else {
                collapseSolInformation()
            }

        }*/
    }

    init {
        View.inflate(getContext(), R.layout.single_opt_type_row, this)
        orientation = LinearLayout.VERTICAL

        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                solInfoHeaderTextHeight = solInfoHeader.height
                solInfoDividerHeight = solInfoDivider.height
                solInfoDescriptionTextHeight = solInfoDescriptionText.height
                solInfoChevronHeight = solInfoChevron.height

                solInfoDescriptionText.visibility = View.GONE
                row.viewTreeObserver.removeOnGlobalLayoutListener(this)

                expandedMeasurementsDone = true
//                collapseRow(NO_ANIMATION_DURATION)
            }
        }
        row.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)

        viewSetup()

        solInfoContainer.setOnClickListener {
            if (solInfoDescriptionText.visibility == View.GONE) {
                expandSolInformation()
            } else {
                collapseSolInformation()
            }

        }

    }

    private fun viewSetup() {
        solInfoDescriptionText.visibility = View.VISIBLE
    }

    private fun expandSolInformation() {
        if (expandedMeasurementsDone) {
            val resizeAnimator = ResizeHeightAnimator(ANIMATION_DURATION)
            val lp = solInfoChevron.layoutParams as RelativeLayout.LayoutParams

            solInfoDescriptionText.visibility = View.VISIBLE
            solInfoDescriptionText.requestFocus()
            lp.addRule(RelativeLayout.BELOW, R.id.sol_info_description_text)
            solInfoChevron.layoutParams = lp
            resizeAnimator.addViewSpec(solInfoDescriptionText, solInfoDescriptionTextHeight, 0)
            resizeAnimator.start()
            AnimUtils.rotate(solInfoChevron)
        }
    }

    private fun collapseSolInformation() {
        if (expandedMeasurementsDone) {
            val resizeAnimator = ResizeHeightAnimator(ANIMATION_DURATION)
            val lp = solInfoChevron.layoutParams as RelativeLayout.LayoutParams

            lp.addRule(RelativeLayout.BELOW, 0)
            solInfoChevron.layoutParams = lp
            resizeAnimator.addViewSpec(solInfoDescriptionText, 0)
            resizeAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(p0: Animator?) {
                    solInfoDescriptionText.visibility = View.GONE
                }
            })
            resizeAnimator.start()
            AnimUtils.reverseRotate(solInfoChevron)
        }
    }
}
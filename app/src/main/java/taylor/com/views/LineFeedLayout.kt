package taylor.com.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

/**
 * a special [ViewGroup] acts like [LinearLayout],
 * it spreads the children from left to right until there is not enough horizontal space for them,
 * then the next child will be placed at a new line
 */
class LineFeedLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    var horizontalGap: Int = 0
    var verticalGap: Int = 0

    /**
     * the height of [LineFeedLayout] depends on how much lines it has
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        var height = 0
        if (heightMode == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(heightMeasureSpec)
        } else {
            var remainWidth = width
            (0 until childCount).map { getChildAt(it) }.forEach { child ->
                val lp = child.layoutParams as MarginLayoutParams
                if (isNewLine(lp, child, remainWidth)) {
                    remainWidth = width - child.measuredWidth
                    height += (lp.topMargin + lp.bottomMargin + child.measuredHeight + verticalGap)
                } else {
                    remainWidth -= child.measuredWidth
                    if (height == 0) height =
                        (lp.topMargin + lp.bottomMargin + child.measuredHeight + verticalGap)
                }
                remainWidth -= (lp.leftMargin + lp.rightMargin + horizontalGap)
            }
        }

        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var left = 0
        var top = 0
        var lastBottom = 0
        var count = 0
        (0 until childCount).map { getChildAt(it) }.forEach { child ->
            val lp = child.layoutParams as MarginLayoutParams
            if (isNewLine(lp, child, r - l - left)) {
                left = -lp.leftMargin
                top = lastBottom
                lastBottom = 0
            }
            val childLeft = left + lp.leftMargin
            val childTop = top + lp.topMargin
            child.layout(
                childLeft,
                childTop,
                childLeft + child.measuredWidth,
                childTop + child.measuredHeight
            )
            if (lastBottom == 0) lastBottom = child.bottom + lp.bottomMargin + verticalGap
            left += child.measuredWidth + lp.leftMargin + lp.rightMargin + horizontalGap
            count++
        }
    }

    /**
     * place the [child] in a new line or not
     *
     * @param lp LayoutParams of [child]
     * @param child child view of [LineFeedLayout]
     * @param remainWidth the remain space of one line in [LineFeedLayout]
     * @param horizontalGap the horizontal gap for the children of [LineFeedLayout]
     */
    private fun isNewLine(
        lp: MarginLayoutParams,
        child: View,
        remainWidth: Int,
    ): Boolean {
        val childOccupation = lp.leftMargin + child.measuredWidth + lp.rightMargin
        return childOccupation > remainWidth
    }
}
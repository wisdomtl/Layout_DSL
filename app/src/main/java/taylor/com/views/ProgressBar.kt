package taylor.com.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * a linear progress bar
 */
class ProgressBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {
    var progressBackgroundColor: Int = Color.parseColor("#00ff00")
        set(value) {
            field = value
            backgroundPaint.color = value
        }
    var progressForegroundColor: Int = Color.parseColor("#ff00ff")
        set(value) {
            field = value
            foreGroundPaint.color = value
        }

    var colors = intArrayOf()

    var backgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = progressBackgroundColor
        style = Paint.Style.FILL
    }

    var foreGroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = progressForegroundColor
        style = Paint.Style.FILL
    }

    var foregroundRectF = RectF()
    var backgroundRectF = RectF()

    var rx: Float = 0f
    var ry: Float = 0f

    var progress: Float = 0f

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        foreGroundPaint.shader =
            if (colors.isEmpty()) null else LinearGradient(0f, 0f, width * progress, height.toFloat(), colors, null, Shader.TileMode.CLAMP)
        backgroundRectF.set(0f, 0f, width.toFloat(), height.toFloat())
        canvas?.drawRoundRect(backgroundRectF, rx, ry, backgroundPaint)
        foregroundRectF.set(0f, 0f, width * progress, height.toFloat())
        canvas?.drawRoundRect(foregroundRectF, rx, ry, foreGroundPaint)
    }
}
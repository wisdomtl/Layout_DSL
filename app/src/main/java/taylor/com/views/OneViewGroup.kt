package test.taylor.com.taylorcode.ui.one

import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import taylor.com.dsl.dp
import taylor.com.dsl.parent_id
import taylor.com.dsl.sp
import taylor.com.dsl.toLayoutId
import kotlin.math.min

@RequiresApi(Build.VERSION_CODES.M)
class OneViewGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ViewGroup(context, attrs, defStyleAttr) {

    private val drawableMap = HashMap<Int, Drawable>()
    private val drawables = mutableListOf<Drawable>()
    private var gestureDetector: GestureDetector? = null

    fun addDrawable(drawable: Drawable) {
        drawables.add(drawable)
        drawableMap[drawable.layoutId] = drawable
    }

    /**
     * find [Drawable] by id
     */
    fun <T> findDrawable(id: String): T? = drawableMap[id.toLayoutId()] as? T

    /**
     * set item click listener for [OneViewGroup] whick detect child [Drawable]'s click event
     */
    fun setOnItemClickListener(onItemClickListener: (String) -> Unit) {
        gestureDetector = GestureDetector(context, object : GestureDetector.OnGestureListener {
            override fun onShowPress(e: MotionEvent?) {
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                e?.let {
                    findDrawableUnder(e.x, e.y)?.let { onItemClickListener.invoke(it.layoutIdString) }
                }
                return true
            }

            override fun onDown(e: MotionEvent?): Boolean {
                return true
            }

            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                return false
            }

            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
                return false
            }

            override fun onLongPress(e: MotionEvent?) {
            }
        })
    }

    /**
     * find child [Drawable] according to coordinate
     */
    private fun findDrawableUnder(x: Float, y: Float): Drawable? {
        drawables.forEach {
            if (it.rect.contains(x.toInt(), y.toInt())) {
                return it
            }
        }
        return null
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        drawables.forEach { it.doMeasure(widthMeasureSpec, heightMeasureSpec) }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val parentWidth = right - left
        val parentHeight = bottom - top
        drawables.forEach {
            val left = getChildLeft(it, parentWidth)
            val top = getChildTop(it, parentHeight)
            it.doLayout(changed, left, top, left + it.layoutMeasuredWidth, top + it.layoutMeasuredHeight)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawables.forEach { it.doDraw(canvas) }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector?.onTouchEvent(event) ?: super.onTouchEvent(event)
    }

    /**
     * get the top of [drawable] relative to the [OneViewGroup]
     */
    private fun getChildTop(drawable: Drawable, parentHeight: Int): Int {
        val parentId = parent_id.toLayoutId()
        return when {
            drawable.topPercent != -1f -> (parentHeight * drawable.topPercent).toInt()
            drawable.centerVerticalOf != -1 -> {
                if (drawable.centerVerticalOf == parentId) {
                    (parentHeight - drawable.layoutMeasuredHeight) / 2
                } else {
                    (drawableMap[drawable.centerVerticalOf]?.let { it.layoutTop + (it.layoutBottom - it.layoutTop) / 2 } ?: 0) - drawable.layoutMeasuredHeight / 2
                }
            }
            drawable.topToBottomOf != -1 -> {
                val b = if (drawable.topToBottomOf == parentId) bottom else drawableMap[drawable.topToBottomOf]?.layoutBottom ?: 0
                (b + drawable.layoutTopMargin)
            }
            drawable.topToTopOf != -1 -> {
                val t = if (drawable.topToTopOf == parentId) top else drawableMap[drawable.topToTopOf]?.layoutTop ?: 0
                (t + drawable.layoutTopMargin)
            }
            drawable.bottomToTopOf != -1 -> {
                val t = if (drawable.bottomToTopOf == parentId) top else drawableMap[drawable.bottomToTopOf]?.layoutTop ?: 0
                (t - drawable.layoutBottomMargin) - drawable.layoutMeasuredHeight
            }
            drawable.bottomToBottomOf != -1 -> {
                val b = if (drawable.bottomToBottomOf == parentId) bottom else drawableMap[drawable.bottomToBottomOf]?.layoutBottom ?: 0
                (b - drawable.layoutBottomMargin) - drawable.layoutMeasuredHeight
            }
            else -> 0
        }
    }

    /**
     * get the left of [drawable] relative to the [OneViewGroup]
     */
    private fun getChildLeft(drawable: Drawable, parentWidth: Int): Int {
        val parentId = parent_id.toLayoutId()
        return when {
            drawable.leftPercent != -1f -> (parentWidth * drawable.leftPercent).toInt()
            drawable.centerHorizontalOf != -1 -> {
                if (drawable.centerHorizontalOf == parentId) {
                    (parentWidth - drawable.layoutMeasuredWidth) / 2
                } else {
                    (drawableMap[drawable.centerHorizontalOf]?.let { it.layoutLeft + (it.layoutRight - it.layoutLeft) / 2 } ?: 0) - drawable.layoutMeasuredWidth / 2
                }
            }
            drawable.startToEndOf != -1 -> {
                val r = if (drawable.startToEndOf == parentId) right else drawableMap[drawable.startToEndOf]?.layoutRight ?: 0
                (r + drawable.layoutLeftMargin)
            }
            drawable.startToStartOf != -1 -> {
                val l = if (drawable.startToStartOf == parentId) left else drawableMap[drawable.startToStartOf]?.layoutLeft ?: 0
                (l + drawable.layoutLeftMargin)
            }
            drawable.endToStartOf != -1 -> {
                val l = if (drawable.endToStartOf == parentId) left else drawableMap[drawable.endToStartOf]?.layoutLeft ?: 0
                (l - drawable.layoutRightMargin) - drawable.layoutMeasuredWidth
            }
            drawable.endToEndOf != -1 -> {
                val r = if (drawable.endToEndOf == parentId) right else drawableMap[drawable.endToEndOf]?.layoutRight ?: 0
                (r - drawable.layoutRightMargin) - drawable.layoutMeasuredWidth
            }
            else -> 0
        }
    }

    /**
     * anything could be draw in [OneViewGroup]
     */
    interface Drawable {
        /**
         * the measured dimension of [Drawable]
         */
        var layoutMeasuredWidth: Int
        var layoutMeasuredHeight: Int

        /**
         * the frame rect of [Drawable]
         */
        var layoutLeft: Int
        var layoutRight: Int
        var layoutTop: Int
        var layoutBottom: Int
        val rect: Rect
            get() = Rect(layoutLeft, layoutTop, layoutRight, layoutBottom)

        /**
         * the unique id of this [Drawable] in int
         */
        var layoutId: Int

        /**
         * the unique id of this [Drawable] in string
         */
        var layoutIdString: String

        /**
         * the relative position of this [Drawable] to another
         */
        var leftPercent: Float
        var topPercent: Float
        var startToStartOf: Int
        var startToEndOf: Int
        var endToEndOf: Int
        var endToStartOf: Int
        var topToTopOf: Int
        var topToBottomOf: Int
        var bottomToTopOf: Int
        var bottomToBottomOf: Int
        var centerHorizontalOf: Int
        var centerVerticalOf: Int

        /**
         * dimension of [Drawable]
         */
        var layoutWidth: Int
        var layoutHeight: Int

        /**
         * inner padding of [Drawable]
         */
        var layoutPaddingStart: Int
        var layoutPaddingEnd: Int
        var layoutPaddingTop: Int
        var layoutPaddingBottom: Int

        /**
         * out margin of [Drawable]
         */
        var layoutTopMargin: Int
        var layoutBottomMargin: Int
        var layoutLeftMargin: Int
        var layoutRightMargin: Int

        /**
         * the the frame rect of [Drawable] is set after this function
         */
        fun setRect(left: Int, top: Int, right: Int, bottom: Int) {
            this.layoutLeft = left
            this.layoutRight = right
            this.layoutTop = top
            this.layoutBottom = bottom
        }

        /**
         * the measured width and height of [Drawable] is set after this function
         */
        fun setDimension(width: Int, height: Int) {
            this.layoutMeasuredWidth = width
            this.layoutMeasuredHeight = height
        }

        fun doMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int)
        fun doLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int)
        fun doDraw(canvas: Canvas?)
    }
}


///**
// * one kind of drawable shows image
// */
//class Image : OneViewGroup.Drawable() {
//    override fun measure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//    }
//
//    override fun draw(canvas: Canvas?) {
//    }
//}

//<editor-fold desc="sub-class of Drawable">
/**
 * one kind of [OneViewGroup.Drawable] shows image
 * and it is a special [ImageView] implemented [OneViewGroup.Drawable]
 */
class ImageDrawable @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr), OneViewGroup.Drawable {
    override var leftPercent: Float = -1f
    override var topPercent: Float = -1f
    override var startToStartOf: Int = -1
    override var startToEndOf: Int = -1
    override var endToEndOf: Int = -1
    override var endToStartOf: Int = -1
    override var topToTopOf: Int = -1
    override var topToBottomOf: Int = -1
    override var bottomToTopOf: Int = -1
    override var bottomToBottomOf: Int = -1
    override var centerHorizontalOf: Int = -1
    override var centerVerticalOf: Int = -1
    override var layoutWidth: Int = 0
    override var layoutHeight: Int = 0

    override var layoutMeasuredWidth: Int = 0
        get() = measuredWidth
    override var layoutMeasuredHeight: Int = 0
        get() = measuredHeight
    override var layoutLeft: Int = 0
        get() = left
    override var layoutRight: Int = 0
        get() = right
    override var layoutTop: Int = 0
        get() = top
    override var layoutBottom: Int = 0
        get() = bottom
    override var layoutId: Int = 0
        get() = id
    override var layoutIdString: String = ""
    override var layoutPaddingStart: Int = 0
        get() = paddingStart
    override var layoutPaddingEnd: Int = 0
        get() = paddingEnd
    override var layoutPaddingTop: Int = 0
        get() = paddingTop
    override var layoutPaddingBottom: Int = 0
        get() = paddingBottom
    override var layoutTopMargin: Int = 0
        get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin ?: 0
    override var layoutBottomMargin: Int = 0
        get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.bottomMargin ?: 0
    override var layoutLeftMargin: Int = 0
        get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.leftMargin ?: 0
    override var layoutRightMargin: Int = 0
        get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.rightMargin ?: 0

    override fun doMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    }

    override fun doLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        layout(left, top, right, bottom)
    }

    override fun doDraw(canvas: Canvas?) {
    }
}

/**
 *  one kind of [OneViewGroup.Drawable] shows text
 */
class Text : OneViewGroup.Drawable {
    private var textPaint: TextPaint? = null
    private var staticLayout: StaticLayout? = null

    var text: CharSequence = ""
    var textSize: Float = 0f
    var textColor: Int = Color.parseColor("#ffffff")
    var spaceAdd: Float = 0f
    var spaceMulti: Float = 1.0f
    var maxLines = 1
    var maxWidth = 0
    var gravity: Layout.Alignment = Layout.Alignment.ALIGN_NORMAL
    var shapePaint: Paint? = null
    var drawableShape: Shape? = null
        set(value) {
            field = value
            shapePaint = Paint().apply {
                isAntiAlias = true
                style = Paint.Style.FILL
                color = Color.parseColor(value?.color)
            }
        }
    override var layoutMeasuredWidth: Int = 0
    override var layoutMeasuredHeight: Int = 0
    override var layoutLeft: Int = 0
    override var layoutRight: Int = 0
    override var layoutTop: Int = 0
    override var layoutBottom: Int = 0
    override var layoutId: Int = 0
    override var layoutIdString: String = ""
    override var leftPercent: Float = -1f
    override var topPercent: Float = -1f
    override var startToStartOf: Int = -1
    override var startToEndOf: Int = -1
    override var endToEndOf: Int = -1
    override var endToStartOf: Int = -1
    override var topToTopOf: Int = -1
    override var topToBottomOf: Int = -1
    override var bottomToTopOf: Int = -1
    override var bottomToBottomOf: Int = -1
    override var centerHorizontalOf: Int = -1
    override var centerVerticalOf: Int = -1
    override var layoutWidth: Int = 0
    override var layoutHeight: Int = 0
    override var layoutPaddingStart: Int = 0
    override var layoutPaddingEnd: Int = 0
    override var layoutPaddingTop: Int = 0
    override var layoutPaddingBottom: Int = 0
    override var layoutTopMargin: Int = 0
    override var layoutBottomMargin: Int = 0
    override var layoutLeftMargin: Int = 0
    override var layoutRightMargin: Int = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun doMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (textPaint == null) {
            textPaint = TextPaint().apply {
                isAntiAlias = true
                textSize = this@Text.textSize
                color = textColor
            }
        }

        val measureWidth = if (layoutWidth != 0) layoutWidth else min(textPaint!!.measureText(text.toString()).toInt(), maxWidth)
        if (staticLayout == null) {
            staticLayout = StaticLayout.Builder.obtain(text, 0, text.length, textPaint!!, measureWidth)
                .setAlignment(gravity)
                .setLineSpacing(spaceAdd, spaceMulti)
                .setIncludePad(false)
                .setMaxLines(maxLines).build()
        }

        val measureHeight = staticLayout!!.height
        setDimension(measureWidth + layoutPaddingEnd + layoutPaddingStart, measureHeight + layoutPaddingTop + layoutPaddingBottom)
    }

    override fun doLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        setRect(left, top, right, bottom)
    }

    override fun doDraw(canvas: Canvas?) {
        canvas?.save()
        canvas?.translate(layoutLeft.toFloat(), layoutTop.toFloat())
        drawBackground(canvas)
        canvas?.translate(layoutPaddingStart.toFloat(), layoutPaddingTop.toFloat())
        staticLayout?.draw(canvas)
        canvas?.restore()
    }

    private fun drawBackground(canvas: Canvas?) {
        if (drawableShape == null) return
        val _shape = drawableShape!!
        if (_shape.radius != 0f) {
            canvas?.drawRoundRect(RectF(0f, 0f, layoutMeasuredWidth.toFloat(), layoutMeasuredHeight.toFloat()), _shape.radius, _shape.radius, shapePaint!!)
        } else if (_shape.corners != null) {
            _shape.path!!.apply {
                addRoundRect(
                    RectF(0f, 0f, layoutMeasuredWidth.toFloat(), layoutMeasuredHeight.toFloat()),
                    _shape.corners!!.radii,
                    Path.Direction.CCW
                )
            }
            canvas?.drawPath(_shape.path!!, shapePaint!!)
        }
    }
}
//</editor-fold>

/**
 * a round rect shows in background
 */
class Shape {
    var color: String? = null
    var colors: List<String>? = null
    var radius: Float = 0f
    internal var path: Path? = null
    var corners: Corners? = null
        set(value) {
            field = value
            path = Path()
        }

    class Corners(
        var leftTopRx: Float = 0f,
        var leftTopRy: Float = 0f,
        var leftBottomRx: Float = 0f,
        var LeftBottomRy: Float = 0f,
        var rightTopRx: Float = 0f,
        var rightTopRy: Float = 0f,
        var rightBottomRx: Float = 0f,
        var rightBottomRy: Float = 0f
    ) {
        val radii: FloatArray
            get() = floatArrayOf(leftTopRx, leftTopRy, rightTopRx, rightTopRy, rightBottomRx, rightBottomRy, leftBottomRx, LeftBottomRy)
    }
}

@RequiresApi(Build.VERSION_CODES.M)
inline fun OneViewGroup.text(init: Text.() -> Unit) = Text().apply(init).also { addDrawable(it) }

@RequiresApi(Build.VERSION_CODES.M)
inline fun OneViewGroup.image(init: ImageDrawable.() -> Unit) = ImageDrawable(context).apply(init).also {
    addView(it)
    addDrawable(it)
}


//<editor-fold desc="helper function for building Drawable by DSL">
fun OneViewGroup.drawableShape(init: Shape.() -> Unit): Shape = Shape().apply(init)

fun Shape.corners(init: Shape.Corners.() -> Unit): Shape.Corners = Shape.Corners().apply(init)

val gravity_center = Layout.Alignment.ALIGN_CENTER
val gravity_left = Layout.Alignment.ALIGN_NORMAL

inline var OneViewGroup.Drawable.drawable_top_margin: Int
    get() {
        return 0
    }
    set(value) {
        layoutTopMargin = value.dp
    }

inline var OneViewGroup.Drawable.drawable_bottom_margin: Int
    get() {
        return 0
    }
    set(value) {
        layoutBottomMargin = value.dp
    }

inline var OneViewGroup.Drawable.drawable_left_margin: Int
    get() {
        return 0
    }
    set(value) {
        layoutLeftMargin = value.dp
    }

inline var OneViewGroup.Drawable.drawable_right_margin: Int
    get() {
        return 0
    }
    set(value) {
        layoutRightMargin = value.dp
    }

inline var OneViewGroup.Drawable.drawable_padding_start: Int
    get() {
        return 0
    }
    set(value) {
        layoutPaddingStart = value.dp
    }

inline var OneViewGroup.Drawable.drawable_padding_end: Int
    get() {
        return 0
    }
    set(value) {
        layoutPaddingEnd = value.dp
    }

inline var OneViewGroup.Drawable.drawable_padding_top: Int
    get() {
        return 0
    }
    set(value) {
        layoutPaddingTop = value.dp
    }

inline var OneViewGroup.Drawable.drawable_padding_bottom: Int
    get() {
        return 0
    }
    set(value) {
        layoutPaddingBottom = value.dp
    }

inline var OneViewGroup.Drawable.drawable_layout_id: String
    get() {
        return ""
    }
    set(value) {
        layoutIdString = value
        layoutId = value.toLayoutId()
    }

inline var OneViewGroup.Drawable.drawable_start_to_start_of: String
    get() {
        return ""
    }
    set(value) {
        startToStartOf = value.toLayoutId()
    }

inline var OneViewGroup.Drawable.drawable_start_to_end_of: String
    get() {
        return ""
    }
    set(value) {
        startToEndOf = value.toLayoutId()
    }

inline var OneViewGroup.Drawable.drawable_end_to_start_of: String
    get() {
        return ""
    }
    set(value) {
        endToStartOf = value.toLayoutId()
    }

inline var OneViewGroup.Drawable.drawable_end_to_end_of: String
    get() {
        return ""
    }
    set(value) {
        endToEndOf = value.toLayoutId()
    }

inline var OneViewGroup.Drawable.drawable_top_to_top_of: String
    get() {
        return ""
    }
    set(value) {
        topToTopOf = value.toLayoutId()
    }

inline var OneViewGroup.Drawable.drawable_top_to_bottom_of: String
    get() {
        return ""
    }
    set(value) {
        topToBottomOf = value.toLayoutId()
    }

inline var OneViewGroup.Drawable.drawable_bottom_to_top_of: String
    get() {
        return ""
    }
    set(value) {
        bottomToTopOf = value.toLayoutId()
    }

inline var OneViewGroup.Drawable.drawable_bottom_to_bottom_of: String
    get() {
        return ""
    }
    set(value) {
        bottomToBottomOf = value.toLayoutId()
    }

inline var OneViewGroup.Drawable.drawable_center_horizontal_of: String
    get() {
        return ""
    }
    set(value) {
        centerHorizontalOf = value.toLayoutId()
    }

inline var OneViewGroup.Drawable.drawable_center_vertical_of: String
    get() {
        return ""
    }
    set(value) {
        centerVerticalOf = value.toLayoutId()
    }

inline var Text.drawable_gravity: Layout.Alignment
    get() {
        return Layout.Alignment.ALIGN_NORMAL
    }
    set(value) {
        gravity = value
    }

inline var Text.drawable_text_size: Float
    get() {
        return 0f
    }
    set(value) {
        textSize = value.sp
    }

inline var Text.drawable_max_width: Int
    get() {
        return 0
    }
    set(value) {
        maxWidth = value.dp
    }

inline var Text.drawable_layout_width: Int
    get() {
        return 0
    }
    set(value) {
        layoutWidth = value.dp
    }

inline var Text.drawable_layout_height: Int
    get() {
        return 0
    }
    set(value) {
        layoutHeight = value.dp
    }

inline var Text.drawable_text_color: String
    get() {
        return ""
    }
    set(value) {
        textColor = Color.parseColor(value)
    }

inline var Text.drawable_text: CharSequence
    get() {
        return ""
    }
    set(value) {
        text = value
    }

inline var Text.drawable_max_lines: Int
    get() {
        return 1
    }
    set(value) {
        maxLines = value
    }

inline var Text.drawable_shape: Shape
    get() {
        return Shape()
    }
    set(value) {
        drawableShape = value
    }


inline var Shape.radius: Float
    get() {
        return 0f
    }
    set(value) {
        radius = value.dp
    }
//</editor-fold>
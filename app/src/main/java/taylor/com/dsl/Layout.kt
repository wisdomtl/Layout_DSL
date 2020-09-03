package taylor.com.dsl

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import android.text.Editable
import android.util.TypedValue
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.helper.widget.Layer
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintProperties
import androidx.constraintlayout.widget.Guideline
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

/**
 * the extension functions and field in this file help you to build layout dynamically,
 * which has a better performance than xml files and more readable than normal java and kotlin code
 */
//<editor-fold desc="widget creation function">
inline fun ViewGroup.TextView(autoAdd: Boolean = true, init: TextView.() -> Unit) =
    TextView(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.ImageView(autoAdd: Boolean = true, init: ImageView.() -> Unit) =
    ImageView(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.Button(autoAdd: Boolean = true, init: Button.() -> Unit) =
    Button(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.View(autoAdd: Boolean = true, init: View.() -> Unit): View =
    View(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.RelativeLayout(autoAdd: Boolean = true, init: RelativeLayout.() -> Unit) =
    RelativeLayout(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.LinearLayout(autoAdd: Boolean = true, init: LinearLayout.() -> Unit) =
    LinearLayout(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.NestedScrollView(autoAdd: Boolean = true, init: NestedScrollView.() -> Unit) =
    NestedScrollView(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.RecyclerView(autoAdd: Boolean = true, init: RecyclerView.() -> Unit) =
    RecyclerView(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.ConstraintLayout(autoAdd: Boolean = true, init: ConstraintLayout.() -> Unit) =
    ConstraintLayout(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.FrameLayout(autoAdd: Boolean = true, init: FrameLayout.() -> Unit) =
    FrameLayout(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.ViewFlipper(autoAdd: Boolean = true, init: ViewFlipper.() -> Unit) =
    ViewFlipper(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.EditText(autoAdd: Boolean = true, init: EditText.() -> Unit) =
    EditText(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.HorizontalScrollView(autoAdd: Boolean = true, init: HorizontalScrollView.() -> Unit) =
    HorizontalScrollView(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.ViewPager2(autoAdd: Boolean = true, init: ViewPager2.() -> Unit) =
    ViewPager2(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ConstraintLayout.Guideline(autoAdd: Boolean = true, init: Guideline.() -> Unit) =
    Guideline(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ConstraintLayout.Flow(autoAdd: Boolean = true, init: Flow.() -> Unit) =
    Flow(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ConstraintLayout.Layer(autoAdd: Boolean = true, init: Layer.() -> Unit) =
    Layer(context).apply(init).also { if (autoAdd) addView(it) }

inline fun Context.ConstraintLayout(init: ConstraintLayout.() -> Unit): ConstraintLayout =
    ConstraintLayout(this).apply(init)

inline fun Context.LinearLayout(init: LinearLayout.() -> Unit): LinearLayout =
    LinearLayout(this).apply(init)

inline fun Context.FrameLayout(init: FrameLayout.() -> Unit) =
    FrameLayout(this).apply(init)

inline fun Context.NestedScrollView(init: NestedScrollView.() -> Unit) =
    NestedScrollView(this).apply(init)

inline fun Context.TextView(init: TextView.() -> Unit) =
    TextView(this).apply(init)

inline fun Context.Button(init: Button.() -> Unit) =
    Button(this).apply(init)

inline fun Context.ImageView(init: ImageView.() -> Unit) =
    ImageView(this).apply(init)

inline fun Context.View(init: View.() -> Unit) =
    View(this).apply(init)

inline fun Context.EditText(init: EditText.() -> Unit) =
    EditText(this).apply(init)

inline fun Context.ViewFlipper(init: ViewFlipper.() -> Unit) =
    ViewFlipper(this).apply(init)

inline fun Context.HorizontalScrollView(init: HorizontalScrollView.() -> Unit) =
    HorizontalScrollView(this).apply(init)

inline fun Context.ViewPager2(init: ViewPager2.() -> Unit) =
    ViewPager2(this).apply(init)

inline fun Context.RecyclerView(init: RecyclerView.() -> Unit) =
    RecyclerView(this).apply(init)

inline fun Fragment.ConstraintLayout(init: ConstraintLayout.() -> Unit) =
    context?.let { ConstraintLayout(it).apply(init) }

inline fun Fragment.LinearLayout(init: LinearLayout.() -> Unit) =
    context?.let { LinearLayout(it).apply(init) }

inline fun Fragment.FrameLayout(init: FrameLayout.() -> Unit) =
    context?.let { FrameLayout(it).apply(init) }

inline fun Fragment.NestedScrollView(init: NestedScrollView.() -> Unit) =
    context?.let { NestedScrollView(it).apply(init) }

inline fun Fragment.TextView(init: TextView.() -> Unit) =
    context?.let { TextView(it).apply(init) }

inline fun Fragment.Button(init: Button.() -> Unit) =
    context?.let { Button(it).apply(init) }

inline fun Fragment.ImageView(init: ImageView.() -> Unit) =
    context?.let { ImageView(it).apply(init) }

inline fun Fragment.View(init: View.() -> Unit) =
    context?.let { View(it).apply(init) }

inline fun Fragment.ViewFlipper(init: ViewFlipper.() -> Unit) =
    context?.let { ViewFlipper(it).apply(init) }

inline fun Fragment.EditText(init: EditText.() -> Unit) =
    context?.let { EditText(it).apply(init) }

inline fun Fragment.HorizontalScrollView(init: HorizontalScrollView.() -> Unit) =
    context?.let { HorizontalScrollView(it).apply(init) }

inline fun Fragment.ViewPager2(init: ViewPager2.() -> Unit) =
    context?.let { ViewPager2(it).apply(init) }

inline fun Fragment.RecyclerView(init: RecyclerView.() -> Unit) =
    context?.let { RecyclerView(it).apply(init) }
//</editor-fold>

//<editor-fold desc="View extend field">
inline var View.layout_id: String
    get() {
        return ""
    }
    set(value) {
        id = value.toLayoutId()
    }
inline var View.padding_top: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(paddingLeft, value.dp, paddingRight, paddingBottom)
    }

inline var View.padding_bottom: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(paddingLeft, paddingTop, paddingRight, value.dp)
    }

inline var View.padding_start: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(value.dp, paddingTop, paddingRight, paddingBottom)
    }

inline var View.padding_end: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(paddingLeft, paddingTop, value.dp, paddingBottom)
    }
inline var View.padding: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(value.dp, value.dp, value.dp, value.dp)
    }
inline var View.layout_width: Int
    get() {
        return 0
    }
    set(value) {
        val w = if (value > 0) value.dp else value
        val h = layoutParams?.height ?: 0
        layoutParams = ViewGroup.MarginLayoutParams(w, h)
    }

inline var View.layout_height: Int
    get() {
        return 0
    }
    set(value) {

        val w = layoutParams?.width ?: 0
        val h = if (value > 0) value.dp else value
        layoutParams = ViewGroup.MarginLayoutParams(w, h)
    }

inline var View.alignParentStart: Boolean
    get() {
        return false
    }
    set(value) {
        if (!value) return
        layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
            (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
                addRule(index, i)
            }
            addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE)
        }
    }

inline var View.alignParentEnd: Boolean
    get() {
        return false
    }
    set(value) {
        if (!value) return
        layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
            (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
                addRule(index, i)
            }
            addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
        }
    }

inline var View.centerVertical: Boolean
    get() {
        return false
    }
    set(value) {
        if (!value) return
        layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
            (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
                addRule(index, i)
            }
            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
        }
    }

inline var View.centerInParent: Boolean
    get() {
        return false
    }
    set(value) {
        if (!value) return
        layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
            (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
                addRule(index, i)
            }
            addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        }
    }

inline var View.weight: Float
    get() {
        return 0f
    }
    set(value) {
        layoutParams =
            LinearLayout.LayoutParams(layoutParams.width, layoutParams.height).also { it ->
                it.gravity = (layoutParams as? LinearLayout.LayoutParams)?.gravity ?: -1
                it.weight = value
            }
    }
inline var View.layout_gravity: Int
    get() {
        return -1
    }
    set(value) {
        layoutParams =
            LinearLayout.LayoutParams(layoutParams.width, layoutParams.height).also { it ->
                it.weight = (layoutParams as? LinearLayout.LayoutParams)?.weight ?: 0f
                it.gravity = value
            }
    }

inline var View.toCircleOf:String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            circleConstraint = value.toLayoutId()
        }
    }

inline var View.circle_radius: Int
    get() {
        return -1
    }
    set(value) {
        layoutParams = layoutParams.append {
            circleRadius = value.dp
        }
    }

inline var View.circle_angle:Float
    get() {
        return  -1f
    }
    set(value) {
        layoutParams = layoutParams.append {
            circleAngle = value
        }
    }

inline var View.start_toStartOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            startToStart = value.toLayoutId()
            startToEnd = -1
        }
    }

inline var View.start_toEndOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            startToEnd = value.toLayoutId()
            startToStart = -1
        }
    }

inline var View.top_toBottomOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            topToBottom = value.toLayoutId()
            topToTop = -1
        }
    }

inline var View.top_toTopOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            topToTop = value.toLayoutId()
            topToBottom = -1
        }
    }

inline var View.end_toEndOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            endToEnd = value.toLayoutId()
            endToStart = -1
        }
    }

inline var View.end_toStartOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            endToStart = value.toLayoutId()
            endToEnd = -1
        }
    }

inline var View.bottom_toBottomOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            bottomToBottom = value.toLayoutId()
            bottomToTop = -1
        }
    }

inline var View.bottom_toTopOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            bottomToTop = value.toLayoutId()
            bottomToBottom = -1
        }
    }

inline var View.horizontal_chain_style: Int
    get() {
        return -1
    }
    set(value) {
        layoutParams = layoutParams.append {
            horizontalChainStyle = value
        }
    }

inline var View.vertical_chain_style: Int
    get() {
        return -1
    }
    set(value) {
        layoutParams = layoutParams.append {
            verticalChainStyle = value
        }
    }

inline var View.horizontal_bias: Float
    get() {
        return -1f
    }
    set(value) {
        layoutParams = layoutParams.append {
            horizontalBias = value
        }
    }
inline var View.dimension_radio: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            dimensionRatio = value
        }
    }

inline var View.vertical_bias: Float
    get() {
        return -1f
    }
    set(value) {
        layoutParams = layoutParams.append {
            verticalBias = value
        }
    }

inline var View.center_horizontal: Boolean
    get() {
        return false
    }
    set(value) {
        if (!value) return
        start_toStartOf = parent_id
        end_toEndOf = parent_id
    }

inline var View.center_vertical: Boolean
    get() {
        return false
    }
    set(value) {
        if (!value) return
        top_toTopOf = parent_id
        bottom_toBottomOf = parent_id
    }

inline var View.align_vertical_to: String
    get() {
        return ""
    }
    set(value) {
        top_toTopOf = value
        bottom_toBottomOf = value
    }

inline var View.align_horizontal_to: String
    get() {
        return ""
    }
    set(value) {
        start_toStartOf = value
        end_toEndOf = value
    }

inline var View.width_percentage: Float
    get() {
        return -1f
    }
    set(value) {
        layoutParams = layoutParams.append {
            width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            matchConstraintPercentWidth = value
        }
    }

inline var View.height_percentage: Float
    get() {
        return -1f
    }
    set(value) {
        layoutParams = layoutParams.append {
            height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            matchConstraintPercentHeight = value
        }
    }

inline var View.background_color: String
    get() {
        return ""
    }
    set(value) {
        setBackgroundColor(Color.parseColor(value))
    }

inline var View.background_res: Int
    get() {
        return -1
    }
    set(value) {
        setBackgroundResource(value)
    }

inline var View.margin_top: Int
    get() {
        return -1
    }
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
            topMargin = value.dp
        }
    }

inline var View.margin_bottom: Int
    get() {
        return -1
    }
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
            bottomMargin = value.dp
        }
    }

inline var View.margin_start: Int
    get() {
        return -1
    }
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
            MarginLayoutParamsCompat.setMarginStart(this, value.dp)
        }
    }

inline var View.margin_end: Int
    get() {
        return -1
    }
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
            MarginLayoutParamsCompat.setMarginEnd(this, value.dp)
        }
    }

inline var View.layout_visibility: Int
    get() {
        return -1
    }
    set(value) {
        visibility = value
    }

/**
 * bind async data
 */
inline var View.bindLiveData: LiveDataBinder?
    get() {
        return null
    }
    set(value) {
        observe(value?.liveData) {
            value?.action?.invoke(it)
        }
    }

/**
 * old fashion for binding data
 */
inline var View.bind: Binder?
    get() {
        return null
    }
    set(value) {
        value?.action?.invoke(this, value.data)
    }

/**
 * bind sync data
 */
inline var View.bindData: () -> Unit
    get() {
        return {}
    }
    set(value) {
        value()
    }

inline var View.fitsSystemWindows: Boolean
    get() {
        return false
    }
    set(value) {
        fitsSystemWindows = value
    }

inline var ImageView.src: Int
    get() {
        return -1
    }
    set(value) {
        setImageResource(value)
    }

inline var TextView.textRes: Int
    get() {
        return -1
    }
    set(value) {
        setText(value)
    }

inline var TextView.textStyle: Int
    get() {
        return -1
    }
    set(value) = setTypeface(typeface, value)

inline var TextView.textColor: String
    get() {
        return ""
    }
    set(value) {
        setTextColor(Color.parseColor(value))
    }

inline var TextView.fontFamily: Int
    get() {
        return 1
    }
    set(value) {
        typeface = ResourcesCompat.getFont(context, value)
    }

inline var TextView.drawable_start: Int
    get() {
        return -1
    }
    set(value) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(value, 0, 0, 0)
    }

inline var TextView.drawable_end: Int
    get() {
        return -1
    }
    set(value) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, value, 0)
    }

inline var TextView.drawable_top: Int
    get() {
        return -1
    }
    set(value) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(0, value, 0, 0)
    }

inline var TextView.drawable_bottom: Int
    get() {
        return -1
    }
    set(value) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, value)
    }

inline var TextView.drawable_padding: Int
    get() {
        return 0
    }
    set(value) {
        compoundDrawablePadding = value.dp
    }

inline var TextView.onTextChange: TextWatcher
    get() {
        return TextWatcher()
    }
    set(value) {
        val textWatcher = object : android.text.TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                value.afterTextChanged.invoke(s)
            }

            override fun beforeTextChanged(
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                value.beforeTextChanged.invoke(text, start, count, after)
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                value.onTextChanged.invoke(text, start, before, count)
            }
        }
        addTextChangedListener(textWatcher)
    }

inline var Button.textAllCaps: Boolean
    get() {
        return false
    }
    set(value) {
        isAllCaps = value
    }


inline var NestedScrollView.fadeScrollBar: Boolean
    get() {
        return false
    }
    set(value) {
        isScrollbarFadingEnabled = true
    }

inline var ConstraintHelper.referenceIds: String
    get() {
        return ""
    }
    set(value) {
        referencedIds = value.split(",").map { it.toLayoutId() }.toIntArray()
    }

inline var Flow.flow_horizontalGap: Int
    get() {
        return 0
    }
    set(value) {
        setHorizontalGap(value.dp)
    }

inline var Flow.flow_verticalGap: Int
    get() {
        return 0
    }
    set(value) {
        setVerticalGap(value.dp)
    }

inline var Flow.flow_wrapMode: Int
    get() {
        return 0
    }
    set(value) {
        setWrapMode(value)
    }

var View.onClick: (View) -> Unit
    get() {
        return {}
    }
    set(value) {
        setOnClickListener { v -> value(v) }
    }

var RecyclerView.onItemClick: (View, Int,Float,Float) -> Unit
    get() {
        return { _, _,_,_ -> }
    }
    set(value) {
        setOnItemClickListener(value)
    }
//</editor-fold>


//<editor-fold desc="View layout constant">
val match_parent = ViewGroup.LayoutParams.MATCH_PARENT
val wrap_content = ViewGroup.LayoutParams.WRAP_CONTENT

val visible = View.VISIBLE
val gone = View.GONE
val invisible = View.INVISIBLE

val horizontal = LinearLayout.HORIZONTAL
val vertical = LinearLayout.VERTICAL

val bold = Typeface.BOLD
val normal = Typeface.NORMAL
val italic = Typeface.ITALIC
val bold_italic = Typeface.BOLD_ITALIC

val gravity_center = Gravity.CENTER
val gravity_left = Gravity.LEFT
val gravity_right = Gravity.RIGHT
val gravity_bottom = Gravity.BOTTOM
val gravity_top = Gravity.TOP
val gravity_center_horizontal = Gravity.CENTER_HORIZONTAL
val gravity_center_vertical = Gravity.CENTER_VERTICAL

val scale_fix_xy = ImageView.ScaleType.FIT_XY
val scale_center_crop = ImageView.ScaleType.CENTER_CROP
val scale_center = ImageView.ScaleType.CENTER
val scale_center_inside = ImageView.ScaleType.CENTER_INSIDE
val scale_fit_center = ImageView.ScaleType.FIT_CENTER
val scale_fit_end = ImageView.ScaleType.FIT_END
val scale_matrix = ImageView.ScaleType.MATRIX
val scale_fit_start = ImageView.ScaleType.FIT_START

val constraint_start = ConstraintProperties.START
val constraint_end = ConstraintProperties.END
val constraint_top = ConstraintProperties.TOP
val constraint_bottom = ConstraintProperties.BOTTOM
val constraint_baseline = ConstraintProperties.BASELINE
val constraint_parent = ConstraintProperties.PARENT_ID

val spread = ConstraintLayout.LayoutParams.CHAIN_SPREAD
val packed = ConstraintLayout.LayoutParams.CHAIN_PACKED
val spread_inside = ConstraintLayout.LayoutParams.CHAIN_SPREAD_INSIDE

val wrap_none = Flow.WRAP_NONE
val wrap_chain = Flow.WRAP_CHAIN
val wrap_aligned = Flow.WRAP_ALIGNED

val parent_id = "0"
//</editor-fold>

//<editor-fold desc="layout helper function">
val Int.dp: Int
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }

fun ViewGroup.MarginLayoutParams.toConstraintLayoutParam() =
    ConstraintLayout.LayoutParams(width, height).also { it ->
        it.topMargin = this.topMargin
        it.bottomMargin = this.bottomMargin
        it.marginStart = this.marginStart
        it.marginEnd = this.marginEnd
    }

fun ViewGroup.LayoutParams.append(set: ConstraintLayout.LayoutParams.() -> Unit) =
    (this as? ConstraintLayout.LayoutParams)?.apply(set)
        ?: (this as? ViewGroup.MarginLayoutParams)?.toConstraintLayoutParam()?.apply(set)


fun String.toLayoutId(): Int {
    var id = java.lang.String(this).bytes.sum()
    if (id == 48) id = 0
    return id
}

fun <T : View> View.find(id: String): T? = findViewById(id.toLayoutId())

fun <T : View> AppCompatActivity.find(id: String): T? = findViewById(id.toLayoutId())

fun <T> View.observe(liveData: LiveData<T>?, action: (T) -> Unit) {
    (context as? LifecycleOwner)?.let { owner ->
        liveData?.observe(owner, Observer { action(it) })
    }
}

fun RecyclerView.setOnItemClickListener(listener: (View, Int, Float, Float) -> Unit) {
    addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
        val gestureDetector = GestureDetector(context, object : GestureDetector.OnGestureListener {
            override fun onShowPress(e: MotionEvent?) {
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                e?.let {
                    findChildViewUnder(it.x, it.y)?.let { child ->
                        listener(child, getChildAdapterPosition(child), it.x - child.left, it.y - child.top)
                    }
                }
                return false
            }

            override fun onDown(e: MotionEvent?): Boolean {
                return false
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

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            gestureDetector.onTouchEvent(e)
            return false
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        }
    })
}

/**
 * get relative position of this [View] relative to [otherView]
 */
fun View.getRelativeRectTo(otherView: View): Rect {
    val parentRect = Rect().also { otherView.getGlobalVisibleRect(it) }
    val childRect = Rect().also { getGlobalVisibleRect(it) }
    return childRect.relativeTo(parentRect)
}

/**
 *  listen click action for the child view of [RecyclerView]'s item
 */
inline fun <T : View> View.onChildViewClick(layoutId: String, x: Float, y: Float, clickAction: ((View) -> Unit)) {
    find<T>(layoutId)?.let {childView->
        childView.getRelativeRectTo(this).takeIf { it.contains(x.toInt(), y.toInt()) }?.let { clickAction(this) }
    }
}

/**
 * get the relative rect of the [Rect] according to the [otherRect] ,considering the [otherRect]'s left and top is zero
 */
fun Rect.relativeTo(otherRect: Rect): Rect {
    val relativeLeft = left - otherRect.left
    val relativeTop = top - otherRect.top
    val relativeRight = relativeLeft + right - left
    val relativeBottom = relativeTop + bottom - top
    return Rect(relativeLeft, relativeTop, relativeRight, relativeBottom)
}
//</editor-fold>


//<editor-fold desc="listener helper class">
class TextWatcher(
    var beforeTextChanged: (
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) -> Unit = { _, _, _, _ -> },
    var onTextChanged: (
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) -> Unit = { _, _, _, _ -> },
    var afterTextChanged: (text: Editable?) -> Unit = {}
)

fun textWatcher(init: TextWatcher.() -> Unit): TextWatcher = TextWatcher().apply(init)

/**
 * helper class for data binding
 */
class LiveDataBinder(var liveData: LiveData<*>? = null, var action: ((Any?) -> Unit)? = null)

fun liveDataBinder(liveData: LiveData<*>?, init: LiveDataBinder.() -> Unit): LiveDataBinder =
    LiveDataBinder(liveData).apply(init)

class Binder(var data: Any?, var action: ((View, Any?) -> Unit)? = null)


//</editor-fold>
package taylor.com.dsl

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.util.TypedValue
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.*
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * the extension functions and field in this file help you to build layout dynamically,
 * which has a better performance than xml files and more readable than normal java and kotlin code
 */
//<editor-fold desc="widget creation function">
/**
 * create [AppCompatTextView] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [AppCompatTextView] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.TextView(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: AppCompatTextView.() -> Unit
): TextView {
    val textView =
        if (style != null) AppCompatTextView(
            ContextThemeWrapper(context, style)
        ) else AppCompatTextView(context)
    return textView.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [AppCompatImageView] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [AppCompatImageView] into [ViewGroup] automatically
 *  @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.ImageView(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: AppCompatImageView.() -> Unit
): ImageView {
    val imageView =
        if (style != null) AppCompatImageView(
            ContextThemeWrapper(context, style)
        ) else AppCompatImageView(context)
    return imageView.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [AppCompatButton] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [AppCompatButton] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.Button(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: AppCompatButton.() -> Unit
): Button {
    val button =
        if (style != null) AppCompatButton(
            ContextThemeWrapper(context, style)
        ) else AppCompatButton(context)
    return button.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [View] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [View] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.View(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: View.() -> Unit
): View {
    val view =
        if (style != null) View(
            ContextThemeWrapper(context, style)
        ) else View(context)
    return view.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [RelativeLayout] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [RelativeLayout] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.RelativeLayout(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: RelativeLayout.() -> Unit
): RelativeLayout {
    val relativeLayout =
        if (style != null) RelativeLayout(
            ContextThemeWrapper(context, style)
        ) else RelativeLayout(context)
    return relativeLayout.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [LinearLayout] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [LinearLayoutCompat] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.LinearLayout(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: LinearLayout.() -> Unit
): LinearLayout {
    val linearLayout =
        if (style != null) LinearLayout(
            ContextThemeWrapper(context, style)
        ) else LinearLayout(context)
    return linearLayout.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [NestedScrollView] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [NestedScrollView] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.NestedScrollView(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: NestedScrollView.() -> Unit
): NestedScrollView {
    val nestedScrollView =
        if (style != null) NestedScrollView(
            ContextThemeWrapper(context, style)
        ) else NestedScrollView(context)
    return nestedScrollView.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [RecyclerView] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [RecyclerView] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.RecyclerView(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: RecyclerView.() -> Unit
): RecyclerView {
    val recyclerView =
        if (style != null) RecyclerView(
            ContextThemeWrapper(context, style)
        ) else RecyclerView(context)
    return recyclerView.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [ConstraintLayout] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [ConstraintLayout] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.ConstraintLayout(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: ConstraintLayout.() -> Unit
): ConstraintLayout {
    val constraintLayout =
        if (style != null) ConstraintLayout(
            ContextThemeWrapper(context, style)
        ) else ConstraintLayout(context)
    return constraintLayout.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [FrameLayout] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [FrameLayout] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.FrameLayout(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: FrameLayout.() -> Unit
): FrameLayout {
    val frameLayout =
        if (style != null) FrameLayout(
            ContextThemeWrapper(context, style)
        ) else FrameLayout(context)
    return frameLayout.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [ViewFlipper] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [ViewFlipper] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.ViewFlipper(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: ViewFlipper.() -> Unit
): ViewFlipper {
    val viewFlipper =
        if (style != null) ViewFlipper(
            ContextThemeWrapper(context, style)
        ) else ViewFlipper(context)
    return viewFlipper.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [AppCompatEditText] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [AppCompatEditText] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.EditText(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: AppCompatEditText.() -> Unit
): AppCompatEditText {
    val editText =
        if (style != null) AppCompatEditText(
            ContextThemeWrapper(context, style)
        ) else AppCompatEditText(context)
    return editText.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [HorizontalScrollView] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [HorizontalScrollView] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.HorizontalScrollView(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: HorizontalScrollView.() -> Unit
): HorizontalScrollView {
    val horizontalScrollView =
        if (style != null) HorizontalScrollView(
            ContextThemeWrapper(context, style)
        ) else HorizontalScrollView(context)
    return horizontalScrollView.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [ViewPager2] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [ViewPager2] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.ViewPager2(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: ViewPager2.() -> Unit
): ViewPager2 {
    val viewPager2 =
        if (style != null) ViewPager2(
            ContextThemeWrapper(context, style)
        ) else ViewPager2(context)
    return viewPager2.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [Guideline] instance within a [ConstraintLayout]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [Guideline] into [ConstraintLayout] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ConstraintLayout.Guideline(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: Guideline.() -> Unit
): Guideline {
    val guideline =
        if (style != null) Guideline(
            ContextThemeWrapper(context, style)
        ) else Guideline(context)
    return guideline.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [Flow] instance within a [ConstraintLayout]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [Flow] into [ConstraintLayout] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ConstraintLayout.Flow(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: Flow.() -> Unit
): Flow {
    val flow =
        if (style != null) Flow(
            ContextThemeWrapper(context, style)
        ) else Flow(context)
    return flow.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [Layer] instance within a [ConstraintLayout]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [Layer] into [ConstraintLayout] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ConstraintLayout.Layer(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: Layer.() -> Unit
): Layer {
    val layer =
        if (style != null) Layer(
            ContextThemeWrapper(context, style)
        ) else Layer(context)
    return layer.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [ConstraintLayout] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.ConstraintLayout(
    style: Int? = null,
    init: ConstraintLayout.() -> Unit
): ConstraintLayout {
    val constraintLayout =
        if (style != null) ConstraintLayout(
            ContextThemeWrapper(this, style)
        ) else ConstraintLayout(this)
    return constraintLayout.apply(init)
}

/**
 * create [LinearLayout] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.LinearLayout(
    style: Int? = null,
    init: LinearLayout.() -> Unit
): LinearLayout {
    val LinearLayout =
        if (style != null) LinearLayout(
            ContextThemeWrapper(this, style)
        ) else LinearLayout(this)
    return LinearLayout.apply(init)
}

/**
 * create [FrameLayout] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.FrameLayout(style: Int? = null, init: FrameLayout.() -> Unit): FrameLayout {
    val frameLayout =
        if (style != null) FrameLayout(
            ContextThemeWrapper(this, style)
        ) else FrameLayout(this)
    return frameLayout.apply(init)
}

/**
 * create [NestedScrollView] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.NestedScrollView(
    style: Int? = null,
    init: NestedScrollView.() -> Unit
): NestedScrollView {
    val nestedScrollView =
        if (style != null) NestedScrollView(
            ContextThemeWrapper(this, style)
        ) else NestedScrollView(this)
    return nestedScrollView.apply(init)
}

/**
 * create [AppCompatTextView] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.TextView(
    style: Int? = null,
    init: AppCompatTextView.() -> Unit
): AppCompatTextView {
    val textView =
        if (style != null) AppCompatTextView(
            ContextThemeWrapper(this, style)
        ) else AppCompatTextView(this)
    return textView.apply(init)

}

/**
 * create [AppCompatButton] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.Button(style: Int? = null, init: AppCompatButton.() -> Unit): AppCompatButton {
    val button =
        if (style != null) AppCompatButton(
            ContextThemeWrapper(this, style)
        ) else AppCompatButton(this)
    return button.apply(init)
}

/**
 * create [AppCompatImageView] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.ImageView(
    style: Int? = null,
    init: AppCompatImageView.() -> Unit
): AppCompatImageView {
    val imageView =
        if (style != null) AppCompatImageView(
            ContextThemeWrapper(this, style)
        ) else AppCompatImageView(this)
    return imageView.apply(init)
}

/**
 * create [View] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.View(style: Int? = null, init: View.() -> Unit): View {
    val view =
        if (style != null) View(
            ContextThemeWrapper(this, style)
        ) else View(this)
    return view.apply(init)
}

/**
 * create [AppCompatEditText] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.EditText(
    style: Int? = null,
    init: AppCompatEditText.() -> Unit
): AppCompatEditText {
    val editText =
        if (style != null) AppCompatEditText(
            ContextThemeWrapper(this, style)
        ) else AppCompatEditText(this)
    return editText.apply(init)
}

/**
 * create [ViewFlipper] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.ViewFlipper(style: Int? = null, init: ViewFlipper.() -> Unit): ViewFlipper {
    val viewFlipper =
        if (style != null) ViewFlipper(
            ContextThemeWrapper(this, style)
        ) else ViewFlipper(this)
    return viewFlipper.apply(init)

}

/**
 * create [HorizontalScrollView] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.HorizontalScrollView(
    style: Int? = null,
    init: HorizontalScrollView.() -> Unit
): HorizontalScrollView {
    val horizontalScrollView =
        if (style != null) HorizontalScrollView(
            ContextThemeWrapper(this, style)
        ) else HorizontalScrollView(this)
    return horizontalScrollView.apply(init)
}

/**
 * create [ViewPager2] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.ViewPager2(
    style: Int? = null,
    init: ViewPager2.() -> Unit
): ViewPager2 {
    val viewPager2 =
        if (style != null) ViewPager2(
            ContextThemeWrapper(this, style)
        ) else ViewPager2(this)
    return viewPager2.apply(init)
}

/**
 * create [ViewPager2] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.RecyclerView(
    style: Int? = null,
    init: RecyclerView.() -> Unit
): RecyclerView {
    val recyclerView =
        if (style != null) RecyclerView(
            ContextThemeWrapper(this, style)
        ) else RecyclerView(this)
    return recyclerView.apply(init)
}

/**
 * create [ConstraintLayout] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.ConstraintLayout(
    style: Int? = null,
    init: ConstraintLayout.() -> Unit
): ConstraintLayout? = context?.let {
    if (style != null) ConstraintLayout(
        ContextThemeWrapper(it, style)
    ) else ConstraintLayout(it)
}?.apply(init)

/**
 * create [LinearLayout] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.LinearLayout(
    style: Int? = null,
    init: LinearLayout.() -> Unit
): LinearLayout? = context?.let {
    if (style != null) LinearLayout(
        ContextThemeWrapper(it, style)
    ) else LinearLayout(it)
}?.apply(init)

/**
 * create [FrameLayout] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.FrameLayout(
    style: Int? = null,
    init: FrameLayout.() -> Unit
): FrameLayout? = context?.let {
    if (style != null) FrameLayout(
        ContextThemeWrapper(it, style)
    ) else FrameLayout(it)
}?.apply(init)

/**
 * create [NestedScrollView] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.NestedScrollView(
    style: Int? = null,
    init: FrameLayout.() -> Unit
): NestedScrollView? = context?.let {
    if (style != null) NestedScrollView(
        ContextThemeWrapper(it, style)
    ) else NestedScrollView(it)
}?.apply(init)

/**
 * create [AppCompatTextView] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.TextView(
    style: Int? = null,
    init: AppCompatTextView.() -> Unit
): AppCompatTextView? = context?.let {
    if (style != null) AppCompatTextView(
        ContextThemeWrapper(it, style)
    ) else AppCompatTextView(it)
}?.apply(init)

/**
 * create [AppCompatButton] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.Button(
    style: Int? = null,
    init: AppCompatButton.() -> Unit
): AppCompatButton? = context?.let {
    if (style != null) AppCompatButton(
        ContextThemeWrapper(it, style)
    ) else AppCompatButton(it)
}?.apply(init)

/**
 * create [AppCompatImageView] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.ImageView(
    style: Int? = null,
    init: AppCompatImageView.() -> Unit
): AppCompatImageView? = context?.let {
    if (style != null) AppCompatImageView(
        ContextThemeWrapper(it, style)
    ) else AppCompatImageView(it)
}?.apply(init)

/**
 * create [View] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.View(
    style: Int? = null,
    init: View.() -> Unit
): View? = context?.let {
    if (style != null) View(
        ContextThemeWrapper(it, style)
    ) else View(it)
}?.apply(init)

/**
 * create [ViewFlipper] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.ViewFlipper(
    style: Int? = null,
    init: ViewFlipper.() -> Unit
): ViewFlipper? = context?.let {
    if (style != null) ViewFlipper(
        ContextThemeWrapper(it, style)
    ) else ViewFlipper(it)
}?.apply(init)

/**
 * create [AppCompatEditText] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.EditText(
    style: Int? = null,
    init: AppCompatEditText.() -> Unit
): AppCompatEditText? = context?.let {
    if (style != null) AppCompatEditText(
        ContextThemeWrapper(it, style)
    ) else AppCompatEditText(it)
}?.apply(init)

/**
 * create [HorizontalScrollView] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.HorizontalScrollView(
    style: Int? = null,
    init: HorizontalScrollView.() -> Unit
): HorizontalScrollView? = context?.let {
    if (style != null) HorizontalScrollView(
        ContextThemeWrapper(it, style)
    ) else HorizontalScrollView(it)
}?.apply(init)

/**
 * create [ViewPager2] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.ViewPager2(
    style: Int? = null,
    init: ViewPager2.() -> Unit
): ViewPager2? = context?.let {
    if (style != null) ViewPager2(
        ContextThemeWrapper(it, style)
    ) else ViewPager2(it)
}?.apply(init)

/**
 * create [ViewPager2] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.RecyclerView(
    style: Int? = null,
    init: RecyclerView.() -> Unit
): RecyclerView? = context?.let {
    if (style != null) RecyclerView(
        ContextThemeWrapper(it, style)
    ) else RecyclerView(it)
}?.apply(init)

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
        layoutParams = if (layoutParams == null) {
            ViewGroup.MarginLayoutParams(w, h)
        } else {
            layoutParams.append {
                width = w
                height = h
            }
        }
    }

inline var View.layout_height: Int
    get() {
        return 0
    }
    set(value) {

        val w = layoutParams?.width ?: 0
        val h = if (value > 0) value.dp else value
        layoutParams = if (layoutParams == null) {
            ViewGroup.MarginLayoutParams(w, h)
        } else {
            layoutParams.append {
                width = w
                height = h
            }
        }
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

inline var View.toCircleOf: String
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

inline var View.circle_angle: Float
    get() {
        return -1f
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

inline var View.background_drawable: Drawable?
    get() {
        return null
    }
    set(value) {
        value?.let { background = it }
    }

inline var View.background_drawable_state_list: List<Pair<IntArray, Drawable>>
    get() {
        return listOf(intArrayOf() to GradientDrawable())
    }
    set(value) {
        background = StateListDrawable().apply {
            value.forEach { pair ->
                addState(pair.first, pair.second)
            }
        }
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

inline var View.guide_percentage: Float
    get() {
        return -1f
    }
    set(value) {
        layoutParams = layoutParams.append {
            guidePercent = value
        }
    }

inline var View.guide_orientation: Int
    get() {
        return 1
    }
    set(value) {
        layoutParams = layoutParams.append {
            orientation = value
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

/**
 * use this attribute to build shape dynamically, getting rid of "shape.xml"
 */
inline var View.shape: GradientDrawable
    get() {
        return GradientDrawable()
    }
    set(value) {
        background = value
    }

inline var ImageView.src: Int
    get() {
        return -1
    }
    set(value) {
        setImageResource(value)
    }

inline var TextView.maxLength: Int
    get() {
        return 1
    }
    set(value) {
        filters = arrayOf<InputFilter>(LengthFilter(value))
    }

inline var TextView.textRes: Int
    get() {
        return -1
    }
    set(value) {
        setText(value)
    }

inline var TextView.hint_color: String
    get() {
        return ""
    }
    set(value) {
        setHintTextColor(Color.parseColor(value))
    }

inline var TextView.hint_text_res: Int
    get() {
        return -1
    }
    set(value) {
        setHint(value)
    }

inline var TextView.line_space_multiplier: Float
    get() {
        return -1f
    }
    set(value) {
        setLineSpacing(lineSpacingExtra, value)
    }

inline var TextView.line_space_extra: Float
    get() {
        return -1f
    }
    set(value) {
        setLineSpacing(value, lineSpacingMultiplier)
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

var View.shakelessClick: (View) -> Unit
    get() {
        return {}
    }
    set(value) {
        setShakelessClickListener(1000) {
            value(it)
        }
    }

var RecyclerView.onItemClick: (View, Int, Float, Float) -> Unit
    get() {
        return { _, _, _, _ -> }
    }
    set(value) {
        setOnItemClickListener(value)
    }

var RecyclerView.hasFixedSize: Boolean
    get() {
        return false
    }
    set(value) {
        setHasFixedSize(value)
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

val scale_fit_xy = ImageView.ScaleType.FIT_XY
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

val gradient_top_bottom = GradientDrawable.Orientation.TOP_BOTTOM
val gradient_tr_bl = GradientDrawable.Orientation.TR_BL
val gradient_right_left = GradientDrawable.Orientation.RIGHT_LEFT
val gradient_br_tl = GradientDrawable.Orientation.BR_TL
val gradient_bottom_top = GradientDrawable.Orientation.BOTTOM_TOP
val gradient_bl_tr = GradientDrawable.Orientation.BL_TR
val gradient_left_right = GradientDrawable.Orientation.LEFT_RIGHT
val gradient_tl_br = GradientDrawable.Orientation.TL_BR

val shape_rectangle = GradientDrawable.RECTANGLE
val shape_oval = GradientDrawable.OVAL
val shape_line = GradientDrawable.LINE
val shape_ring = GradientDrawable.RING

val gradient_type_linear = GradientDrawable.LINEAR_GRADIENT
val gradient_type_radial = GradientDrawable.RADIAL_GRADIENT
val gradient_type_sweep = GradientDrawable.SWEEP_GRADIENT

val state_enable = android.R.attr.state_enabled
val state_disable = -android.R.attr.state_enabled
val state_pressed = android.R.attr.state_pressed
val state_unpressed = -android.R.attr.state_pressed
val state_focused = android.R.attr.state_focused
val state_unfocused = -android.R.attr.state_focused
val state_selected = android.R.attr.state_selected
val state_unselected = -android.R.attr.state_selected

val input_type_number = InputType.TYPE_CLASS_NUMBER

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

val Float.dp: Float
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )
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
    var id = hashCode()
    if (this == parent_id) id = 0
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
                        listener(
                            child,
                            getChildAdapterPosition(child),
                            it.x - child.left,
                            it.y - child.top
                        )
                    }
                }
                return false
            }

            override fun onDown(e: MotionEvent?): Boolean {
                return false
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                return false
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
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
inline fun View.onChildViewClick(
    vararg layoutId: String, // the id of the child view of RecyclerView's item
    x: Float, // the x coordinate of click point
    y: Float,// the y coordinate of click point,
    clickAction: ((View?) -> Unit)
) {
    var clickedView: View? = null
    layoutId
        .map { id ->
            find<View>(id)?.let { view ->
                view.getRelativeRectTo(this).also { rect ->
                    if (rect.contains(x.toInt(), y.toInt())) {
                        clickedView = view
                    }
                }
            } ?: Rect()
        }
        .fold(Rect()) { init, rect -> init.apply { union(rect) } }
        .takeIf { it.contains(x.toInt(), y.toInt()) }
        ?.let { clickAction.invoke(clickedView) }
}

/**
 * a new View.OnClickListener which prevents click shaking
 */
fun View.setShakelessClickListener(threshold: Long, onClick: (View) -> Unit) {
    class Click(
        var view: View? = null,
        var clickTime: Long = -1,
        var onClick: ((View?) -> Unit)? = null
    ) {
        fun isShake(click: Click) = abs(clickTime - click.clickTime) < threshold
    }

    val mainScope = MainScope()
    val clickActor = mainScope.actor<Click>(capacity = Channel.UNLIMITED) {
        var preClick: Click = Click()
        for (click in channel) {
            if (!click.isShake(preClick)) {
                click.onClick?.invoke(click.view)
            }
            preClick = click
        }
    }.autoDispose(this)
    setOnClickListener { view ->
        mainScope.launch {
            clickActor.send(
                Click(view, System.currentTimeMillis()) { onClick(view) }
            )
        }.autoDispose(this)
    }
}


/**
 * avoid memory leak for View and activity when activity has finished while coroutine is still running
 */
fun Job.autoDispose(view: View): Job {
    val isAttached =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && view.isAttachedToWindow || view.windowToken != null

    val listener = object : View.OnAttachStateChangeListener {
        override fun onViewDetachedFromWindow(v: View?) {
            cancel()
            v?.removeOnAttachStateChangeListener(this)
        }

        override fun onViewAttachedToWindow(v: View?) = Unit
    }

    view.addOnAttachStateChangeListener(listener)
    invokeOnCompletion {
        view.removeOnAttachStateChangeListener(listener)
    }
    return this
}

/**
 * avoid memory leak
 */
fun <T> SendChannel<T>.autoDispose(view: View): SendChannel<T> {
    val isAttached =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && view.isAttachedToWindow || view.windowToken != null
    val listener = object : View.OnAttachStateChangeListener {
        override fun onViewDetachedFromWindow(v: View?) {
            close()
            v?.removeOnAttachStateChangeListener(this)
        }

        override fun onViewAttachedToWindow(v: View?) = Unit
    }

    view.addOnAttachStateChangeListener(listener)
    invokeOnClose {
        view.removeOnAttachStateChangeListener(listener)
    }
    return this
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

//<editor-fold desc="building helper class">
/**
 * helper attribute for building [GradientDrawable]
 */
inline var GradientDrawable.solid_color: String
    get() {
        return ""
    }
    set(value) {
        setColor(Color.parseColor(value))
    }

inline var GradientDrawable.corner_radius: Int
    get() {
        return -1
    }
    set(value) {
        cornerRadius = value.dp.toFloat()
    }

inline var GradientDrawable.corner_radii: IntArray
    get() {
        return intArrayOf()
    }
    set(value) {
        cornerRadii = value.map { it.dp.toFloat() }.toFloatArray()
    }

inline var GradientDrawable.gradient_colors: List<String>
    get() {
        return emptyList()
    }
    set(value) {
        colors = value.map { Color.parseColor(it) }.toIntArray()
    }

inline var GradientDrawable.padding_start: Int
    get() {
        return -1
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    set(value) {
        val paddingRect = Rect().also { getPadding(it) }
        setPadding(value.dp, paddingRect.top, paddingRect.right, paddingRect.bottom)
    }

inline var GradientDrawable.padding_end: Int
    get() {
        return -1
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    set(value) {
        val paddingRect = Rect().also { getPadding(it) }
        setPadding(paddingRect.left, paddingRect.top, value.dp, paddingRect.bottom)
    }

inline var GradientDrawable.padding_top: Int
    get() {
        return -1
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    set(value) {
        val paddingRect = Rect().also { getPadding(it) }
        setPadding(paddingRect.left, value.dp, paddingRect.right, paddingRect.bottom)
    }

inline var GradientDrawable.padding_bottom: Int
    get() {
        return -1
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    set(value) {
        val paddingRect = Rect().also { getPadding(it) }
        setPadding(paddingRect.left, paddingRect.top, paddingRect.right, value.dp)
    }

inline var GradientDrawable.strokeAttr: Stroke?
    get() {
        return null
    }
    set(value) {
        value?.apply { setStroke(width.dp, Color.parseColor(color), dashWidth.dp, dashGap.dp) }
    }

inline var GradientDrawable.color_state_list: List<Pair<IntArray, String>>
    get() {
        return listOf(intArrayOf() to "#000000")
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    set(value) {
        val states = mutableListOf<IntArray>()
        val colors = mutableListOf<Int>()
        value.forEach { pair ->
            states.add(pair.first)
            colors.add(Color.parseColor(pair.second))
        }
        color = ColorStateList(states.toTypedArray(), colors.toIntArray())
    }

/**
 * helper function for building [GradientDrawable]
 */
inline fun shape(init: GradientDrawable.() -> Unit) = GradientDrawable().apply(init)

/**
 * helper class for set stroke for [GradientDrawable]
 */
data class Stroke(
    var width: Int = 0,
    var color: String = "#000000",
    var dashWidth: Float = 0f,
    var dashGap: Float = 0f
)

//</editor-fold>
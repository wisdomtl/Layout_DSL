package taylor.com.dsl

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.*
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.helper.widget.Layer
import androidx.constraintlayout.widget.Barrier
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

/**
 * the extension functions and field in this file help you to build layout dynamically,
 * which has a better performance than xml files and more readable than normal java and kotlin code
 *
 * using this dsl to build view in kotlin like the following:
 * private val rootView by lazy {
 *   ConstraintLayout {
 *      layout_width = match_parent
 *      layout_height = match_parent
 *
 *       ImageView {
 *          layout_id = "ivBack"
 *          layout_width = 40
 *          layout_height = 40
 *          margin_start = 20
 *          margin_top = 20
 *          src = R.drawable.ic_back_black
 *          start_toStartOf = parent_id
 *          top_toTopOf = parent_id
 *          onClick = onBackClick
 *       }
 *
 *       TextView {
 *          layout_width = wrap_content
 *          layout_height = wrap_content
 *          text = "commit"
 *          textSize = 30f
 *          layout_visibility = gone
 *          textStyle = bold
 *          align_vertical_to = "ivBack"
 *          center_horizontal = true
 *       }
 *   }
 * }
 */


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
        if (style != null) AppCompatTextView(ContextThemeWrapper(context, style))
        else AppCompatTextView(context)
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
 * create [Barrier] instance within a [ConstraintLayout]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [Barrier] into [ConstraintLayout] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ConstraintLayout.Barrier(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: Barrier.() -> Unit
): Barrier {
    val barrier =
        if (style != null) Barrier(
            ContextThemeWrapper(context, style)
        ) else Barrier(context)
    return barrier.apply(init).also { if (autoAdd) addView(it) }
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


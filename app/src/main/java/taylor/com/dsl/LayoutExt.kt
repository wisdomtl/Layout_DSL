package taylor.com.dsl

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import taylor.com.views.Selector
import taylor.com.views.LineFeedLayout
import taylor.com.views.PercentLayout
import taylor.com.views.ProgressBar


/**
 * layout dsl for customized view
 */
inline fun ViewGroup.LineFeedLayout(autoAdd: Boolean = true, init: LineFeedLayout.() -> Unit) =
    LineFeedLayout(context).apply(init).also { if (autoAdd) addView(it) }

inline fun Context.LineFeedLayout(init: LineFeedLayout.() -> Unit): LineFeedLayout =
    LineFeedLayout(this).apply(init)

inline fun Fragment.LineFeedLayout(init: LineFeedLayout.() -> Unit) =
    context?.let { LineFeedLayout(it).apply(init) }

inline fun ViewGroup.Selector(autoAdd: Boolean = true, init: Selector.() -> Unit) =
    Selector(context).apply(init).also { addView(it) }

inline fun Context.Selector(init: Selector.() -> Unit): Selector =
    Selector(this).apply(init)

inline fun Fragment.Selector(init: Selector.() -> Unit) =
    context?.let { Selector(it).apply(init) }

inline fun ViewGroup.ProgressBar(autoAdd: Boolean = true, init: ProgressBar.() -> Unit) =
    ProgressBar(context).apply(init).also { if (autoAdd) addView(it) }

inline fun Context.ProgressBar(init: ProgressBar.() -> Unit): ProgressBar =
    ProgressBar(this).apply(init)

inline fun Fragment.ProgressBar(init: ProgressBar.() -> Unit) =
    context?.let { ProgressBar(it).apply(init) }

/**
 * create [PercentLayout] instance within a [ViewGroup]
 * @param style an style int value defined in xml
 * @param autoAdd whether add [PercentLayout] into [ViewGroup] automatically
 * @param init set attributes for this view in this lambda
 */
inline fun ViewGroup.PercentLayout(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: PercentLayout.() -> Unit
): PercentLayout {
    val percentLayout =
        if (style != null) PercentLayout(
            ContextThemeWrapper(context, style)
        ) else PercentLayout(context)
    return percentLayout.apply(init).also { if (autoAdd) addView(it) }
}

/**
 * create [PercentLayout] instance
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Context.PercentLayout(style: Int? = null, init: PercentLayout.() -> Unit): PercentLayout {
    val percentLayout =
        if (style != null) PercentLayout(
            ContextThemeWrapper(this, style)
        ) else PercentLayout(this)
    return percentLayout.apply(init)
}

/**
 * create [PercentLayout] instance within [Fragment]
 * @param style an style int value defined in xml
 * @param init set attributes for this view in this lambda
 */
inline fun Fragment.PercentLayout(
    style: Int? = null,
    init: PercentLayout.() -> Unit
): PercentLayout? = context?.let {
    if (style != null) PercentLayout(
        ContextThemeWrapper(it, style)
    ) else PercentLayout(it)
}?.apply(init)

inline var LineFeedLayout.horizontal_gap: Int
    get() {
        return -1
    }
    set(value) {
        horizontalGap = value.dp
    }

inline var LineFeedLayout.vertical_gap: Int
    get() {
        return -1
    }
    set(value) {
        verticalGap = value.dp
    }


inline var View.left_percent: Float
    get() {
        return -1f
    }
    set(value) {
        updateLayoutParams<PercentLayout.LayoutParam> {
            leftPercent = value
        }
    }

inline var View.top_percent: Float
    get() {
        return -1f
    }
    set(value) {
        updateLayoutParams<PercentLayout.LayoutParam> {
            topPercent = value
        }
    }

inline var View.start_to_start_of_percent: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<PercentLayout.LayoutParam> {
            startToStartOf = value.toLayoutId()
        }
    }

inline var View.start_to_end_of_percent: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<PercentLayout.LayoutParam> {
            startToEndOf = value.toLayoutId()
        }
    }

inline var View.end_to_end_of_percent: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<PercentLayout.LayoutParam> {
            endToEndOf = value.toLayoutId()
        }
    }

inline var View.end_to_start_of_percent: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<PercentLayout.LayoutParam> {
            endToStartOf = value.toLayoutId()
        }
    }

inline var View.top_to_top_of_percent: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<PercentLayout.LayoutParam> {
            topToTopOf = value.toLayoutId()
        }
    }

inline var View.top_to_bottom_of_percent: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<PercentLayout.LayoutParam> {
            topToBottomOf = value.toLayoutId()
        }
    }

inline var View.bottom_to_bottom_of_percent: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<PercentLayout.LayoutParam> {
            bottomToBottomOf = value.toLayoutId()
        }
    }

inline var View.bottom_to_top_of_percent: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<PercentLayout.LayoutParam> {
            bottomToTopOf = value.toLayoutId()
        }
    }

inline var View.center_vertical_of_percent: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<PercentLayout.LayoutParam> {
            centerVerticalOf = value.toLayoutId()
        }
    }

inline var View.center_horizontal_of_percent: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<PercentLayout.LayoutParam> {
            centerHorizontalOf = value.toLayoutId()
        }
    }

package taylor.com.dsl

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import taylor.com.views.Selector
import taylor.com.views.LineFeedLayout
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
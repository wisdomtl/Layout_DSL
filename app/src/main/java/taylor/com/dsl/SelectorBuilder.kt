package taylor.com.dsl

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable

/**
 * helper function for building [StateListDrawable]
 */
inline fun selector(init: StateListDrawable.() -> Unit) = StateListDrawable().apply(init)

inline var StateListDrawable.items: Map<IntArray, Drawable>
    get() {
        return emptyMap()
    }
    set(value) {
        value.forEach { entry -> addState(entry.key, entry.value) }
    }
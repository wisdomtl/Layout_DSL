package taylor.com.dsl

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.os.Build
import androidx.annotation.RequiresApi

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

/**
 * color res should be returned by ContextCompat.getColor()
 */
inline var GradientDrawable.solid_color_res: Int
    get() {
        return -1
    }
    set(value) {
        setColor(value)
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

/**
 * color res should be returned by ContextCompat.getColor()
 */
inline var GradientDrawable.gradient_colors_res: List<Int>
    get() {
        return emptyList()
    }
    set(value) {
        colors = value.toIntArray()
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

inline var GradientDrawable.strokeAttr_res: Stroke?
    get() {
        return null
    }
    set(value) {
        value?.apply { setStroke(width.dp, color_res, dashWidth.dp, dashGap.dp) }
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
 * color res should be returned by ContextCompat.getColor()
 */
inline var GradientDrawable.color_state_list_res: List<Pair<IntArray, Int>>
    get() {
        return listOf(intArrayOf() to 0)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    set(value) {
        val states = mutableListOf<IntArray>()
        val colors = mutableListOf<Int>()
        value.forEach { pair ->
            states.add(pair.first)
            colors.add(pair.second)
        }
        color = ColorStateList(states.toTypedArray(), colors.toIntArray())
    }

/**
 * helper function for building [GradientDrawable]
 */
inline fun shape(init: GradientDrawable.() -> Unit) = GradientDrawable().apply(init)

/**
 * helper class for set stroke for [GradientDrawable]
 * color res should be returned by ContextCompat.getColor()
 */
data class Stroke(
    var width: Number = 0f,
    var color: String = "#000000",
    var color_res:Int = 0,
    var dashWidth: Float = 0f,
    var dashGap: Float = 0f
)

package taylor.com.dsl

import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.text.InputType
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.Barrier
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintProperties

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

val barrier_left = Barrier.LEFT
val barrier_top = Barrier.TOP
val barrier_right = Barrier.RIGHT
val barrier_bottom = Barrier.BOTTOM
val barrier_start = Barrier.START
val barrier_end = Barrier.END

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

val wrap_mode_chain = Flow.WRAP_CHAIN
val wrap_mode_none = Flow.WRAP_NONE
val wrap_mode_aligned = Flow.WRAP_ALIGNED

val ellipsize_end = TextUtils.TruncateAt.END
val ellipsize_marquee = TextUtils.TruncateAt.MARQUEE
val ellipsize_middle = TextUtils.TruncateAt.MIDDLE
val ellipsize_start = TextUtils.TruncateAt.START

val parent_id = "0"

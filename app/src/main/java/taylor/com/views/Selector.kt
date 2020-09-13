package taylor.com.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout

open class Selector @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {
    /**
     * the unique identifier for a [Selector]
     */
    var tag: String = "default tag"

    /**
     * the identifier for the [SelectorGroup] this [Selector] belongs to
     */
    var groupTag: String = "default group tag"

    /**
     * the [SelectorGroup] this [Selector] belongs to
     */
    var group: SelectorGroup? = null

    /**
     * the layout view for this [Selector]
     */
    var contentView: View? = null
        set(value) {
            field = value
            value?.let {
                addView(it, LayoutParams(MATCH_PARENT, MATCH_PARENT))
                setOnClickListener {
                    group?.onSelectorClick(this)
                }
            }
        }

    /**
     * it will be invoked when the selection state of this [Selector] has changed,
     * override it if you want customized effect of selected or unselected
     */
    var onStateChange: ((Selector, Boolean) -> Unit)? = null

    init {
        contentView?.let {
            addView(it, LayoutParams(MATCH_PARENT, MATCH_PARENT))
            setOnClickListener {
                group?.onSelectorClick(this)
            }
        }
    }

    override fun setSelected(selected: Boolean) {
        val isPreSelected = isSelected
        super.setSelected(selected)
        if (isPreSelected != selected) {
            onStateChange?.invoke(this, selected)
        }
    }
}


package taylor.com.views


class SelectorGroup {

    companion object {
        /**
         * single choice mode, previous [Selector] will be unselected if a new one is selected
         */
        var MODE_SINGLE = { selectorGroup: SelectorGroup, selector: Selector ->
            selectorGroup.run {
                find(selector.groupTag)?.let { setSelected(it, false) }
                setSelected(selector, true)
            }
        }

        /**
         * multiple choice mode, several [Selector] could be selected in one [SelectorGroup]
         */
        var MODE_MULTIPLE = { selectorGroup: SelectorGroup, selector: Selector ->
            selectorGroup.setSelected(selector, !selector.isSelected)
        }
    }

    /**
     * the selected [Selector]s in this [SelectorGroup]
     */
    private var selectors = mutableListOf<Selector>()

    /**
     * the choice mode of this [SelectorGroup], there are two default choice mode, which is [singleMode] and [multipleMode]
     */
    var choiceMode: ((SelectorGroup, Selector) -> Unit)? = null


    var selectChangeListener: ((List<Selector>, Boolean) -> Unit)? = null

    fun onSelectorClick(selector: Selector) {
        choiceMode?.invoke(this, selector)
    }

    fun find(groupTag: String) = selectors.find { it.groupTag == groupTag }

    fun setSelected(selector: Selector, select: Boolean) {
        if (select) selectors.add(selector) else selectors.remove(selector)
        selector.isSelected = select
        if (select) {
            selectChangeListener?.invoke(selectors, select)
        } else {
            selectChangeListener?.invoke(listOf(selector), select)
        }
    }
}
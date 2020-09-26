# Layout_DSL
Build Android layout dynamically with kotlin, get rid of xml file, which has poor performance


```kotlin
class MainActivity : AppCompatActivity() {
    // build layout dynamically by DSL
    private val contentView by lazy {
        ConstraintLayout {
            layout_width = match_parent
            layout_height = match_parent

            TextView {
                layout_width = wrap_content
                layout_height = wrap_content
                text = "commit"
                textSize = 30f
                gravity = gravity_center
                center_horizontal = true
                top_toTopOf = parent_id
                padding = 10
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set layout to content view
        setContentView(contentView)
    }
}
```

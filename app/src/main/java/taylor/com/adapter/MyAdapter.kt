package taylor.com.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import taylor.com.bean.User
import taylor.com.dsl.*

/**
 * show how use layout dsl in [RecyclerView.Adapter]
 */
class MyAdapter(var myBean: List<User>?) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = parent.context.run {
            ConstraintLayout {
                layout_height = 90
                layout_width = match_parent
                margin_end = 20
                margin_start =20
                background_color = "#eeeeee"

                TextView {
                    layout_id = "tvContent"
                    layout_width = match_parent
                    layout_height = wrap_content
                    textSize = 15f
                    gravity = gravity_center
                    start_toStartOf = parent_id
                    top_toTopOf = parent_id
                }

                View {
                    layout_id = "vDivider"
                    layout_width = match_parent
                    layout_height = 1
                    top_toBottomOf = "tvContent"
                    background_color = "#888888"
                }

                TextView {
                    layout_id = "tvStart"
                    layout_width = wrap_content
                    layout_height = wrap_content
                    textSize = 26f
                    textColor ="#3F4658"
                    text = "start"
                    start_toStartOf = parent_id
                    top_toBottomOf = "vDivider"
                    margin_top = 20
                }

                TextView {
                    layout_id = "tvEnd"
                    layout_width = wrap_content
                    layout_height = wrap_content
                    textSize = 26f
                    textColor ="#3F4658"
                    text = "end"
                    end_toEndOf = parent_id
                    align_vertical_to = "tvStart"
                }


            }
        }
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return myBean?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        myBean?.get(position)?.let { holder.bind(it) }
    }
}
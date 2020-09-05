package taylor.com.views

import android.view.ViewGroup
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.RecyclerView
import taylor.com.bean.User
import taylor.com.dsl.*
import taylor.com.layout_dsl.R
import test.taylor.com.taylorcode.kotlin.override_property.MyViewHolder

class MyAdapter(var myBean: List<User>?) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = parent.context.run {
            ConstraintLayout {
                layout_height = 90
                layout_width = match_parent
                margin_end = 20
                margin_start = 20
                padding_start = 20
                padding_end = 20
                padding_top = 10
                padding_bottom =10

                View {
                    layout_width = match_parent
                    layout_height = 0
                    center_vertical = true
                    center_horizontal = true
                    background_res = R.drawable.bg_item
                }

                TextView {
                    layout_id = "tvContent"
                    layout_width = wrap_content
                    layout_height = wrap_content
                    textSize = 15f
                    gravity = gravity_center
                    start_toStartOf = parent_id
                    top_toTopOf = parent_id
                    margin_top = 10
                }

                View {
                    layout_id = "vDivider"
                    layout_width = match_parent
                    layout_height = 2
                    top_toBottomOf = "tvContent"
                    background_color = "#888888"
                    margin_top = 5
                }

                TextView {
                    layout_id = "tvChatroom"
                    layout_width = wrap_content
                    layout_height = wrap_content
                    textSize = 15f
                    top_toBottomOf = "vDivider"
                    start_toStartOf = parent_id
                    text = "chat room"
                }

                TextView {
                    layout_id = "tvNumber"
                    layout_width = wrap_content
                    layout_height = wrap_content
                    textSize = 15f
                    align_vertical_to = "tvChatroom"
                    end_toEndOf = parent_id
                    text = "10"
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
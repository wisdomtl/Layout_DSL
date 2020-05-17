package test.taylor.com.taylorcode.kotlin.override_property

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import taylor.com.bean.User
import taylor.com.dsl.*

class MyAdapter(var myBean: List<User>?) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = parent.context.run {
            ConstraintLayout {
                layout_height = 60
                layout_width = match_parent
                margin_end = 20
                margin_start =20
                TextView {
                    layout_id = "tvContent"
                    layout_width = match_parent
                    layout_height = 40
                    textSize = 20f
                    gravity = gravity_center
                    center_horizontal = true
                    center_vertical = true
                }

                View {
                    layout_id = "vDivider"
                    layout_width = match_parent
                    layout_height = 1
                    background_color = "#ffffff"
                    bottom_toBottomOf =  parent_id
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
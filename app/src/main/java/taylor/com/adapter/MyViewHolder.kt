package taylor.com.adapter

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import taylor.com.bean.User
import taylor.com.dsl.find

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User?) {
        itemView.apply {
            find<TextView>("tvContent")?.apply {
                text = user?.name ?: "no name"
                val color = if (user?.gender == 1) Color.parseColor("#b300ff00") else Color.parseColor("#b3ff00ff")
                setBackgroundColor(color)
            }
        }
    }
}
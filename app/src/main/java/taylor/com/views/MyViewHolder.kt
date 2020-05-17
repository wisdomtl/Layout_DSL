package test.taylor.com.taylorcode.kotlin.override_property

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import taylor.com.dsl.find

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(myBean: MyBean?) {
        itemView.apply {
            find<TextView>("tvContent")?.text = myBean?.name ?: "no name"
        }
    }
}
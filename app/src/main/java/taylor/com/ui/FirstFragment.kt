package taylor.com.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import taylor.com.adapter.MyAdapter
import taylor.com.bean.User
import taylor.com.dsl.*

/**
 * show how to use layout dsl in [Fragment]
 */
class FirstFragment : Fragment() {

    private val diamondUrl =
        "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2571315283,182922750&fm=26&gp=0.jpg"
    private val coinUrl =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1589731033455&di=4266c84e59efb61a7f82c71b305954db&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F23%2F31%2F57%2F591be2a6807c3.png"

    private val nameLiveData = MutableLiveData<CharSequence>()
    private val nameColorLiveData = MutableLiveData<String>()
    private val avatarLiveData = MutableLiveData<Bitmap>()
    private val commitLiveData = MutableLiveData<Boolean>()
    private val contentLiveData = MutableLiveData<Boolean>().apply { value = false }

    private lateinit var rv: RecyclerView

    private val target = object : SimpleTarget<Bitmap>() {
        override fun onResourceReady(
            resource: Bitmap?,
            glideAnimation: GlideAnimation<in Bitmap>?
        ) {
            avatarLiveData.value = resource
        }
    }

    private val rootView by lazy {
        ConstraintLayout {
            layout_width = match_parent
            layout_height = match_parent

            ImageView {
                layout_id = "ivBack"
                layout_width = 40
                layout_height = 40
                margin_start = 20
                margin_top = 20
                src = R.drawable.ic_back_black
                start_toStartOf = parent_id
                top_toTopOf = parent_id
                onClick = onBackClick
            }

            TextView(R.style.myTextView) {
                layout_id = "tvCommit"
                layout_width = wrap_content
                layout_height = wrap_content
                text = "commit"
                gravity = gravity_center
                textStyle = bold
                align_vertical_to = "ivBack"
                center_horizontal = true
                padding = 10
                onClick = { _: View ->
                    Toast.makeText(context, "on click", Toast.LENGTH_LONG).show()
                }
                shape = shape {
                    corner_radius = 10
                    shape = shape_rectangle
                    gradientType = gradient_type_linear
                    orientation = gradient_left_right
                    gradient_colors = listOf("#ffff00", "#0000ff")
                    strokeAttr = Stroke(5, "#000000", dashWidth = 2f, dashGap = 3f)
                    color_state_list = listOf(
                        intArrayOf(state_enable) to "#007EFF",
                        intArrayOf(state_disable) to "#FDB2DA"
                    )
                }
                bindLiveData = liveDataBinder(commitLiveData) {
                    action = {
                        (it as? Boolean)?.let {
                            isEnabled = it
                        }
                    }
                }
            }

            ImageView {
                layout_width = 40
                layout_height = 40
                src = R.drawable.ic_member_more
                align_vertical_to = "ivBack"
                end_toEndOf = parent_id
                margin_end = 20
            }

            View {
                layout_id = "vDivider"
                layout_width = match_parent
                layout_height = 1
                margin_top = 10
                background_color = "#eeeeee"
                top_toBottomOf = "tvCommit"
            }

            Layer {
                layout_id = "layer"
                layout_width = wrap_content
                layout_height = wrap_content
                referenceIds = "ivDiamond,tvName,tvContent,ivAvatar,tvTime,tvSub"
                background_res = R.drawable.tag_checked_shape
                start_toStartOf = "ivDiamond"
                top_toTopOf = "ivDiamond"
                bottom_toBottomOf = "tvTime"
                end_toEndOf = "tvTime"
            }

            ImageView {
                layout_id = "ivDiamond"
                layout_width = 40
                layout_height = 40
                margin_start = 20
                margin_top = 40
                src = R.drawable.diamond_tag
                start_toStartOf = "ivBack"
                top_toBottomOf = "vDivider"
                margin_top = 10
                onClick = { _: View ->
                    commitLiveData.postValue(true)
                }
            }

            TextView {
                layout_id = "tvName"
                layout_width = wrap_content
                layout_height = wrap_content
                margin_start = 5
                gravity = gravity_center
                padding = 10
                textSize = 20f
                textStyle = bold
                align_vertical_to = "ivDiamond"
                start_toEndOf = "ivDiamond"
                bindLiveData = liveDataBinder(nameLiveData) {
                    action = {
                        text = it as CharSequence
                    }
                }
                bind = Binder("init title") { _, data ->
                    text = data.toString()
                }

                onClick = { _: View ->
                    commitLiveData.postValue(false)
                }

            }

            TextView {
                layout_id = "tvContent"
                layout_width = 0
                layout_height = wrap_content
                margin_top = 5
                text = "The changes were merged into release with so many bugs"
                textSize = 23f
                start_toStartOf = "ivDiamond"
                top_toBottomOf = "ivDiamond"
                end_toStartOf = "ivAvatar"
                bindLiveData = liveDataBinder(contentLiveData) {
                    action = {
                        (it as? Boolean)?.let {
                            isEnabled = it
                        }
                    }
                }
                background_drawable_state_list = listOf(
                    intArrayOf(state_enable) to shape {
                        shape = shape_rectangle
                        corner_radius = 10
                        solid_color = "#FDB2DA"
                    },
                    intArrayOf(state_disable) to shape {
                        shape = shape_rectangle
                        corner_radius = 10
                        solid_color = "#80FDB2DA"
                    }
                )
            }

            ImageView {
                layout_id = "ivAvatar"
                layout_width = 100
                layout_height = 100
                margin_end = 20
                src = R.drawable.user_portrait_gender_female
                end_toEndOf = parent_id
                start_toEndOf = "tvContent"
                top_toTopOf = "tvContent"
                onClick = {
                    contentLiveData.postValue(contentLiveData.value?.not())
                }
            }

            TextView {
                layout_id = "tvSub"
                layout_width = wrap_content
                layout_height = wrap_content
                text = "merge it with mercy"
                textColor = "#c4747E8B"
                textSize = 18f
                start_toStartOf = "ivDiamond"
                top_toBottomOf = "tvContent"
            }

            TextView {
                layout_id = "tvTime"
                layout_width = wrap_content
                layout_height = wrap_content
                margin_top = 20
                text = "2020.04.30"
                end_toEndOf = "ivAvatar"
                top_toBottomOf = "ivAvatar"
            }

            TextView {
                layout_id = "tvCancel"
                layout_width = wrap_content
                layout_height = wrap_content
                margin_end = 30
                background_res = R.drawable.bg_orange_btn
                padding_start = 30
                padding_top = 10
                padding_end = 30
                padding_bottom = 10
                text = "cancel"
                margin_bottom = 20
                textSize = 20f
                textStyle = bold
                bottom_toBottomOf = parent_id
                end_toStartOf = "tvOk"
                start_toStartOf = parent_id
                horizontal_chain_style = packed
                onClick = onCancelClick
            }

            TextView {
                layout_id = "tvOk"
                layout_width = wrap_content
                layout_height = wrap_content
                background_res = R.drawable.bg_orange_btn
                padding_start = 30
                padding_top = 10
                margin_bottom = 20
                padding_end = 30
                padding_bottom = 10
                text = "Ok"
                textSize = 20f
                textStyle = bold
                bottom_toBottomOf = parent_id
                end_toEndOf = parent_id
                horizontal_chain_style = packed
                start_toEndOf = "tvCancel"
            }

            rv = RecyclerView {
                layout_id = "rvTest"
                layout_width = match_parent
                layout_height = wrap_content
                top_toBottomOf = "tvTime"
                bottom_toTopOf = "tvOk"
                onItemClick = onListItemClick
                margin_top = 10
                onItemLongClick = onItemLongClickListener
            }

            EditText {
                layout_width = match_parent
                layout_height = 50
                textSize = 20f
                background_color = "#00ffff"
                top_toBottomOf = "rvTest"
                onTextChange = textWatcher {
                    onTextChanged = { text: CharSequence?, start: Int, count: Int, after: Int ->
                        Log.v("ttaylor", "tag=text change, FirstFragment.()  text=${text}")
                    }
                }
            }
        }
    }

    private val onCancelClick = { v: View ->
        nameLiveData.value = "new title"
        Unit
    }

    private val onItemLongClickListener = { v: View, i: Int, x: Float, y: Float ->
        adapter.myBean?.get(i)?.let {
            Log.v("ttaylor", "on item(${it.name}) long click  ")
        }
        Unit
    }

    private val onListItemClick = { v: View, i: Int, x: Float, y: Float ->
        adapter.myBean?.get(i)?.let {
            nameLiveData.value = SpannableStringBuilder(it.name).apply {
                setSpan(
                    ForegroundColorSpan(Color.RED),
                    0,
                    it.name.indexOf(" "),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
                val color = if (it.gender == 1) "#b300ff00" else "#b3ff00ff"
                setSpan(
                    ForegroundColorSpan(Color.parseColor(color)),
                    it.name.indexOf(" "),
                    it.name.lastIndex + 1,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
            }

            if (it.gender == 1) Glide.with(context).load(diamondUrl).asBitmap().into(target)
            else Glide.with(context).load(coinUrl).asBitmap().into(target)
        }
        v.onChildViewClick("tvStart", "tvEnd", x = x, y = y) {
            Log.v("ttaylor", "tag=adsf, FirstFragment.()  on two child clicked")
        }
        false
    }

    private val onBackClick = { v: View ->
        activity?.finish()
        Unit
    }

    private val users = listOf(
        User("Taylor Swift", 10),
        User("Linda candy", 20, 1),
        User("Cindy json", 30, 1),
        User("Evian Mary", 40, 1)
    )

    private var adapter = MyAdapter(users)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter
    }
}

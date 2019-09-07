package i.part.app.course.todo.board.ui

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import i.part.app.course.todo.R
import i.part.app.course.todo.board.data.Avatar
import i.part.app.course.todo.core.util.ui.PicassoCircleTransformation

//input size is for choose item // true for big and false for small
class AvatarRecyclerAdapter(
    private val context: Context,
    avatars: List<Avatar>,
    picasso: Picasso,
    val inputSize: Boolean
) :
    RecyclerView.Adapter<AvatarRecyclerAdapter.ViewHolder>() {
    private val avatars: List<Avatar>
    lateinit var v: View
    private val picasso: Picasso

    init {
        this.avatars = avatars
        this.picasso = picasso
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (inputSize) {
            v = LayoutInflater.from(parent.context)
                .inflate(i.part.app.course.todo.R.layout.avatar_item_add_board, parent, false)
        } else {
            v = LayoutInflater.from(parent.context)
                .inflate(i.part.app.course.todo.R.layout.avatar_item, parent, false)
        }
        return ViewHolder(v)
    }

    val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
    val Float.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tag = avatars[position]
        val t = avatars[position]
        val trsfrm = PicassoCircleTransformation()
        picasso.load(t.image_url).transform(trsfrm).error(R.drawable.person_empty)
            .into(holder.avatarImageView)

        //holder.avatarImageView.setBackgroundResource(R.drawable.ic_avatar)
    }

    override fun getItemCount(): Int {
        return avatars.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var avatarImageView: ImageView

        init {
            avatarImageView = itemView.findViewById<View>(R.id.avatarImageView) as ImageView
            itemView.setOnClickListener { view ->
                val myAvatar = view.tag as Avatar
                Toast.makeText(view.context, myAvatar.image_url, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}

package i.part.app.course.todo.features.board.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import i.part.app.course.todo.R
import i.part.app.course.todo.core.util.ui.OverlapDecoration
import java.util.*

class AddTaskFragment : DialogFragment() {
    var avatarManager: RecyclerView.LayoutManager? = null
    var avatarAdapter: RecyclerView.Adapter<*>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setTitle("Add BoardDetailFragment")
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)
        val closeButton = view.findViewById<ImageButton>(R.id.ib_add_task_close)
        closeButton.setOnClickListener { this.dismiss() }

        view.findViewById<MaterialButton>(R.id.btn_add_task_confirm)?.let {
            it.setOnClickListener {
                this.findNavController().navigate(R.id.action_addTaskFragment_to_board)
                this.dismiss()
            }
        }

        view.findViewById<ImageButton>(R.id.ib_add_task_plus)?.let {
            it.setOnClickListener {
                val fragmentType: String? = "add_task"
                val myBundle = bundleOf("fragmentType" to fragmentType)
                this.findNavController()
                    .navigate(R.id.action_addTaskFragment_to_addMember2, myBundle)
            }
        }
        val rv_avatar = view.findViewById<RecyclerView>(R.id.rv_add_task_avatars)
        val myAvatarViews: ArrayList<AvatarView> = ArrayList()
        rv_avatar.let { it.setHasFixedSize(true) }
        val overlap: OverlapDecoration = OverlapDecoration()
        rv_avatar.addItemDecoration(overlap)
        avatarManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        rv_avatar.let { it.layoutManager = avatarManager }

        val fakeLink: String =
            "https://www.shareicon.net/download/2016/05/24/770136_man_512x512.png"
        myAvatarViews.add(AvatarView(fakeLink))
        myAvatarViews.add(AvatarView(fakeLink))
        myAvatarViews.add(AvatarView(fakeLink))
        myAvatarViews.add(AvatarView(fakeLink))
        myAvatarViews.add(AvatarView(fakeLink))
        myAvatarViews.add(AvatarView(fakeLink))
        val picasso = Picasso.get()
        context?.let { avatarAdapter = AvatarRecyclerAdapter(it, myAvatarViews, picasso, true) }
        rv_avatar.let { it.adapter = avatarAdapter }
        return view
    }
}

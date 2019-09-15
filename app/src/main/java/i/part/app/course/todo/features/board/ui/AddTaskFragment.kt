package i.part.app.course.todo.features.board.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import i.part.app.course.todo.R
import i.part.app.course.todo.core.util.ui.OverlapDecoration
import i.part.app.course.todo.databinding.DialogAddTaskBinding
import kotlinx.android.synthetic.main.dialog_add_task.*
import java.util.*

class AddTaskFragment : DialogFragment() {
    var avatarManager: RecyclerView.LayoutManager? = null
    var avatarAdapter: RecyclerView.Adapter<*>? = null
    lateinit var binding: DialogAddTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_task, container, false)
        val view = binding.root
        binding.lifecycleOwner = this

        dialog?.setTitle("Add BoardDetailFragment")
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ib_add_task_close.setOnClickListener { this.dismiss() }

        btn_add_task_confirm.setOnClickListener {
            this.findNavController().navigate(R.id.action_addTaskFragment_to_board)
            this.dismiss()
        }


        ib_add_task_plus.setOnClickListener {
            val fragmentType: String? = "add_task"
            val myBundle = bundleOf("fragmentType" to fragmentType)
            this.findNavController()
                .navigate(R.id.action_addTaskFragment_to_addMember2, myBundle)
        }

        val myAvatarViews: ArrayList<AvatarView> = ArrayList()
        rv_add_task_avatars.let { it.setHasFixedSize(true) }
        val overlap: OverlapDecoration = OverlapDecoration()
        rv_add_task_avatars.addItemDecoration(overlap)
        avatarManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        rv_add_task_avatars.let { it.layoutManager = avatarManager }

        val fakeLink: String =
            "https://www.shareicon.net/download/2016/05/24/770136_man_512x512.png"
        myAvatarViews.add(AvatarView(fakeLink))
        myAvatarViews.add(AvatarView(fakeLink))
        myAvatarViews.add(AvatarView(fakeLink))
        myAvatarViews.add(AvatarView(fakeLink))
        myAvatarViews.add(AvatarView(fakeLink))
        myAvatarViews.add(AvatarView(fakeLink))
        val picasso = Picasso.get()
        context?.let { avatarAdapter = AvatarRecyclerAdapter(myAvatarViews, picasso, true) }
        rv_add_task_avatars.let { it.adapter = avatarAdapter }

        binding.ownerName = "HosseiN Bakand"
    }
}

package i.part.app.course.todo.board.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import i.part.app.course.todo.R
import i.part.app.course.todo.board.data.Avatar
import i.part.app.course.todo.core.util.ui.OverlapDecoration
import i.part.app.course.todo.core.util.ui.RoundedCornersTransformation
import kotlinx.android.synthetic.main.fragment_edit_board.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Edit_board : DialogFragment() {
    var avatarManager: RecyclerView.LayoutManager? = null
    var avatarAdapter: RecyclerView.Adapter<*>? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_edge)
        dialog?.setCanceledOnTouchOutside(false)
        val view = inflater.inflate(R.layout.fragment_edit_board, container, false)
        val closeButton = view.findViewById<ImageButton>(R.id.editBoardCloseButton)
        closeButton.setOnClickListener {
            this.dismiss()
        }


        view?.let {
            it.findViewById<ImageButton>(R.id.editBoardPlusButton)?.setOnClickListener {
                this.findNavController().navigate(R.id.action_edit_board_to_addMember2)
            }
        }
        view?.let {
            it.findViewById<MaterialButton>(R.id.btn_add_task_confirm)?.setOnClickListener {
                this.findNavController().navigate(R.id.action_edit_board_to_board)
            }
        }
        //recycle
        val rv_avatar = view.findViewById<RecyclerView>(R.id.avatarsRecyclerView2)
        val myAvatars: ArrayList<Avatar> = ArrayList()

        rv_avatar.let { it.setHasFixedSize(true) }
        val overlap: OverlapDecoration = OverlapDecoration()
        rv_avatar.addItemDecoration(overlap)
        avatarManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        rv_avatar.let { it.layoutManager = avatarManager }

        //start generating fake data
        myAvatars.add(Avatar())
        myAvatars.add(Avatar())
        myAvatars.add(Avatar())
        myAvatars.add(Avatar())
        myAvatars.add(Avatar())
        myAvatars.add(Avatar())
        //end
        val picasso = Picasso.get()
        context?.let { avatarAdapter = AvatarRecyclerAdapter(it, myAvatars, picasso, true) }


        val image = view.findViewById<ImageView>(R.id.editBoardBGImage)
        val url =
            "https://img.freepik.com/free-vector/colorful-watercolor-background_79603-99.jpg?size=626&ext=jpg"
        val radius = 8
        val margin = 0
        val transformation = RoundedCornersTransformation(radius, margin)
        picasso.load(url).transform(transformation).error(R.drawable.person_empty)
            .fit().into(image)
        rv_avatar.let { it.adapter = avatarAdapter }

        val editText = view.findViewById<EditText>(R.id.boardNameEditText)
        editText.setSelection(0, editText.text.toString().length)
        return view
    }

    companion object {

        fun newInstance(title: String): Edit_board {
            val frag = Edit_board()
            val args = Bundle()

            args.putString("Add BoardDetailFragment", title)
            frag.arguments = args
            return frag
        }
    }
}

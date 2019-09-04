package i.part.app.course.todo.board.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import i.part.app.course.todo.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddMember2 : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var list: ArrayList<AddMember2Item>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_member_2, container, false)

        recyclerView = view.findViewById(R.id.addMember2_nowMember) as RecyclerView
        recyclerView.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = layoutManager

        list = ArrayList()

        //Adding Data into ArrayList
        val url =
            "https://www.irreverentgent.com/wp-content/uploads/2018/03/Awesome-Profile-Pictures-for-Guys-look-away2.jpg"
        list.add(AddMember2Item(url, "Project Manager"))
        list.add(AddMember2Item("Bradley Matthews", "Senior Developer"))
        list.add(AddMember2Item(url, "Lead Developer"))
        list.add(AddMember2Item("Gary Thompson", "Lead Developer"))
        list.add(AddMember2Item("Corey Williamson", "UI/UX Developer"))
        list.add(AddMember2Item("Samuel Jones", "Front-End Developer"))
        list.add(AddMember2Item("Michael Read", "Backend Developer"))
        list.add(AddMember2Item("Robert Phillips", "Android Developer"))
        list.add(AddMember2Item("Albert Stewart", "Web Developer"))
        list.add(AddMember2Item("Wayne Diaz", "Junior Devafdsgeloper"))
        list.add(AddMember2Item("Todd Miller", "Project Manageafdr"))
        list.add(AddMember2Item("Bradley Matthews", "Senior Devefaloper"))
        list.add(AddMember2Item("Harley Gibson", "Lead Developfaer"))
        list.add(AddMember2Item("Gary Thompson", "Lead Developedfr"))
        list.add(AddMember2Item("Corey Williamson", "UI/UX Develoabper"))
        list.add(AddMember2Item("Samuel Jones", "Front-End Developeafr"))
        list.add(AddMember2Item("Michael Read", "Backend Developxcvxcber"))
        list.add(AddMember2Item("Robert Phillips", "Android Developeiyl,r"))
        list.add(AddMember2Item("Albert Stewart", "Web Developesadgadsr"))
        list.add(AddMember2Item("Wayne Diaz", "Junior Developerasfbafs"))
        list.add(AddMember2Item("Todd Miller", "Project Manageradva"))
        list.add(AddMember2Item("Bradley Matthews", "Senior Developsdgsder"))
        list.add(AddMember2Item("Harley Gibson", "Lead Developer"))
        list.add(AddMember2Item("Gary Thompson", "Lead Developer"))
        list.add(AddMember2Item("Corey Williamson", "UI/UX Developer"))
        list.add(AddMember2Item("Samuel Jones", "Front-End Developer"))
        list.add(AddMember2Item("Michael Read", "Backend Developer"))
        list.add(AddMember2Item("Robert Phillips", "Android Developer"))
        list.add(AddMember2Item("Albert Stewart", "Web Developer"))
        list.add(AddMember2Item("Wayne Diaz", "Junior Developer"))
        list.add(AddMember2Item("Todd Miller", "Project Manager"))
        list.add(AddMember2Item("Bradley Matthews", "Senior Developer"))
        list.add(AddMember2Item("Harley Gibson", "Lead Developer"))
        list.add(AddMember2Item("Gary Thompson", "Lead Developer"))
        list.add(AddMember2Item("Corey Williamson", "UI/UX Developer"))
        list.add(AddMember2Item("Samuel Jones", "Front-End Developer"))
        list.add(AddMember2Item("Michael Read", "Backend Developer"))
        list.add(AddMember2Item("Robert Phillips", "Android Developer"))
        list.add(AddMember2Item("Albert Stewart", "Web Developer"))
        list.add(AddMember2Item("Wayne Diaz", "Junior Developer"))
        context?.let {
            mAdapter = AddMember2Adapter(it, list)
        }
        recyclerView.adapter = mAdapter
        val button = view.findViewById<Button>(R.id.b_add_member)
        button.setOnClickListener {
            val fm = fragmentManager
            val editNameDialogFragment = AddMember3Fragment.newInstance()
            fm?.let {
                editNameDialogFragment.show(it, "fragment_edit_name")
            }
        }
        val toolbar = view.findViewById<MaterialToolbar>(R.id.addMember2_toolbar)


        toolbar.setNavigationOnClickListener(View.OnClickListener {
            Toast.makeText(
                view.context,
                "---back---",
                Toast.LENGTH_SHORT
            ).show()

        })

        return view
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddMember2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

package com.example.task_046

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess
import com.example.task_046.databinding.FragmentEmailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [emailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class emailFragment : Fragment() {

    private val emailes = mutableListOf(
        EmailViewModal(R.drawable.email_ic, "aaromanets1980@gmail.com"),
        EmailViewModal(R.drawable.email_ic, "ssromanets1980@gmail.com"),
        EmailViewModal(R.drawable.email_ic, "a.romanets@yandex.ru"),
        EmailViewModal(R.drawable.email_ic, "san-romanets@yandex.ru"),
        EmailViewModal(R.drawable.email_ic, "AANN-romanets@yandex.ru"),
    )

    private var _binding: FragmentEmailBinding? = null
    private val binding get() = _binding!!

    private var adapter: CustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEmailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, saveInstanceState: Bundle?) {
        super.onViewCreated(view, saveInstanceState)

        val toolBarEmail = binding.toolBarEmail
        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.setSupportActionBar(toolBarEmail)

        binding.recyclerViewRV.layoutManager = LinearLayoutManager(context)
        adapter = CustomAdapter(emailes)
        binding.recyclerViewRV.adapter = adapter
        binding.recyclerViewRV.setHasFixedSize(true)

        binding.backBTN.setOnClickListener{
            view.findNavController().navigate(R.id.action_emailFragment_to_signUpFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain->{
                exitProcess(-1)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
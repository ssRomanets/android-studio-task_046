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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.system.exitProcess
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.example.task_046.databinding.FragmentContactBinding

/**
 * A simple [Fragment] subclass.
 * Use the [emailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class contactFragment : Fragment() {
    private var _binding: FragmentContactBinding? = null
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
        _binding = FragmentContactBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, saveInstanceState: Bundle?) {
        super.onViewCreated(view, saveInstanceState)

        val toolBarEmail = binding.toolBarContact
        val nameET = binding.nameET
        val telephoneET  = binding.telephoneET
        binding.recyclerViewRV.layoutManager = LinearLayoutManager(context)

        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.setSupportActionBar(toolBarEmail)

        binding.backBTN.setOnClickListener{
            view.findNavController().navigate(R.id.action_contactFragment_to_signUpFragment)
        }

        binding.saveBTN.setOnClickListener{
            val name = nameET.text.toString()
            val telephone  = telephoneET.text.toString()
            if (name.isBlank() || telephone.isBlank()) return@setOnClickListener
            val contactAdd = ContactViewModal(name, telephone)
            addContact(contactAdd)
            with(binding) {
                nameET.text.clear()
                telephoneET.text.clear()
            }
            readContacts()
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

    private fun addContact(contactAdd: ContactViewModal) {
        val id = FirebaseAuth.getInstance().currentUser!!.uid
        val database = Firebase.database.reference.child("contacts").child(id)
        val map: HashMap<String, ContactViewModal> = HashMap()
        map[contactAdd.name.toString()] = contactAdd
        database.updateChildren(map as Map<String, Any>)
    }

    private fun readContacts() {
        val id = FirebaseAuth.getInstance().currentUser!!.uid
        Firebase.database.reference.child("contacts")
            .child(id).get().addOnSuccessListener{
                val listContacts = mutableListOf<ContactViewModal>()
                for (element in it.children) {
                    val contact: ContactViewModal= element.getValue(ContactViewModal::class.java)!!
                    listContacts.add(contact)
                }
                adapter = CustomAdapter(listContacts)
                binding.recyclerViewRV.adapter = adapter
                binding.recyclerViewRV.setHasFixedSize(true)
            }
    }
}
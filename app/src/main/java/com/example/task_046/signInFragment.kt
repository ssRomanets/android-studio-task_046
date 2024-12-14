package com.example.task_046

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.task_046.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass.
 * Use the [signInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class signInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, saveInstanceState: Bundle?) {
        super.onViewCreated(view, saveInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener{
            login(view)
        }
        binding.RedirectSignUpTV.setOnClickListener{
            view.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun login(view: View,) {
        val email = binding.emailET.text.toString()
        val pass = binding.passwordET.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(activity as AppCompatActivity) {
            if (it.isSuccessful) {
                Toast.makeText(context, "Успешно вошел в систему", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_signInFragment_to_emailFragment)
            } else {
                Toast.makeText(context, "Не удалось войти в систему", Toast.LENGTH_SHORT).show()
                binding.RedirectSignUpTV.visibility = View.VISIBLE
            }
        }
    }

}
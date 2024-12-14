package com.example.task_046

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.task_046.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.appcompat.app.AppCompatActivity

/**
 * A simple [Fragment] subclass.
 * Use the [signUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class signUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, saveInstanceState: Bundle?) {
        super.onViewCreated(view, saveInstanceState)

        auth = Firebase.auth
        binding.signUpBtn.setOnClickListener{
            signUpUser(view)
        }
        binding.redirectLoginTV.setOnClickListener{
            view.findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    private fun signUpUser(view: View,) {
        val email = binding.emailSignUpET.text.toString()
        val pass  = binding.passwordSignUpET.text.toString()
        val confirmPassword = binding.passwordConfirmSignUpET.text.toString()

        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(context, "Адрес электронной почты и пароль не могут быть пустыми", Toast.LENGTH_SHORT).show()
            return
        }
        if (pass != confirmPassword) {
            Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(activity as AppCompatActivity) {
            if (it.isSuccessful) {
                Toast.makeText(context, "Успешно зарегистрирован", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            } else {
                if (auth.currentUser != null) {
                    Toast.makeText(context, "Пользователь уже существует", Toast.LENGTH_SHORT).show()
                    view.findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                }
                Toast.makeText(context, "Регистрация не прошла", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
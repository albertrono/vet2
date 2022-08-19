package com.example.veterinarymedicinelibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.veterinarymedicinelibrary.databinding.ActivityDashboardadminBinding
import com.example.veterinarymedicinelibrary.databinding.ActivityDashboarduserBinding
import com.google.firebase.auth.FirebaseAuth

class Dashboarduser : AppCompatActivity() {
    private lateinit var binding: ActivityDashboarduserBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDashboarduserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()
        checkUser()


        binding.logoutbtn.setOnClickListener {
            firebaseAuth.signOut()
           startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

    private fun checkUser() {
        val firebaseUser=firebaseAuth.currentUser
        if (firebaseUser == null){
           binding.subtitleIv.text="not logged in"
        }else{
            val email=firebaseUser.email
            binding.subtitleIv.text=email
        }
    }
}
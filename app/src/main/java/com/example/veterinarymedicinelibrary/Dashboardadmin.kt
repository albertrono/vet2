package com.example.veterinarymedicinelibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.veterinarymedicinelibrary.databinding.ActivityDashboardadminBinding
import com.google.firebase.auth.FirebaseAuth

class Dashboardadmin : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardadminBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDashboardadminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addCategoryBtn.setOnClickListener {
            startActivity(Intent(this,categoryAddActivity::class.java))
        }

        firebaseAuth=FirebaseAuth.getInstance()
        checkUser()
        binding.logoutbtn.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }
    }
    private fun checkUser() {
        val firebaseUser=firebaseAuth.currentUser
        if (firebaseUser == null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }else{
            val email=firebaseUser.email
            binding.subtitleIv.text=email
        }
    }
}
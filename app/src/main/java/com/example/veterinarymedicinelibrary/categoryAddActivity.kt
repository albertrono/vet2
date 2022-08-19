package com.example.veterinarymedicinelibrary

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.veterinarymedicinelibrary.databinding.ActivityCategoryAddBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class categoryAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryAddBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progresDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategoryAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()
        progresDialog=ProgressDialog(this)
        progresDialog.setTitle("please wait...")
        progresDialog.setCanceledOnTouchOutside(false)

        binding.backbtn.setOnClickListener{
            onBackPressed()
        }

       binding.submitBtn.setOnClickListener {
           validateData()
       }
    }
    private var category=""
    private fun validateData() {
        category= binding.categoryEt.text.toString().trim()
        if (category.isEmpty()){
            Toast.makeText(this, "enter category..", Toast.LENGTH_SHORT).show()
        }else{
            addCategoryFirebase()
        }
    }

    private fun addCategoryFirebase() {
         progresDialog.show()
        val timestamp=System.currentTimeMillis()

        val hashMap=HashMap<String, Any>()
        hashMap["id"]="$timestamp"
        hashMap["category"]="$timestamp"
        hashMap["timestamp"]="$timestamp"
        hashMap["uid"]="$timestamp"

        val ref= FirebaseDatabase.getInstance().getReference("Users")
        ref.child("$timestamp")
            .setValue(hashMap)
            .addOnSuccessListener {
                progresDialog.dismiss()
                Toast.makeText(this, "added succesfully...", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener { e->
                progresDialog.dismiss()
                Toast.makeText(this, "failed to add due to ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }
}
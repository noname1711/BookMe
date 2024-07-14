package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PickActivity : AppCompatActivity() {

    private lateinit var dbRef :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick)
        dbRef = FirebaseDatabase.getInstance("https://hehe-610a7-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("lich")
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val edtDiadiem = findViewById<EditText>(R.id.edtDiadiem)
        val empDiadiem= edtDiadiem.text.toString()
        val edtTime = findViewById<EditText>(R.id.edtTime)
        val empTime = edtTime.text.toString()
        val empId = dbRef.push().key!!
        val employee = EmployeeModel(empId,empDiadiem,empTime)

        if(empDiadiem.isEmpty()){
            edtDiadiem.error="Nhập địa điểm tớ đón cậu í :3"
            return
        }
        if(empTime.isEmpty()){
            edtTime.error="Cậu ko rảnh lúc nào à :("
            return
        }
        dbRef.child(empId).setValue(employee).addOnCompleteListener {
            Toast.makeText(this,"Oki cậu tớ biết rùi :3",Toast.LENGTH_SHORT).show()
            edtTime.setText("")
            edtDiadiem.setText("")
        }
            .addOnFailureListener{ err->
                Toast.makeText(this,"tớ chưa nhận đc :( ${err.message}",Toast.LENGTH_SHORT).show()
            }
    }
}
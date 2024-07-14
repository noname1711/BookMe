package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.demo.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var dialog: AlertDialog  //cần có dòng này để dialog không báo đỏ
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnClick.setOnClickListener{
            showDialog()
        }
    }

    private fun showDialog() {
        val build = AlertDialog.Builder(this,R.style.Themecustom)   //hiển thị ngay màn hình
        val view = layoutInflater.inflate(R.layout.custom_dialog,null)  //chuyển đổi layout đã thiết kế thành view
        build.setView(view)
        //nút thoát
        val btnThoat= view.findViewById<ImageButton>(R.id.btnThoat)
        btnThoat.setOnClickListener{
            dialog.dismiss()
            Toast.makeText(this,"Nghĩ kĩ chưa cậu T_T", Toast.LENGTH_LONG).show()
        }
        //nút tham gia
        val btnThamGia = view.findViewById<Button>(R.id.btnThamGia)
        btnThamGia.setOnClickListener {
            Toast.makeText(this,"Z nào chúng mik đi chơi nha =))", Toast.LENGTH_LONG).show()
            val i = Intent(this,PickActivity::class.java)
            startActivity(i)
        }
        dialog= build.create()
        dialog.show()
    }
}
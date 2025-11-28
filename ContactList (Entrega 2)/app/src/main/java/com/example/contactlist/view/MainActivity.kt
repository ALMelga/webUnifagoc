package com.example.contactlist.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactlist.view.AddContactActivity
import com.example.contactlist.R
import com.example.contactlist.database.ContatoDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var btnAddTask : FloatingActionButton
    fun goToAddContactActivity(){
        val intent = Intent(this, AddContactActivity::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edit_nome_contato)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnAddTask = findViewById(R.id.fab_add_contato)
        btnAddTask.setOnClickListener {
            goToAddContactActivity()
        }
    }

    override fun onStart() {
        super.onStart()
        val contactList = ContatoDAO(this).list()
        contactList.forEach {
            Log.i("info_db","Id: ${it.id} - Nome: ${it.nome} - Telefone: ${it.telefone} - Email: ${it.email}")
        }
    }
}
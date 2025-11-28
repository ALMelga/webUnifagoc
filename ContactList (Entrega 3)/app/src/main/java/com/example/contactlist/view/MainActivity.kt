package com.example.contactlist.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.view.AddContactActivity
import com.example.contactlist.R
import com.example.contactlist.adapter.ContactAdapter
import com.example.contactlist.database.ContatoDAO
import com.example.contactlist.model.Contato
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var btnAddTask : FloatingActionButton
    private lateinit var rvContact : RecyclerView

    private var contactAdapter : ContactAdapter? = null
    fun goToAddContactActivity(contato : Contato? = null){
        val intent = Intent(this, AddContactActivity::class.java)
        if(contato!=null){
            intent.putExtra("id",contato.id)
            intent.putExtra("nome",contato.nome)
            intent.putExtra("telefone",contato.telefone)
            intent.putExtra("email",contato.email)
        }
        startActivity(intent)
    }

    fun updateList(){
        val contactList = ContatoDAO(this).list()
        contactAdapter?.setContactList(contactList)
    }

    fun confirmDelete(id:Int){
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Confirmar exclusão")
        alertBuilder.setMessage("Deseja realmente excluir o contato?")

        alertBuilder.setPositiveButton("Sim"){ _ , _ ->
            val contatoDAO = ContatoDAO(this)
            if(contatoDAO.delete(id)){
                updateList()
                Toast.makeText(this, "Contato excluído com sucesso", Toast.LENGTH_SHORT).show()
            }
        }
        alertBuilder.setNegativeButton("Não"){ _ , _ -> }

        alertBuilder.create().show()
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
        rvContact =  findViewById(R.id.rv_contact)
        rvContact.layoutManager = LinearLayoutManager(this)
        contactAdapter = ContactAdapter()
        rvContact.adapter = contactAdapter

        updateList()

        btnAddTask = findViewById(R.id.fab_add_contato)
        btnAddTask.setOnClickListener {
            goToAddContactActivity()
        }

        contactAdapter?.setOnDeleteClick { contact->
            confirmDelete(contact.id)
        }

        contactAdapter?.setOnUpdateClick { contact->
            goToAddContactActivity(contact)
        }
    }

    override fun onStart() {
        super.onStart()
        updateList()

    }
}
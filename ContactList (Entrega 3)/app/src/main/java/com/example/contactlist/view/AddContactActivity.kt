package com.example.contactlist.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.contactlist.R
import com.example.contactlist.database.ContatoDAO
import com.example.contactlist.model.Contato

class AddContactActivity : AppCompatActivity() {

    private lateinit var btnAddContact: Button

    private lateinit var btnCancelar: Button
    private lateinit var editNome: EditText
    private lateinit var editTelefone: EditText
    private lateinit var editEmail: EditText

    fun saveContact(nome: String, telefone: String, email: String) {
        val newContact = Contato(-1, nome, telefone, email)
        val contatoDAO = ContatoDAO(this)

        if (contatoDAO.save(newContact)) {
            Toast.makeText(this, "Contato inserido com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Erro ao inserir contato", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateContact(id: Int) {
        val contact = Contato(
            id,
            editNome.text.toString(),
            editTelefone.text.toString(),
            editEmail.text.toString()
        )

        val contatoDAO = ContatoDAO(this)

        if (contatoDAO.update(contact)) {
            Toast.makeText(this, "Contato atualizado com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Erro ao atualizar contato", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_contact)

        btnAddContact = findViewById(R.id.btn_add_contato)
        editNome = findViewById(R.id.ed_nome_contato)
        editTelefone = findViewById(R.id.ed_telefone_contato)
        editEmail = findViewById(R.id.ed_email_contato)

        val bundle = intent.extras
        var contactId: Int? = null

        if (bundle != null) {
            contactId = bundle.getInt("id")
            val contactNome = bundle.getString("nome")
            val contactTelefone = bundle.getString("telefone")
            val contactEmail = bundle.getString("email")

            // Preenche os campos
            editNome.setText(contactNome)
            editTelefone.setText(contactTelefone)
            editEmail.setText(contactEmail)

            btnAddContact.text = "Editar"
        }

        btnAddContact.setOnClickListener {
            val nome = editNome.text.toString()
            val telefone = editTelefone.text.toString()
            val email = editEmail.text.toString()

            if (nome.isNotEmpty() && telefone.isNotEmpty() && email.isNotEmpty()) {
                if (contactId != null) {
                    updateContact(contactId)
                } else {
                    saveContact(nome, telefone, email)
                }
            } else {
                Toast.makeText(this, "Insira os dados do contato", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancelar = findViewById(R.id.btn_cancelar_contato)

        btnCancelar.setOnClickListener {
            finish()
        }

    }
}

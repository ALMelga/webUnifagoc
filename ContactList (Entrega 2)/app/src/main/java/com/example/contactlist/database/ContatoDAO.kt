package com.example.contactlist.database

import android.content.ContentValues
import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import com.example.contactlist.model.Contato

class ContatoDAO(context : Context) : iContato {
    val write = DatabaseHelper(context).writableDatabase
    val read = DatabaseHelper(context).readableDatabase

    override fun save(contato: Contato): Boolean {
        val content = ContentValues()
        content.put("nome",contato.nome)
        content.put("telefone",contato.telefone)
        content.put("email",contato.email)

        try{
            write.insert("contact",null,content)
            Log.i("info_db","Registro inserido com sucesso")
        } catch (e: Exception){
            Log.i("info_db","Erro ao inserir registro no banco")
                return false
            }
            return true
        }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(contato: Contato): Boolean {
        TODO("Not yet implemented")
    }

    override fun list(): List<Contato> {
        val contactList = mutableListOf<Contato>()
        val sql = "SELECT * FROM contact"
        val cursor = read.rawQuery(sql,null)
        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
            val telefone = cursor.getString(cursor.getColumnIndexOrThrow("telefone"))
            val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))

            val contato = Contato(id,nome,telefone,email)
            contactList.add(contato)
        }
        return contactList
    }

}
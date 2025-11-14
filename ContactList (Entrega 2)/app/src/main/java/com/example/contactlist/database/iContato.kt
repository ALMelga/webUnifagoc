package com.example.contactlist.database

import com.example.contactlist.model.Contato

interface iContato {
    fun save(contato: Contato) : Boolean
    fun delete(id: Int) : Boolean
    fun update(contato: Contato) : Boolean
    fun list() : List<Contato>
}
package com.example.contactlist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    "contatos",
    null,
    1
){
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS contact(" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "  nome VARCHAR(100) NOT NULL," +
                "  telefone VARCHAR(15) NOT NULL," +
                "  email VARCHAR(50) NOT NULL" +
                ");"
        try{
            db?.execSQL(sql);
            Log.i("info_db","Banco criado com sucesso")
        } catch (e: Exception){
            Log.i("info_db","Erro ao criar banco")
        }
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }
}
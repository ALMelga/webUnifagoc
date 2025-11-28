package com.example.contactlist.adapter
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.R
import com.example.contactlist.model.Contato
import org.w3c.dom.Text
import java.util.EventListener

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private var contactList : List<Contato> = emptyList()
    private var onDeleteClick : ((Contato)-> Unit)? = null
    private var onUpdateClick : ((Contato)-> Unit)? = null
    fun setOnDeleteClick(listener: (Contato) -> Unit){
        onDeleteClick = listener
    }

    fun setOnUpdateClick(listener: (Contato) -> Unit){
        onUpdateClick = listener
    }
    fun setContactList(contactList : List<Contato>){
        this.contactList = contactList
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foto = itemView.findViewById<ImageView>(R.id.contato_foto)
        val nome = itemView.findViewById<TextView>(R.id.nome_contato)
        val telefone = itemView.findViewById<TextView>(R.id.telefone_contato)
        val email = itemView.findViewById<TextView>(R.id.email_contato)
        val btnEdit = itemView.findViewById<ImageButton>(R.id.btn_editar)
        val btnExcluir = itemView.findViewById<ImageButton>(R.id.btn_excluir)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_contato,parent,false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ContactViewHolder,
        position: Int
    ) {
        val contact = contactList[position]
        val nome = contact.nome
        val telefone = contact.telefone
        val email = contact.email

        holder.foto.setImageResource(R.drawable.ic_foto)
        holder.itemView.findViewById<TextView>(R.id.nome_contato).text = nome
        holder.itemView.findViewById<TextView>(R.id.telefone_contato).text = telefone
        holder.itemView.findViewById<TextView>(R.id.email_contato).text = email

        holder.btnExcluir.setOnClickListener {
            onDeleteClick?.invoke(contact)
        }

        holder.btnEdit.setOnClickListener {
            onUpdateClick?.invoke(contact)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }


}
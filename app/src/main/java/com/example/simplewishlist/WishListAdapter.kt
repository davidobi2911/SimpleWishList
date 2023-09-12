package com.example.simplewishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishListAdapter (private val items: MutableList<WishListObj>) : RecyclerView.Adapter<WishListAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView
        val numberTextViewt: TextView
        val urlTextView: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            nameTextView = itemView.findViewById(R.id.Name)
            numberTextViewt = itemView.findViewById(R.id.Number)
            urlTextView = itemView.findViewById(R.id.URL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: WishListAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val item = items.get(position)
        // Set item views based on views and data model
        holder.nameTextView.text = item.itemName
        holder.numberTextViewt.text = item.itemPrice.toString()
        holder.urlTextView.text = item.itemURL

        holder.itemView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.itemURL))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "This is the wrong URL for this " + item.itemName, Toast.LENGTH_LONG).show()
            }
        }
        holder.itemView.setOnLongClickListener{
            items.removeAt(holder.adapterPosition)
            this.notifyDataSetChanged()
            true
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

package com.example.simplewishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var items: MutableList<WishListObj>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lookup the RecyclerView in activity layout
        val listRv = findViewById<RecyclerView>(R.id.rvWishList)
        // Fetch the list of emails
        items = ArrayList()
        // Create adapter passing in the list of emails
        val adapter = WishListAdapter(items)
        // Attach the adapter to the RecyclerView to populate items
        listRv.adapter = adapter
        // Set layout manager to position the items
        listRv.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.submitButton).setOnClickListener {
            // get the values entered from all three views
            val inputNameView = findViewById<EditText>(R.id.editTextName)
            val nameStr = inputNameView.text.toString()

            val inputPriceView =  findViewById<EditText>(R.id.editTextPrice)
            val priceStr =  inputPriceView.text.toString().toDouble()

            val inputUrlView = findViewById<EditText>(R.id.editTextUrl)
            val urlString =  inputUrlView.text.toString()

            // construct WishListObj
            var wishListVal = WishListObj(nameStr,priceStr,urlString )
            // add new obj to items
            items.add(wishListVal)
            adapter.notifyDataSetChanged()
        }
    }
}
package com.example.groccerystore_2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.groccerystore.R

class CommentActivity : AppCompatActivity() {

    private lateinit var productCommentTV: TextView
    private lateinit var productNameTV: TextView
    private lateinit var productPriceTV: TextView
    private lateinit var productImageIV: ImageView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_comment)

        productCommentTV = findViewById(R.id.commentProductTV)
        productNameTV = findViewById(R.id.productNameTV)
        productPriceTV = findViewById(R.id.productPriceTV)
        productImageIV = findViewById(R.id.productImageIV)

        val comment = intent.getStringExtra("commentProd")
        val name = intent.getStringExtra("nameProd")
        val price = intent.getStringExtra("priceProd")
        val image = intent.getStringExtra("photoProd")
        productCommentTV.text = "$comment"
        productNameTV.text = "$name"
        productPriceTV.text = "$price"
        val photo: Uri? = Uri.parse(image)
        productImageIV.setImageURI(photo)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = ""
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.contexi_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.exitMenuMain ->{
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
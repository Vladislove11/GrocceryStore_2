package com.example.groccerystore

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.groccerystore_2.CommentActivity

class SecondActivity : AppCompatActivity() {

    private val GALLARY_REQUEST = 302
    var photoUri: Uri? = null
    var products: MutableList<Product> = mutableListOf()
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private lateinit var listViewLV: ListView
    private lateinit var productNameET: EditText
    private lateinit var productPriceET: EditText
    private lateinit var productCommentET: EditText
    private lateinit var editImageIV: ImageView
    private lateinit var saveBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = ""

        init()

        editImageIV.setOnClickListener{
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLARY_REQUEST)
        }

        saveBTN.setOnClickListener {
            createProduct()

            val listAdapter = ListAdapter(this@SecondActivity, products)
            listViewLV.adapter = listAdapter
            listAdapter.notifyDataSetChanged()
            clearEditFields()
        }
        listViewLV.onItemClickListener =
        AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, CommentActivity::class.java)
            intent.putExtra("commentProd", products[position].comment)
            intent.putExtra("nameProd", products[position].name)
            intent.putExtra("priceProd", products[position].price)
            intent.putExtra("photoProd", products[position].image)
            startActivity(intent)
        }
   }

    private fun init() {
        listViewLV = findViewById(R.id.listViewLV)
        productNameET = findViewById(R.id.productNameET)
        productPriceET = findViewById(R.id.productPriceET)
        productCommentET = findViewById(R.id.productCommentET)
        editImageIV = findViewById(R.id.editImageIV)
        saveBTN = findViewById(R.id.saveBTN)
    }

    private fun createProduct() {
        val productName = productNameET.text.toString()
        val productPrice = productPriceET.text.toString()
        val productComment = productCommentET.text.toString()
        val productImage = photoUri.toString()
        val product = Product(productName, productPrice, productComment, productImage)
        products.add(product)
        photoUri = null
    }

    private fun clearEditFields() {
        productNameET.text.clear()
        productPriceET.text.clear()
        productCommentET.text.clear()
        editImageIV.setImageResource(R.drawable.ic_photo)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        editImageIV = findViewById(R.id.editImageIV)
        when(requestCode) {
            GALLARY_REQUEST -> if (resultCode == RESULT_OK){
                photoUri = data?.data
                editImageIV.setImageURI(photoUri)
            }
        }
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
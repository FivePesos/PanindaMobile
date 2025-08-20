package dev.komsay.basicapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import dev.komsay.basicapplication.ui.components.ProductSellerCard
import dev.komsay.basicapplication.ui.components.ProductSellerItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class  HomePage : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
        val formattedDateTime = currentDateTime.format(formatter)

        val receivedUsername = intent.getStringExtra("USERNAME_KEY")
        val userTxtView = findViewById<TextView>(R.id.userTextView)
        val dateTime = findViewById<TextView>(R.id.dateTimeView)

        userTxtView.text = "Hello, $receivedUsername"
        dateTime.text =formattedDateTime.toString()

        val product = ProductSellerItem(
            id = "1",
            name = "Piattos",
            price = 10.50,
            stock = 15,
            imageResId = R.drawable.piattos
        )


        val viewHolder = ProductSellerCard(findViewById(R.id.productSellerContainer))
        viewHolder.bind(product) { selectedProduct, quantity ->
            handleSellClick(selectedProduct, quantity)
        }
    }

    private fun handleSellClick(product: ProductSellerItem, quantity: Int) {
        val totalAmount = product.price * quantity

        Toast.makeText(
            this,
            "Selling ${quantity}x ${product.name} for ${String.format("₱%.2f", totalAmount)}",
            Toast.LENGTH_LONG
        ).show()
    }
}
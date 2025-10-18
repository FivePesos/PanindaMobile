package dev.komsay.panindamobile

import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.komsay.panindamobile.ui.components.NavigationBarManager
import dev.komsay.panindamobile.ui.components.ProductInventoryComponent

class InventoryPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inventory_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.inventoryPage)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val addBtn = findViewById<ImageButton>(R.id.addBtn)

        addBtn.setOnClickListener {
            val dialog = AddProduct(this)
            dialog.show()
        }

        val app = application as Paninda
        val dataHelper = app.dataHelper

        val products = dataHelper.getAllProducts()

        val container = findViewById<LinearLayout>(R.id.productInventoryContainer)

        for (product in products) {
            val component = ProductInventoryComponent(container)
            component.bind(product)
        }

        val navigationBarManager = NavigationBarManager(this, findViewById(R.id.navbar))
        navigationBarManager.setup()
    }
}
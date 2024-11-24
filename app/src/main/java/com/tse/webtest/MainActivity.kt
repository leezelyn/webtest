package com.tse.webtest

import ProductViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tse.webtest.model.Product
import com.tse.webtest.ui.theme.WebTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WebTestTheme {
                WebTestTheme {
                    val productViewModel: ProductViewModel by viewModels() // 使用 Activity 级别的 ViewModel
                    ProductScreen(viewModel = productViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WebTestTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(viewModel: ProductViewModel) {
    val productList by viewModel.productList
    val errorMessage by viewModel.errorMessage

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Product List") })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (errorMessage.isNotEmpty()) {
                    Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
                }

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(productList) { product ->
                        ProductItem(product = product)
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.fetchProductList() }) {
                Text("Fetch")
            }
        }
    )
}

@Composable
fun ProductItem(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Title: ${product.title}")
        Text("Description: ${product.description}")
        Text("Edit Time: ${product.editTime}")
    }
}
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tse.webtest.model.Product
import com.tse.webtest.utils.RetrofitClient
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    var productList = mutableStateOf<List<Product>>(emptyList())
        private set

    var errorMessage = mutableStateOf("")

    fun fetchProductList() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getProductList()
                if (response.actionType == "ok") {
                    productList.value = response.data
                } else {
                    errorMessage.value = "Unexpected ActionType: ${response.actionType}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}

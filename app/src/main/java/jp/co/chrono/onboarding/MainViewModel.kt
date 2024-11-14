package jp.co.chrono.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : ViewModel() {


    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles


    private val qiitaRepository = QiitaRepository()

    fun searchArticles(query: String?) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fetchedArticles = qiitaRepository.getArticles(query)

                withContext(Dispatchers.Main) {
                    _articles.value = fetchedArticles
                }
            } catch (e: Exception) {

                withContext(Dispatchers.Main) {

                }
            }
        }
    }
}

package jp.co.chrono.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers

class SearchViewModel: ViewModel() {

    var articles: LiveData<List<Article>> = MutableLiveData<List<Article>>()
    private val qiitaRepository: QiitaRepository = QiitaRepository()

    fun searchArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            articles = qiitaRepository.getArticles()
        }
    }
}

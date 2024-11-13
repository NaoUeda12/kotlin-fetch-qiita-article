import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.chrono.onboarding.Article
import kotlinx.coroutines.Dispatchers


class SearchViewModel: ViewModel() {

    var articles: LiveData<List<Article>> = MutableLiveData<List<Article>>()
    private val qiitaRepository: QiitaRepository = QiitaRepository()

    fun searchArticles(query: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            articles = qiitaRepository.getArticles()
        }
    }
}

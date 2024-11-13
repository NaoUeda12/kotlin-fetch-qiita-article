package jp.co.chrono.onboarding

import MyItemAdapter
import android.app.VoiceInteractor
import android.os.Bundle
import android.util.Log
import android.view.WindowInsetsAnimation
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.search.SearchBar
import jp.co.chrono.onboarding.databinding.ActivityMainBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.lang.reflect.Modifier

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchView = binding.searchView

        searchView.setOnQueryTextListener(SearchViewListener(searchViewModel))
        searchView.setIconifiedByDefault(false)

        // viewModelのQiita記事のリストをオブザーブする
        searchViewModel.articles.observe(this, Observer { it ->
            // recyclerViewのAdapterに、取得した記事の情報を渡す
            it?.let { viewAdapter.setArticles(it) }
        })

        // recyclerViewをセット
        val recyclerView = binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = viewAdapter
        }

        binding.searchButton.setOnClickListener {
            val searchQuery = binding.searchBox.query.toString()
            if (searchQuery.isNotEmpty()) {
                fetchRelatedArticles(searchQuery)
            } else {
                fetchRecentArticles(searchQuery)
            }
        }
    }

    // searchViewのリスナークラス
    class SearchViewListener(private val viewModel: SearchViewModel) : SearchView.OnQueryTextListener {
        // 文字が入力されたタイミングで実行される
        override fun onQueryTextChange(newText: String?): Boolean {
            viewModel.searchArticles(newText)
            return false
        }

        // 検索が実行されたタイミングで実行される
        override fun onQueryTextSubmit(query: String?): Boolean {
            viewModel.searchArticles(query)
            return false
        }
    }
}

// 関連記事を取得する関数
fun fetchRelatedArticles(searchQuery: String) {

}

// 直近の記事を取得する関数
fun fetchRecentArticles(query: String) {

}

// Retrofitのビルダー
// private val retrofit = Retrofit.Builder().apply {
//     baseUrl("https://api.qiita.com/v2/")
// }.build()
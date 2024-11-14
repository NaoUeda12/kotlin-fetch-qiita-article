package jp.co.chrono.onboarding

import MyItemAdapter
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import jp.co.chrono.onboarding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewAdapter: MyItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewModelの初期化
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewAdapter = MyItemAdapter()

        val searchView = binding.searchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // 文字が入力されたときに呼ばれる
            override fun onQueryTextChange(newText: String?): Boolean {
                mainViewModel.searchArticles(newText)
                return false
            }

            // 検索ボタンが押されたときに呼ばれる
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainViewModel.searchArticles(query)
                return false
            }
        })

        // RecyclerViewの設定
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = viewAdapter
        }

        // 検索ボタンのクリックリスナー設定
        binding.searchButton.setOnClickListener {
            val searchQuery = binding.searchView.query.toString()
            if (searchQuery.isNotEmpty()) {
                fetchRelatedArticles(searchQuery)
            } else {
                fetchRecentArticles(searchQuery)
            }
        }

        // ViewModelのarticlesリストをオブザーブ
        mainViewModel.articles.observe(this, Observer { articles ->
            articles?.let {
                viewAdapter.setArticles(it)
            }
        })
    }

    // 関連記事を取得する関数
    fun fetchRelatedArticles(searchQuery: String) {
        // 関連記事の取得処理
    }

    // 直近の記事を取得する関数
    fun fetchRecentArticles(query: String) {
        // 直近の記事の取得処理
    }
}
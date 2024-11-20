package jp.co.chrono.onboarding

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import jp.co.chrono.onboarding.databinding.ActivityMainBinding
import jp.co.chrono.onboarding.network.QiitaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainActivity"
private const val SEARCH_QUERY_KEY = "search_query"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var savedQuery: String? = null
    private val adapter = ArticleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedQuery = savedInstanceState?.getString(SEARCH_QUERY_KEY)

        setupRecyclerView() // RecyclerViewの設定

        // 検索ボックスに保存されたクエリを設定
        savedQuery?.let { binding.searchBox.setQuery(it, false) }

        // ボタンタップでAPIをコール
        binding.searchButton.setOnClickListener {
            val query = binding.searchBox.query.toString()
            if (query.isBlank()) {
                Toast.makeText(this, "検索クエリを入力してください", Toast.LENGTH_SHORT).show()
            } else {
                fetchArticles(query)
            }
        }

        // 検索ボックスのリスナー設定
        binding.searchBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        binding.myRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun fetchArticles(query: String) {
        lifecycleScope.launch {
            try {
                val page = 1
                val perPage = 10

                // データ取得
                val articles = QiitaApi.retrofitService.getItems(query, page, perPage)

                // デバッグ出力: 取得した記事の詳細をログに出力
                articles.forEach { article ->
                    Log.d(TAG, "Title: ${article.title}, Author: ${article.user.name}")
                }

                // UI更新
                withContext(Dispatchers.Main) {
                    adapter.submitList(articles)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching articles: ${e.message}", e)
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SEARCH_QUERY_KEY, savedQuery)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }
}

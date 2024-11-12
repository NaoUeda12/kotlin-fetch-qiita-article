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

        // ViewModelのインスタンスを取得
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)


        /* 30個の文字列を格納したmutableList */
        val itemList = mutableListOf<String>()
        for (i in 1..30) {
            itemList.add("${i}個目のアイテム")
        }

        /* RecyclerView にセット */
        binding.myRecyclerView.setHasFixedSize(true)
        binding.myRecyclerView.adapter = MyItemAdapter(itemList)
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)


        binding.searchButton.setOnClickListener {
            val searchQuery = binding.searchBox.query.toString()

            if (searchQuery.isNotEmpty()) {
                fetchRelatedArticles(searchQuery)
            } else {
                fetchRecentArticles(searchQuery)
            }
        }


    }

}

// 関連記事を取得する関数
fun fetchRelatedArticles(searchQuery: String) {

}

// 直近の記事を取得する関数
fun fetchRecentArticles(query: String) {

}


//// Retrofit本体
//private val retrofit = Retrofit.Builder().apply {
//    baseUrl("okhttp3.HttpUrl")
//}.build()








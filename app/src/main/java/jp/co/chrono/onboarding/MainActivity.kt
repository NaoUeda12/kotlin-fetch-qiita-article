package jp.co.chrono.onboarding

import MyItemAdapter
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.search.SearchBar
import jp.co.chrono.onboarding.databinding.ActivityMainBinding
import java.lang.reflect.Modifier


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /* 30個の文字列を格納したmutableList */
        val itemList = mutableListOf<String>()
        for (i in 1..30) {
            itemList.add("${i}個目のアイテム")
        }

        /* RecyclerView にセット */
        binding.myRecyclerView.setHasFixedSize(true)
        binding.myRecyclerView.adapter = MyItemAdapter(itemList)
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)


        // ViewBinding を使用してボタンにアクセス
        val searchButton =
            binding.searchButton.setOnClickListener {
                // ボタンがクリックされたときの処理
                binding.searchBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String): Boolean {
                        // text changed
                        return false
                    }

                    override fun onQueryTextSubmit(query: String): Boolean {
                        // submit button pressed
                        return false


                    }


                })
                val searchQuery = binding.searchBox.query.toString()
                Toast.makeText(this@MainActivity, "検索結果: $searchQuery", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}









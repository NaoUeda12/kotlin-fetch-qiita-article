package jp.co.chrono.onboarding

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.savedstate.SavedStateRegistry
import com.google.android.material.search.SearchBar
import jp.co.chrono.onboarding.databinding.ActivityMainBinding
import java.lang.reflect.Modifier

private const val TAG = "MainActivity"
private const val SEARCH_QUERY_KEY = "search_query"

class MainActivity : AppCompatActivity(), SavedStateRegistry.SavedStateProvider {

    private lateinit var binding: ActivityMainBinding
    private var savedQuery: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("com.stored_query", MODE_PRIVATE)
        savedQuery = sharedPref.getString(SEARCH_QUERY_KEY, null)

        // 検索ボックスに保存されたクエリを設定
        savedQuery?.let {
            binding.searchBox.setQuery(it, false)
        }
    }

    val searchBar = binding.searchBox

//    // RecyclerViewの設定
//    val itemList = mutableListOf<String>()
//    for (i in 1..30)
//    {
//        itemList.add("${i}個目のアイテム")
//    }
//    binding.myRecyclerView.setHasFixedSize(true)
//    binding.myRecyclerView.adapter = MyItemAdapter(itemList)
//    binding.myRecyclerView.layoutManager = LinearLayoutManager(this)
    
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun saveState(): Bundle {
        val bundle = Bundle()
        bundle.putString(SEARCH_QUERY_KEY, savedQuery)
        return bundle
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
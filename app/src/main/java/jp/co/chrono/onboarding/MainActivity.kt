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

        if (savedInstanceState == null) {
            savedQuery = null
        } else {
            savedQuery = savedStateRegistry.consumeRestoredStateForKey(SEARCH_QUERY_KEY)
                ?.getString(SEARCH_QUERY_KEY)
        }

        // RecyclerViewの設定
        val itemList = mutableListOf<String>()
        for (i in 1..30) {
            itemList.add("${i}個目のアイテム")
        }
        binding.myRecyclerView.setHasFixedSize(true)
        binding.myRecyclerView.adapter = MyItemAdapter(itemList)
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)


        savedQuery?.let {
            binding.searchBox.setQuery(it, false)
        }


        binding.searchBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d(TAG, "Search submitted with query: $query")
                savedQuery = query
                return false
            }
        })
    }

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
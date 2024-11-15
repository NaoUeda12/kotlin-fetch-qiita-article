package jp.co.chrono.onboarding

import MyItemAdapter
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.search.SearchBar
import jp.co.chrono.onboarding.databinding.ActivityMainBinding
import java.lang.reflect.Modifier

// Tag for logging
private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")


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


        binding.searchButton.setOnClickListener {
            binding.searchBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {

                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    Log.d(TAG, "Search submitted with query: $query")
                    return false
                }
            })
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }
}




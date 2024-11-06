package jp.co.chrono.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.co.chrono.onboarding.databinding.ActivityMainBinding
import java.lang.reflect.Modifier


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}

@Composable
fun SearchQiitaArticleScreen(modifier: Modifier = Modifier) {
    
}
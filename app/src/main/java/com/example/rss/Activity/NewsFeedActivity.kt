package com.example.rss.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.rss.Fragment.RSSFragment
import com.example.rss.R
import com.example.rss.databinding.NewsItemBinding
import kotlinx.android.synthetic.main.news_item.*

class NewsFeedActivity : AppCompatActivity() {

    private val binding by lazy { NewsItemBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_feed)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_root, RSSFragment()).commit();
    }

    fun openFavorites(view: View){
        startActivity(Intent(this, FavoritesActivity::class.java))
    }
}
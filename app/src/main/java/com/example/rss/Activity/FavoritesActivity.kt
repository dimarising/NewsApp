package com.example.rss.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rss.Fragment.RSSFragment
import com.example.rss.R

class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentFavorite, RSSFragment()).commit();
    }
}
package com.example.rss.Item

class RssItem {
    var title = ""
    var link = ""
    var description = ""

    override fun toString(): String {
        return "RssItem(title='$title', link='$link', description= '$description')"
    }
}
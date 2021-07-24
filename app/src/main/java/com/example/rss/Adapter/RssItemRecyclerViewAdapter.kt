package com.example.rss.Adapter


import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rss.Fragment.RSSFragment
import com.example.rss.Item.RssItem
import com.example.rss.R
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException


class RssItemRecyclerViewAdapter(
        private val mValues: List<RssItem>,
        private val mListener: RSSFragment.OnListFragmentInteractionListener?,
        private val context: FragmentActivity?,



) : RecyclerView.Adapter<RssItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            v.setBackgroundColor(Color.parseColor("#686868"))
            val array: List<String> = v.tag.toString().split("'")

            val uris = Uri.parse(array.get(3))
            val intents = Intent(Intent.ACTION_VIEW, uris)
            val b = Bundle()
            b.putBoolean("new_window", true)
            intents.putExtras(b)
            if (context != null) {
                context.startActivity(intents)
            }





        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mValues[position]
        holder.titleTV?.text = item.title
        holder.contentTV?.text  = item.description.substringAfterLast(">")

        var link = getFeaturedImageLink(item.description)


        if(link != null) {
            context?.let {
                holder.featuredImg?.let { it1 ->
                    Glide.with(it)
                            .load(link)
                            .centerCrop()
                            .into(it1)
                }
            }
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }




    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val titleTV: TextView? = mView.findViewById(R.id.titleTextView)
        val contentTV: TextView? = mView.findViewById(R.id.bodyTextView)
        val featuredImg: ImageView? = mView.findViewById(R.id.imageView);
    }


    private fun getFeaturedImageLink(htmlText: String): String? {
        var result: String? = null

        val stringBuilder = StringBuilder()
        try {
            val doc: org.jsoup.nodes.Document = Jsoup.parse(htmlText)
            val imgs: Elements = doc.select("img")

            for (img in imgs) {
                var src = img.attr("src")
                result = src
            }

        } catch (e: IOException) {

        }
        return result

    }

}
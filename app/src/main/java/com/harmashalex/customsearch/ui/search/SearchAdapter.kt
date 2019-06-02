package com.harmashalex.customsearch.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.harmashalex.customsearch.R
import com.harmashalex.customsearch.data.entity.SearchItem
import com.harmashalex.customsearch.util.Utils
import java.lang.IllegalArgumentException

class SearchAdapter(val clickDelegate: ClickDelegate) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val HEADER_VIEW_TYPE = 0
    private val SEARCH_ITEM_VIEW_TYPE = 1

    var searchItems: ArrayList<SearchItem> = arrayListOf()

    class SearchViewHolder(val view: View, val tvTitle: TextView, val tvDescription: TextView) : RecyclerView.ViewHolder(view)

    class HeaderViewHolder(val view: View,
                           val ivCoverImage: ImageView,
                           val etSearch: EditText,
                           val btnSearch: Button,
                           val btnSearchStop: Button,
                           val btnLastSearch:Button) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
       return when(viewType) {
            HEADER_VIEW_TYPE -> buildHeaderViewHolder(parent)
            SEARCH_ITEM_VIEW_TYPE -> buildSearchItemViewHolder(parent)
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    private fun buildHeaderViewHolder(parent: ViewGroup): HeaderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.header_search, parent, false)
        val ivLogo = itemView.findViewById<ImageView>(R.id.iv_cover_image)
        val etSearch = itemView.findViewById<EditText>(R.id.et_search)
        val btnSearch = itemView.findViewById<Button>(R.id.btn_search)
        val btnStopSearch = itemView.findViewById<Button>(R.id.btn_stop_search)
        val btnLastSearch = itemView.findViewById<Button>(R.id.btn_last_search)
        return HeaderViewHolder(itemView, ivLogo, etSearch, btnSearch, btnStopSearch, btnLastSearch)
    }

    private fun buildSearchItemViewHolder(parent: ViewGroup): SearchViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        val tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
        return SearchViewHolder(itemView, tvTitle, tvDescription)
    }

    override fun getItemViewType(position: Int) = if (position == 0) HEADER_VIEW_TYPE else SEARCH_ITEM_VIEW_TYPE

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is HeaderViewHolder -> bindHeaderView(holder)
            is SearchViewHolder -> bindSearchViewItem(holder, position)
        }
    }

    private fun bindHeaderView(holder: HeaderViewHolder) {
        Utils.loadImage(holder.ivCoverImage.context.getString(R.string.search_engine_image), holder.ivCoverImage)
        holder.btnSearch.setOnClickListener { clickDelegate.onSearchClicked(holder.etSearch.text.toString()) }
        holder.btnSearchStop.setOnClickListener { clickDelegate.onStopSearchClicked() }
        holder.btnLastSearch.setOnClickListener { clickDelegate.showLastQueryClicked() }
    }

    private fun bindSearchViewItem(holder: SearchViewHolder, position: Int) {
        if (position <= searchItems.size - 1) {
            val item = searchItems.get(position)
            holder.tvTitle.text = item.title
            holder.tvDescription.text = item.snippet
            holder.view.setOnClickListener{ clickDelegate.openInExternalBrowser(item.link) }
        }
    }

    override fun getItemCount() = searchItems.size + 1
}
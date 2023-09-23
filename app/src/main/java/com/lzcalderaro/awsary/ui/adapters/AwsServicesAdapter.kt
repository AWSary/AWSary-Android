package com.lzcalderaro.awsary.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lzcalderaro.awsary.R
import com.lzcalderaro.awsary.webservice.dto.AwsItem

class AwsServicesAdapter(private val awsList: List<AwsItem>?, private val context:Context): BaseAdapter(), Filterable {

    var awsFilterableList: List<AwsItem>? = awsList

    override fun getCount(): Int {
        return awsFilterableList?.size ?: 0
    }

    override fun getItem(position: Int): Any {
        return awsFilterableList?.get(position) ?: 0
    }

    override fun getItemId(position: Int): Long {
        return awsFilterableList?.get(position)?.id?.toLong() ?: 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val createdView: View = LayoutInflater.from(context).inflate(R.layout.item, parent, false)

        val awsService = (awsFilterableList?.get(position) ?: null) as AwsItem
        populate(createdView, awsService)

        return createdView
    }

    private fun populate(createdView: View, awsService: AwsItem) {
        val title: TextView = createdView.findViewById(R.id.itemText)
        val image: ImageView = createdView.findViewById(R.id.itemImage)

        title.text = awsService.name
        Glide.with(context).load(awsService.imageURL).into(image)
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val filteredList = mutableListOf<AwsItem>()

            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(awsList!!)
            } else {
                for (item in awsFilterableList!!) {
                    if (item.name.lowercase().contains(constraint.toString().lowercase())) {
                        filteredList.add(item)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            awsFilterableList = filterResults?.values as MutableList<AwsItem>
            notifyDataSetChanged()
        }

    }
}
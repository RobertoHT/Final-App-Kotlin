package com.roberto.app.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.roberto.app.R
import com.roberto.app.inflate
import com.roberto.app.models.Rate
import com.roberto.app.utils.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_rates_item.view.*
import java.text.SimpleDateFormat

/**
 * Created by Roberto Hdez. on 26/06/18.
 */
class RatesAdapter(private val items: List<Rate>) : RecyclerView.Adapter<RatesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.fragment_rates_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(rate: Rate) = with(itemView) {
            textViewRate.text = rate.text
            textViewStar.text = rate.rate.toString()
            textViewDate.text = SimpleDateFormat("dd MMM, yyyy").format(rate.createdAt)
            if (rate.profileImageURL.isEmpty()) {
                Picasso.get().load(R.drawable.ic_person).resize(100, 100).centerCrop()
                        .transform(CircleTransform()).into(imageViewProfile)
            } else {
                Picasso.get().load(rate.profileImageURL).resize(100, 100).centerCrop()
                        .transform(CircleTransform()).into(imageViewProfile)
            }
        }
    }
}
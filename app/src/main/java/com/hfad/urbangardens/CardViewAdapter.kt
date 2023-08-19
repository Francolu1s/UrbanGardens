package com.hfad.urbangardens

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.urbangardens.CardViewModelClass
import com.hfad.urbangardens.R

class CardViewAdapter(
    private val context: Context,
    private val data: List<CardViewModelClass>,
    private val onItemClick: (CardViewModelClass) -> Unit
) : RecyclerView.Adapter<CardViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.card_view_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: CardView = itemView.findViewById(R.id.cardViewLayout)
        private val titleTextView: TextView = itemView.findViewById(R.id.cardViewText)
        private val imageView: ImageView = itemView.findViewById(R.id.cardViewImage)

        init {
            cardView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = data[position]
                    onItemClick(item)
                }
            }
        }

        fun bind(item: CardViewModelClass) {
            titleTextView.text = "Add ${item.cardViewText}"
            imageView.setImageResource(item.cardViewImage)
        }
    }
}
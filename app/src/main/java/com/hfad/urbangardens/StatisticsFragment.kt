package com.hfad.urbangardens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.NumberFormat
import java.util.*

class StatisticsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_statistics, container, false)
        // Edit Total Units
        val grandTUsed = rootView.findViewById<TextView>(R.id.grandTotalUsed)
        val grandTAveragePrice = rootView.findViewById<TextView>(R.id.grandTotalAveragePrice)
        val grandTSpent = rootView.findViewById<TextView>(R.id.grandTotalSpent)
        val numberOfGoods = 6
        val loggedInUserId = MyApplication.userViewModel.loggedInUserId
        val addItemDao = MyApplication.userDatabase.addItemDao()
        val itemsArray = loggedInUserId?.let { addItemDao.getItemsByUser(it) }
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        var grandTotalUsed = 0.0
        var grandTotalSpent = 0.0
        format.maximumFractionDigits = 1
        for (i in 1..numberOfGoods) {
            val itemName = MyApplication.categoriesList[i-1].cardViewText
            val volumeTextId = resources.getIdentifier("tComponent$i", "id",
                activity?.packageName ?: "urbangardens"
            )
            val volumeText = rootView.findViewById<TextView>(volumeTextId)
            val priceTextId = resources.getIdentifier("aPrice$i", "id",
                activity?.packageName ?: "urbangardens"
            )
            val priceText = rootView.findViewById<TextView>(priceTextId)
            val spentTextId = resources.getIdentifier("tSpent$i", "id",
                activity?.packageName ?: "urbangardens"
            )
            val spentText = rootView.findViewById<TextView>(spentTextId)
            var countVolume = 0.0
            var countSpent = 0.0
            var countRegisters = 0.0

            if (itemsArray != null) {
                for (element in itemsArray) {
                    if(element.item == itemName) {
                        countVolume += element.unitsBought
                        countSpent += element.unitPrice * element.unitsBought
                        countRegisters ++
                    }
                }
            }
            val averagePrice = if(countVolume == 0.0 && countSpent == 0.0 ) 0.0 else countSpent/countVolume
            format.currency = Currency.getInstance("USD")
            volumeText.text = String.format("%.1f", countVolume)
            spentText.text = format.format(countSpent)
            priceText.text = format.format(averagePrice)
            grandTotalUsed += countVolume
            grandTotalSpent += countSpent
        }
        grandTAveragePrice.text = if(grandTotalUsed == 0.0 && grandTotalSpent == 0.0 ) "$0.0" else format.format(grandTotalSpent/grandTotalUsed)
        grandTSpent.text = format.format(grandTotalSpent)
        grandTUsed.text = String.format("%.1f", grandTotalUsed)
        return rootView
    }
}
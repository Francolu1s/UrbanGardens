package com.hfad.urbangardens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoriesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_categories, container, false)
        //2. AdapterView
        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //1. Data
        val categoriesList = listOf(
            CardViewModelClass("Add Water", R.drawable.water),
            CardViewModelClass("Add Soil", R.drawable.soil),
            CardViewModelClass("Add Seeds", R.drawable.seeds),
            CardViewModelClass("Add Compost", R.drawable.compost),
            CardViewModelClass("Add Tools", R.drawable.tools),
            CardViewModelClass("Add Boundaries", R.drawable.boundary),
        )
        //3. Adapter
        adapter = CardViewAdapter(rootView.context, categoriesList) { item ->
            // Handle the click event for the item
            // You can access the properties of the clicked item, such as item.title, item.imageResId, etc.
            // Perform any actions you want when an item is clicked
            Toast.makeText(activity, "Clicked: ${item.cardViewText}", Toast.LENGTH_SHORT).show()
            val intent = Intent(rootView.context, RegisterGoods::class.java)
            intent.putExtra("position", item.cardViewText)
            intent.putExtra("image", item.cardViewImage)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        // Inflate the layout for this fragment
        return rootView
    }
}
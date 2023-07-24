package com.hfad.urbangardens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class TipsFragment : Fragment() {
    private lateinit var tipsButton: Button
    private lateinit var tipsText: TextView
    private lateinit var descriptionText: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_tips, container, false)
        tipsButton = rootView.findViewById(R.id.tipsButton)
        tipsText = rootView.findViewById(R.id.tipsText)
        descriptionText = rootView.findViewById(R.id.descriptionText)
        imageView = rootView.findViewById(R.id.imageView)
        var tip = 0
        //1. Data
        val tipsList = listOf(
            TipsClass("Plan your plot", "An urban garden can occupy a shared, community plot of land, a section of a yard or rooftop, or a container on a fire escape or windowsill. Most edible plants are hungry for sunlight, so look for a south-facing area for your plants.", R.drawable.tip1),
            TipsClass("Get the right tools for the job", "The only major things one needs to start a garden are soil, a vessel or area to plant in, and seeds. Many gardeners further opt for terra-cotta pots, compost (more about that later), gardening gloves, a watering can, organic fertilizers, and lumber for raised beds. ", R.drawable.tip2),
            TipsClass("Grow appropriate plants", "Once you’ve figured out your zone, it’s time to pick plants that thrive in urban landscapes. Non-edible varieties include zinnias, daylilies, and coneflowers; if you’re growing plants to eat, look for varieties that don’t need a huge amount of space (this is probably not the best time to put in a pumpkin patch).",  R.drawable.tip3),
            TipsClass("Grow what you eat", "If you will grow edible plants, one of the biggest mistakes you can make is growing food you don’t normally like to eat. There’s no reason to grow 15 summer squash plants if you can’t stand ratatouille, or cucumbers if you never eat them. Focus on foods you naturally gravitate toward and start easy. ", R.drawable.tip4),
            TipsClass("Raise your beds", "Raised beds are a great, no-till approach to gardening. They also circumvent the potential for harmful toxins to leach into your plants. If you plan to install raised beds, there are a lot of prefab beds that can be purchased online or from garden stores, or you can build your own.", R.drawable.tip5),
            TipsClass("Test your Soil","Any soil you want to grow food in ought to first be tested first for pH and nutrient levels, as well as for contaminants. City dirt, in particular, is likely to have absorbed toxins that you don’t want ending up in your vegetables. Local extension offices, as well as national soil-test centers, can provide results on everything from pH and nutrient levels to potential toxins.", R.drawable.tip6),
            TipsClass("Make dirt", "Making dirt is as easy as composting food scraps from your kitchen table. Other items that can be broken down include shredded paper, cardboard, and newspaper—and, depending on what household cleaners you use at home, dustpan refuse and whatever you suck up with your vacuum cleaner.", R.drawable.tip7),
            TipsClass("Grow up", "Small spaces don’t have to mean limited garden space. Vertical gardening offers flexibility for growing plants along the interior or outside walls, or up arbors in the yard or on rooftops. Tire towers for potatoes, felt wall hangings for herbs or loose leaf greens, and trellises for peas, beans, and squash can all offer big yields in tiny spaces.", R.drawable.tip8),
            TipsClass("Embrace every season","Four-season city dwellers can enjoy the respite of winter and scale back the foods being grown, or use colder seasons to grow herbs indoors on kitchen counters. In places like California, urban gardeners can study when to plant crops like artichokes and garlic for year-round plants.", R.drawable.tip9),
            TipsClass("Rethink your fish tank", "Aquaponics is a method of gardening in which fish provide the nitrogen plants need so you can grow things indoors without a lick of soil or fertilizer. All it takes is a fish tank of any size, a tiny pond pump to pull water out of it, and a grow bed with pea gravel and seeds (as well as an indoor grow light to keep on between eight and 12 hours a day).",  R.drawable.tip10),
            )
        tipsText.text = tipsList[tip].tipText
        descriptionText.text = tipsList[tip].descriptionText
        imageView.setImageResource(tipsList[tip].tipImage)


        tipsButton.setOnClickListener {
            if(tip + 1>tipsList.lastIndex) tip = 0 else {
                tip++
            }
            tipsText.text = tipsList[tip].tipText
            descriptionText.text = tipsList[tip].descriptionText
            imageView.setImageResource(tipsList[tip].tipImage)


        }

        // Inflate the layout for this fragment
        return rootView
    }
}
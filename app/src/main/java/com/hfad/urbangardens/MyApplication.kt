package com.hfad.urbangardens
import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.room.Room

class MyApplication : Application(), ViewModelStoreOwner {
    companion object {
        lateinit var userViewModel: UserViewModel
        lateinit var userDatabase: UserDatabase
        val categoriesList = listOf(
            CardViewModelClass("Water", R.drawable.water, "Liters"),
            CardViewModelClass("Soil", R.drawable.soil, "Kilograms"),
            CardViewModelClass("Seeds", R.drawable.seeds, "grams"),
            CardViewModelClass("Compost", R.drawable.compost, "Kilograms"),
            CardViewModelClass("Tools", R.drawable.tools, "Tools"),
            CardViewModelClass("Boundaries", R.drawable.boundary, "Meters"),
        )
    }
    private val viewModelStore = ViewModelStore()

    override fun onCreate() {
        super.onCreate()
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


    }
    override fun getViewModelStore(): ViewModelStore {
        return viewModelStore
    }
}
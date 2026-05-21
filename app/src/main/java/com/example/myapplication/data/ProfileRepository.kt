package com.example.myapplication.data

import android.content.Context
import android.content.SharedPreferences

class ProfileRepository(private val context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("restaurant_prefs", Context.MODE_PRIVATE)

    fun getRestaurantName(): String = prefs.getString("name", "Tante Sunda") ?: "Tante Sunda"
    fun getAddress(): String = prefs.getString("address", "Jl. Braga No. 123, Bandung") ?: "Jl. Braga No. 123, Bandung"
    fun getDescription(): String = prefs.getString("description", "Tante Sunda menyajikan keaslian cita rasa bumi Parahyangan. Dengan bahan-bahan segar pilihan dan resep turun-temurun, kami menghadirkan suasana pedesaan yang asri di tengah kota.") ?: "Tante Sunda menyajikan keaslian cita rasa bumi Parahyangan. Dengan bahan-bahan segar pilihan dan resep turun-temurun, kami menghadirkan suasana pedesaan yang asri di tengah kota."
    fun getOpenHours(): String = prefs.getString("open_hours", "09:00 - 21:00") ?: "09:00 - 21:00"

    fun saveProfile(name: String, address: String, description: String, openHours: String) {
        prefs.edit().apply {
            putString("name", name)
            putString("address", address)
            putString("description", description)
            putString("open_hours", openHours)
            apply()
        }
    }
}

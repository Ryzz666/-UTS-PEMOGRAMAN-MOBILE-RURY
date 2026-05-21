package com.example.myapplication.data

import com.example.myapplication.R

object MenuData {
    val menuList = listOf(
        MenuItem(
            "1",
            "Nasi Timbel Kumplit",
            65000,
            "Nasi timbel hangat dibungkus daun pisang, disajikan dengan ayam goreng, tahu, tempe, sambal dadak, dan lalapan segar.",
            R.drawable.sunda.toString(),
            4.8
        ),
        MenuItem(
            "2",
            "Ayam Bakar Madu",
            45000,
            "Ayam pilihan yang dibakar dengan bumbu madu khas Parahyangan, gurih dan manis meresap sampai ke tulang.",
            R.drawable.sunda.toString(),
            4.7
        ),
        MenuItem(
            "3",
            "Gurame Terbang",
            95000,
            "Ikan gurame segar yang digoreng dengan teknik khusus hingga renyah, disajikan dengan sambal kecap pedas.",
            R.drawable.sunda.toString(),
            4.9
        ),
        MenuItem(
            "4",
            "Karedok Leunca",
            25000,
            "Sayuran segar mentah dengan bumbu kacang kencur yang khas, memberikan kesegaran alami di setiap suapan.",
            R.drawable.sunda.toString(),
            4.5
        ),
        MenuItem(
            "5",
            "Pepes Ikan Mas",
            35000,
            "Ikan mas yang dikukus dengan bumbu kuning melimpah di dalam balutan daun pisang, aroma rempah yang menggoda.",
            R.drawable.sunda.toString(),
            4.6
        ),
        MenuItem(
            "6",
            "Soto Bandung",
            40000,
            "Soto bening dengan potongan daging sapi empuk, lobak segar, dan taburan kacang kedelai goreng yang renyah.",
            R.drawable.sunda.toString(),
            4.4
        ),
        MenuItem(
            "7",
            "Sate Maranggi",
            55000,
            "Sate daging sapi bumbu ketumbar khas Purwakarta, disajikan dengan sambal tomat pedas segar.",
            R.drawable.sunda.toString(),
            4.8
        ),
        MenuItem(
            "8",
            "Tutug Oncom",
            30000,
            "Nasi hangat yang diaduk dengan oncom bakar spesial, aroma kencur dan terasi yang membangkitkan selera.",
            R.drawable.sunda.toString(),
            4.5
        ),
        MenuItem(
            "9",
            "Es Lilin Geulis",
            15000,
            "Pencuci mulut tradisional dengan berbagai varian rasa buah asli, menyegarkan hari Anda.",
            R.drawable.sunda.toString(),
            4.3
        ),
        MenuItem(
            "10",
            "Surabi Oncom",
            20000,
            "Surabi tradisional yang dimasak menggunakan tungku kayu bakar dengan topping oncom pedas yang gurih.",
            R.drawable.sunda.toString(),
            4.6
        )
    )

    fun getMenuById(id: String): MenuItem? {
        return menuList.find { it.id == id }
    }
}

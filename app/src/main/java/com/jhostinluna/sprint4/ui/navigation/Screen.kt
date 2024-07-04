package com.jhostinluna.sprint4.ui.navigation

import com.jhostinluna.sprint4.R

sealed class Screen (
    val route : String,
    val resourceId: Int
) {
    object Home : Screen(route = "home_fragment", resourceId = R.string.home_fragment)
    object AddPerson : Screen(route = "create_person_fragment", resourceId = R.string.create_person_fragment)
    object PersonDetail : Screen(route = "person_detail_fragment", resourceId = R.string.person_detail_fragment)
    object MapCity : Screen(route = "map_city_fragment", resourceId = R.string.map_city_fragment)

}
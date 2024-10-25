package org.sopt.and.navigation

import org.sopt.and.R


data class BottomNavigationItem(
    val label : Int = R.string.home,
    val icon : Int = R.drawable.ic_home,
    val route : String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = R.string.home,
                icon = R.drawable.ic_home,
                route = BottomNavRoutes.Home
            ),
            BottomNavigationItem(
                label = R.string.search,
                icon = R.drawable.ic_search,
                route = BottomNavRoutes.Search
            ),
            BottomNavigationItem(
                label = R.string.my,
                icon = R.drawable.img_sample,
                route = BottomNavRoutes.My
            ),
        )
    }
}
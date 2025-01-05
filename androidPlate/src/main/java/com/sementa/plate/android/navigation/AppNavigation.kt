package com.sementa.plate.android.navigation

enum class Screen {
    HOME,
    VEHICLE_DETAIL,
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object VehicleDetail : NavigationItem(Screen.VEHICLE_DETAIL.name)
}
package com.example.myappcompose.resolve_parking

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class ResolveParkingScreen(
    val route: String, val navArguments: List<NamedNavArgument> = emptyList()
) {

    object TicketDetail : ResolveParkingScreen(
        route = "ticketDetail"
    ) {
        fun createRouteToDetail(model: String) = "ticketDetail"

    }

    object PaymentConfirm : ResolveParkingScreen(
        route = "paymentConfirm"
    ) {
        fun createsPaymentConfirmToHome() = "home"
        fun createsPaymentConfirmToNewTicket() = "addTicket"
    }

    object ConfirmSales : ResolveParkingScreen(
        route = "confirmSales"
    ) {
        fun createConfirmSalesToPayment() = "paymentConfirm"
    }

    object AddTicket : ResolveParkingScreen(route = "addTicket") {
        fun createAddTicketToConfirmSales() = "confirmSales"
    }

    object Home : ResolveParkingScreen(route = "home") {
        fun createHomeToDetailScreen() = "ticketDetail"
        fun createHomeToAddTicket() = "addTicket"
    }

    object StarShift : ResolveParkingScreen(route = "starShift") {
        fun createStartShiftToHome() = "home"
    }

    object PinActivity : ResolveParkingScreen(route = "pinActivity") {
        fun createPinScreenToStartShift() = "starShift"
    }


}
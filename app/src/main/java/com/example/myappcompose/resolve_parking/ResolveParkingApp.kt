package com.example.myappcompose.resolve_parking

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myappcompose.resolve_parking.compose_ui.AddTicketScreen
import com.example.myappcompose.resolve_parking.compose_ui.ConfirmSalesScreen
import com.example.myappcompose.resolve_parking.compose_ui.PaymentConfirmScreen
import com.example.myappcompose.resolve_parking.compose_ui.PinEnterScreen
import com.example.myappcompose.resolve_parking.compose_ui.ResolveHomeScreen
import com.example.myappcompose.resolve_parking.compose_ui.StartShiftScreen
import com.example.myappcompose.resolve_parking.compose_ui.TicketDetailScreen
import com.example.myappcompose.resolve_parking.data.models.TicketDetailModel
import com.example.myappcompose.ui.theme.MyAppComposeTheme
import com.google.gson.Gson

@Composable
fun ResolveParkingApp() {
    MyAppComposeTheme {
        val navController = rememberNavController()
        ResolveParkingAppNavHost(
            navController = navController
        )
    }
}


@Composable
fun ResolveParkingAppNavHost(
    navController: NavHostController
) {
    val activity = (LocalContext.current as Activity)
    NavHost(
        navController = navController,
        startDestination = ResolveParkingScreen.PinActivity.route
    ) {
        composable(
            route = ResolveParkingScreen.PinActivity.route,
            arguments = ResolveParkingScreen.PinActivity.navArguments
        ) {
            PinEnterScreen()
        }
        composable(
            route = ResolveParkingScreen.StarShift.route,
            arguments = ResolveParkingScreen.StarShift.navArguments
        ) {
            StartShiftScreen {
                navController.navigate(
                    ResolveParkingScreen.StarShift.createStartShiftToHome()
                )
            }
        }
        composable(
            route = ResolveParkingScreen.Home.route,
            arguments = ResolveParkingScreen.Home.navArguments
        ) {
            val json = it.arguments?.getString("model")
            ResolveHomeScreen(
                {
                    navController.navigate(
                        ResolveParkingScreen.Home.createHomeToAddTicket()
                    )
                },
                {
                    navController.navigate(
                        ResolveParkingScreen.Home.createHomeToDetailScreen()
                    )
                }
            )
        }

        composable(
            route = ResolveParkingScreen.TicketDetail.route,
            arguments = ResolveParkingScreen.TicketDetail.navArguments
        ) {
            val json = it.arguments?.getString("model")
            val product = if (!json.isNullOrEmpty()) {
                Gson().fromJson(json, TicketDetailModel::class.java)
            } else {
                TicketDetailModel()
            }
            TicketDetailScreen(product) {
                navController.navigateUp()
            }
        }

        composable(
            route = ResolveParkingScreen.AddTicket.route,
            arguments = ResolveParkingScreen.AddTicket.navArguments
        ) {

            AddTicketScreen({
                navController.navigateUp()
            }, {

                navController.navigate(
                    ResolveParkingScreen.AddTicket.createAddTicketToConfirmSales()
                )
            })
        }

        composable(
            route = ResolveParkingScreen.ConfirmSales.route,
            arguments = ResolveParkingScreen.ConfirmSales.navArguments
        ) {

            ConfirmSalesScreen({
                navController.navigateUp()
            }) {

                navController.navigate(
                    ResolveParkingScreen.ConfirmSales.createConfirmSalesToPayment()
                )
            }
        }
        composable(
            route = ResolveParkingScreen.PaymentConfirm.route,
            arguments = ResolveParkingScreen.PaymentConfirm.navArguments
        ) {

            PaymentConfirmScreen({
                navController.navigate(
                    ResolveParkingScreen.PaymentConfirm.createsPaymentConfirmToHome()
                )
            }) {

                navController.navigate(
                    ResolveParkingScreen.PaymentConfirm.createsPaymentConfirmToNewTicket()
                )
            }
        }


    }
}


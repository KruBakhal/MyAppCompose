package com.example.myappcompose.shopapp.compose

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ShareCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.example.CategoryList
import com.example.example.ProductModel
import com.example.myappcompose.R
import com.example.myappcompose.ui.theme.MyAppComposeTheme
import com.example.myappcompose.shopapp.utils.ShopScreen
import com.google.gson.Gson

@Composable
fun ShopApp() {
    MyAppComposeTheme {
        val navController = rememberNavController()
        SunFlowerNavHost(
            navController = navController
        )
    }
}

@Composable
fun SunFlowerNavHost(
    navController: NavHostController
) {
    val activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = ShopScreen.Home.route) {
        composable(route = ShopScreen.Home.route, arguments = ShopScreen.Home.navArguments) {
            ShopHomeScreen {
                navController.navigate(
                    ShopScreen.Home.createRouteToProduct(
                        category = Uri.encode(Gson().toJson(it))
                    )
                )
            }
        }
        composable(
            route = ShopScreen.ProductList.route,
            arguments = ShopScreen.ProductList.navArguments
        ) {
            val json = it.arguments?.getString("category")
            val productListObject = if (!json.isNullOrEmpty()) {
                Gson().fromJson(json, CategoryList::class.java)
            } else {
                CategoryList()
            }

            ProductScreen(productListObject, onBackClick = {
                navController.navigateUp()
            }) {
                navController.navigate(
                    ShopScreen.ProductList.createRouteToDetail(
                        detail = Uri.encode(Gson().toJson(it))
                    )
                )
            }
        }
        composable(
            route = ShopScreen.ProductDetail.route,
            arguments = ShopScreen.ProductDetail.navArguments
        ) {
            val json = it.arguments?.getString("detail")
            val product = if (!json.isNullOrEmpty()) {
                Gson().fromJson(json, ProductModel::class.java)
            } else {
                ProductModel()
            }
            DetailScreen(product,
                onBackClick = { navController.navigateUp() }
            ) {
                createShareIntent(activity, it)
            }
        }

    }
}

// Helper function for calling a share functionality.
// Should be used when user presses a share button/menu item.
private fun createShareIntent(activity: Activity, plantName: String) {
    val shareText = activity.getString(R.string.app_name).toString() + " $plantName"
    val shareIntent = ShareCompat.IntentBuilder(activity)
        .setText(shareText)
        .setType("text/plain")
        .createChooserIntent()
        .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
    activity.startActivity(shareIntent)
}

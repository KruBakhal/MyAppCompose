package com.example.myappcompose.resolve_parking.compose_ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myappcompose.R
import com.example.myappcompose.resolve_parking.data.models.DrawerMenu
import com.example.myappcompose.resolve_parking.data.models.TicketData
import com.example.myappcompose.resolve_parking.theme.button_self_normal_color
import com.example.myappcompose.resolve_parking.theme.control_box_outline
import com.example.myappcompose.resolve_parking.theme.gray_color
import com.example.myappcompose.resolve_parking.theme.green_regular
import com.example.myappcompose.resolve_parking.theme.hint_color
import com.example.myappcompose.resolve_parking.theme.red_regular
import com.example.myappcompose.ui.theme.fontFamily


@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier.background(Color.White), containerColor = Color.White
    ) { contentPadding ->
        HomeScreenContent(contentPadding.calculateTopPadding(), Modifier)
    }
}

@Composable
fun HomeScreenContent(contentPadding: Dp, modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        MainNavigation()
    }
}

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    val parkingData = listOf<TicketData>(
        TicketData(
            1,
            "D5-231-0194-E1",
            30.0,
            "23:59:00",
            2,
            "2024-09-14T05:31:42-07:00",
            "2024-09-14T23:59:00-07:00",
            ticketSerialNo = "D5-258-0306",
            isPaymentRequired = false,
            durationStr = "All Day"
        ), TicketData(
            2,
            "D5-231-0194-E1",
            30.0,
            "23:59:00",
            2,
            "2024-09-14T05:31:42-07:00",
            "2024-09-14T23:59:00-07:00",
            ticketSerialNo = "D5-258-0306",
            isPaymentRequired = false,
            durationStr = "All Day"
        ), TicketData(
            3,
            "D5-231-0194-E1",
            30.0,
            "23:59:00",
            2,
            "2024-09-14T05:31:42-07:00",
            "2024-09-14T23:59:00-07:00",
            ticketSerialNo = "D5-258-0306",
            isPaymentRequired = false,
            durationStr = "All Day"
        )
    )
    val menus = arrayOf(
        DrawerMenu(R.drawable.ic_ticket, "Add Ticket", "MainRoute.Articles.name"),
        DrawerMenu(R.drawable.img_exit, "Exit Shift", "MainRoute.Settings.name"),
        DrawerMenu(R.drawable.ic_end_shift, "End Shift", "MainRoute.About.name"),
        DrawerMenu(R.drawable.img_report, "Report", "MainRoute.About.name"),
        DrawerMenu(R.drawable.ic_setting, "Settings", "MainRoute.About.name")
    )
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet {
            DrawerContent(menus) { route ->
//                    navController.navigate(route)
            }
        }
    }) {
        val constraintSet = ConstraintSet() {
            val layTop = createRefFor("layTop")
            val layBottom = createRefFor("layBottom")
            val layBottom1 = createRefFor("layBottom1")
            constrain(layTop) {
                top.linkTo(parent.top)
                bottom.linkTo(layBottom.top)
                height = Dimension.fillToConstraints
            }
            constrain(layBottom) {
                bottom.linkTo(layBottom1.top)
            }
            constrain(layBottom1) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }
        }
        ConstraintLayout(
            constraintSet,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("layTop"),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(id = R.drawable.navigation_menu),
                        modifier = Modifier.size(50.dp),
                        contentDescription = null
                    )
                    Text(
                        text = "Home",
                        modifier = Modifier.wrapContentWidth(),
                        textAlign = TextAlign.Start,
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold, maxLines = 1
                    )
                    Image(
                        painterResource(id = R.drawable.ic_search),
                        modifier = Modifier
                            .size(30.dp),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = stringResource(R.string.recent_tickets),
                        modifier = Modifier.wrapContentWidth(),
                        textAlign = TextAlign.Center,
                        color = gray_color,
                        fontSize = TextUnit(22f, TextUnitType.Sp),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold, maxLines = 1
                    )
                    Text(
                        text = "Loading..",
                        modifier = Modifier.weight(1f),
                        fontSize = 14.sp,
                        textAlign = TextAlign.End,
                        color = gray_color
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painterResource(id = R.drawable.baseline_refresh_24),
                        modifier = Modifier
                            .size(30.dp)
                            .padding(4.dp),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(modifier = Modifier
                    .fillMaxWidth(), content = {
                    items(parkingData.distinct(), key = { task -> task.id!! }) { data ->
                        data.getGracePeriod()

                        ParkingItem(
                            data.ticketSerialNo.toString(),
                            data.licensePlateNo.toString(),
                            data.getTimeString(),
                            data.durationStr.toString(),
                            data.getPrice(),
                            data.isPaymentRequired
                        )
                        Divider(
                            modifier = Modifier.height(5.dp),
                            color = Color.Transparent
                        )
                    }

                })

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("layBottom"),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = red_regular)
                ) {
                    Text(
                        text = stringResource(id = R.string.close_ticket)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = green_regular)
                ) {
                    Text(text = stringResource(id = R.string.new_ticket_btn))
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("layBottom1")
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults
                        .buttonColors(containerColor = button_self_normal_color)
                ) {
                    Text(text = stringResource(id = R.string.scan_ticket_btn))
                }
            }
        }


    }
}


@Composable
fun ParkingItem(
    id: String, license: String, time: String, duration: String, cost: String, isActive: Boolean
) {
    val backgroundColor =
        if (isActive) Color(0xFF00C853) else Color(0xFFD32F2F) // Green if active, red if not

    Card(
        modifier = Modifier
            .fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, control_box_outline)
    ) {
        val constraintSet = ConstraintSet {
            val layLeft = createRefFor("lay_left")
            val layCenter = createRefFor("lay_center")
            val layEnd = createRefFor("lay_end")
            val layDivder = createRefFor("lay_diveder")
            constrain(layLeft) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(layCenter.start)
                width = Dimension.fillToConstraints
            }
            constrain(layCenter) {
                top.linkTo(layLeft.top)
                bottom.linkTo(layLeft.bottom)
                start.linkTo(layLeft.end)
                end.linkTo(layDivder.start)
                height = Dimension.fillToConstraints
            }

            constrain(layDivder) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(layCenter.end)
                end.linkTo(layEnd.start)
                height = Dimension.fillToConstraints
            }
            constrain(layEnd) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
            createHorizontalChain(
                layLeft, layCenter, layDivder, layEnd, chainStyle = ChainStyle.Packed
            )
        }

        ConstraintLayout(
            constraintSet, modifier = Modifier.fillMaxSize()
        ) {

            // ID and License column
            Column(
                modifier = Modifier.layoutId("lay_left"), verticalArrangement = Arrangement.Center
            ) {
                // Row for ID with colored background
                Box(
                    modifier = Modifier
                        .background(
                            backgroundColor, shape = RoundedCornerShape(8.dp, 0.dp, 8.dp, 0.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = id, color = Color.White, fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Lic: $license",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }


            // Time and Duration column
            Column(
                modifier = Modifier
                    .layoutId("lay_center")
                    .fillMaxHeight()
                    .padding(0.dp, 4.dp, 8.dp, 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = time, style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Dur: $duration",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            Divider(
                color = control_box_outline, modifier = Modifier
                    .layoutId("lay_diveder")
                    .width(1.dp)
            )
            // Cost column
            Column(
                modifier = Modifier
                    .layoutId("lay_end")
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = cost,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}


@Composable
private fun DrawerContent(
    menus: Array<DrawerMenu>, onMenuClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(id = R.drawable.splash_logo),
                modifier = Modifier
                    .width(150.dp)
                    .border(
                        2.dp, control_box_outline, RoundedCornerShape(10.dp)
                    ),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        menus.forEach {
            NavigationDrawerItem(label = { Text(text = it.title) }, icon = {
                Icon(
                    painterResource(id = it.icon),
                    modifier = Modifier.size(20.dp),
                    contentDescription = null,
                    tint = Color.Black
                )
            }, selected = false, onClick = {
                onMenuClick(it.route)
            })
        }
    }
}

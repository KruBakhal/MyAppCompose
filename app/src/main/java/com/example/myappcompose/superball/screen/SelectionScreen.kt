package com.example.myappcompose.superball.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.practicesession.superballgame.model.BallTypeModel
import com.example.practicesession.superballgame.model.SuperBallModel
import com.example.practicesession.superballgame.viewmodel.SelectionViewModel

@Composable
fun SelectionScreenFunc(
    model: SuperBallModel?,
    isUpdate: Boolean? = false,
    onBackClick: () -> Boolean,
    onSaveClick: () -> Unit

) {
    model?.let {
        val viewModel: SelectionViewModel = hiltViewModel()
        val mutableState by viewModel.saveStatus.collectAsState()
        LaunchedEffect(key1 = mutableState) {
            if (mutableState) {
                onBackClick.invoke()
            }
        }
        LaunchedEffect(true) {
            viewModel.superBallModel = model
            viewModel.setupData(model.maxNormal, model.maxSuper)
            if (isUpdate == true) viewModel.syncWithDBGameData()
        }
        Scaffold(modifier = Modifier, topBar = {
            TopBarAddSave(2, onBackClick = {
                onBackClick.invoke()
            }, onSaveAddClick = {
                viewModel.saveToDB()
            })
        }) { contentPadding ->
            model.let {
                Box(
                    modifier = Modifier
                        .padding(contentPadding)
                ) {
                    SelectionBody(Modifier, it, viewModel, isUpdate)
                }
            }
        }
    }
}

@Composable
fun SelectionBody(
    modifier: Modifier = Modifier,
    model: SuperBallModel,
    selectionViewModel: SelectionViewModel,
    isUpdate: Boolean? = false
) {
    setupFunc(modifier, selectionViewModel, model)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun setupFunc(modifier: Modifier, selectionViewModel: SelectionViewModel, model: SuperBallModel) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Select Total: ${(model.maxNormal + model.maxSuper)}",
                color = Color.Black,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 4) //grid size
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                        .padding(5.dp),
                    maxItemsInEachRow = 4,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    showGridLayout(
                        selectionViewModel.listSelected,
                        itemSize
                    ) { it, model ->
                        false
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Normal Range: ${(model.nRangeMin)} to  ${(model.nRangeMax)} Max: ${model.maxNormal}",
                color = Color.Black,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {

                val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 4) //grid size
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                        .padding(5.dp),
                    maxItemsInEachRow = 4,
                    verticalArrangement = Arrangement.Center
                ) {
                    showGridLayout(selectionViewModel.listNormal, itemSize) { it, model ->
                        if (model.selected) {
                            selectionViewModel.addToNormal(it, model)
                            true
                        } else if (selectionViewModel.countNormal < selectionViewModel.maxNormal) {
                            selectionViewModel.addToNormal(it, model)
                            true
                        } else {
                            false
                        }

                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Super Range: ${(model.sRangeMin)} to  ${(model.sRangeMax)} Max: ${model.maxSuper}",
                color = Color.Black,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 4) //grid size
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                        .padding(5.dp),
                    maxItemsInEachRow = 4,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    showGridLayout(selectionViewModel.listSuper, itemSize) { it, model ->
                        if (model.selected) {
                            selectionViewModel.addToSuper(it, model)
                            true
                        } else if (selectionViewModel.countSuper < selectionViewModel.maxSuper) {
                            selectionViewModel.addToSuper(it, model)
                            true
                        } else {
                            false
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun showGridLayout(
    listSelected: SnapshotStateList<BallTypeModel>,
    itemSize: Dp,
    onClick: (Int, BallTypeModel) -> Boolean
) {
    /* LazyVerticalGrid(
         modifier = Modifier
             .fillMaxWidth()
             .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
             .padding(5.dp),
         columns = GridCells.Fixed(4),
         verticalArrangement = Arrangement.spacedBy(16.dp),
         horizontalArrangement = Arrangement.spacedBy(16.dp)

     ) {
         items(listSelected) { item ->
             ItemBall(ballTypeModel = item) {
                 return@ItemBall onClick.invoke(0, it)
             }
         }
     }*/

    /*
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        for (i in 0..listSelected.size step 4) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (j in i..(i + 4)) {
                    if (j < listSelected.size)
                        ItemBall(ballTypeModel = listSelected.get(j), itemSize = itemSize) {
                            return@ItemBall onClick.invoke(0, it)
                        }
                }
            }
        }
    }
*/

    for (ballTypeModel in listSelected) {
        ItemBall(ballTypeModel = ballTypeModel, itemSize = itemSize) {
            return@ItemBall onClick.invoke(0, it)
        }
    }

}



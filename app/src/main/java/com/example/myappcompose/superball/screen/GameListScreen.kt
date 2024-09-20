package com.example.myappcompose.superball.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import com.example.practicesession.superballgame.model.SuperBallModel
import com.example.practicesession.superballgame.viewmodel.GameListViewModel

@Composable
public fun GamesListScreen(
    model: SuperBallModel?,
    onBackClick: () -> Boolean,
    onMoveToSelection: (SuperBallModel, Boolean) -> Unit
) {
    val viewModel: GameListViewModel = hiltViewModel()
    viewModel.superBallModel = model
    viewModel.getGameList()
    Scaffold(modifier = Modifier,
        topBar = {
            TopBarAddSave(1, onBackClick = {
                onBackClick.invoke()
            }, onSaveAddClick = {
                model?.let { onMoveToSelection.invoke(it, false) }
            })
        }) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(), Arrangement.Top,
            Alignment.CenterHorizontally
        ) {

            GamePart(Modifier, viewModel.listOfGame, onEditClick = {
                onMoveToSelection.invoke(it, true)
            }) {
                viewModel.deleteModel(it)
            }

        }
    }
}

@Composable
fun GamePart(
    modifier: Modifier = Modifier,
    listOfGame: LiveData<List<SuperBallModel>>,
    onEditClick: (SuperBallModel) -> Unit,
    onDeleteClick: (SuperBallModel) -> Unit,
) {
    listOfGame.value?.let {
        LazyColumn(modifier = modifier.padding(10.dp, 0.dp),
            content = {
                items(it.distinct(), key = { task -> task.id }) { model ->
                    ItemGame(model, Modifier, onEditClick, onDeleteClick)
                    Spacer(modifier = Modifier.height(20.dp))
                }
            })
    }
}

@Composable
fun ItemGame(
    superBallModel: SuperBallModel,
    modifier: Modifier = Modifier,
    onEditClick: ((SuperBallModel) -> Unit)?,
    onDeleteClick: ((SuperBallModel) -> Unit)?
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "${superBallModel.gameName}",
                color = Color.Black, textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyRow(content = {
                items(
                    superBallModel.selectedList!!.toList(), key = { it -> it.number }
                ) { it ->
                    ItemBall(Modifier, ballTypeModel=it,itemSize=0.dp)
                    Spacer(modifier = Modifier.width(10.dp))
                }


            }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(20.dp))
            onEditClick?.let {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_edit),
                        contentDescription = "back", tint = Color.Black,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                onEditClick?.invoke(superBallModel)
                            }
                    )
                    Spacer(modifier = Modifier.width(20.dp))

                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_delete),
                        contentDescription = "back", tint = Color.Black,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                onDeleteClick?.invoke(superBallModel)
                            }
                    )

                }
            }
        }

    }

}



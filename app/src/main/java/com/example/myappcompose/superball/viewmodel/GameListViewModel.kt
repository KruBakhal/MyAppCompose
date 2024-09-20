package com.example.practicesession.superballgame.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicesession.superballgame.database.SuperBallDBImpl
import com.example.practicesession.superballgame.model.BallTypeModel
import com.example.practicesession.superballgame.model.SuperBallModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(val superBallDBImpl: SuperBallDBImpl) : ViewModel() {

    var superBallModel: SuperBallModel? = null
    val _listOfGame = MutableLiveData<List<SuperBallModel>>(arrayListOf())
    val listOfGame: LiveData<List<SuperBallModel>> get() = _listOfGame

    /*  init {
          val list = arrayListOf<SuperBallModel>()
          for (i in 1..8) {
              val selected = arrayListOf<BallTypeModel>()
              for (j in 1..i) {
                  selected.add(BallTypeModel(true, j, true))
              }
              list.add(SuperBallModel(i, "Game-$i", selected, 5, 2, 1, 20, 1, 5))
          }
          _listOfGame.value=(list)
      }*/

    fun getGameList() {
        viewModelScope.launch {
            val list = superBallDBImpl.getSpecificGameList(superBallModel!!.gameName)
            list?.let {
                if (it.size != 0)
                    _listOfGame.postValue(list)
            }

        }
    }

    fun deleteModel(superBallModel: SuperBallModel) {
        viewModelScope.launch {
            superBallDBImpl.deleteSavedNews(superBallModel)
        }
    }

}
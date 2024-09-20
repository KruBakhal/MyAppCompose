package com.example.practicesession.superballgame.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicesession.superballgame.database.SuperBallDBImpl
import com.example.practicesession.superballgame.model.BallTypeModel
import com.example.practicesession.superballgame.model.SuperBallModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
public class SelectionViewModel @Inject constructor(val databaseImpl: SuperBallDBImpl) :
    ViewModel() {

    private var isDatabaseFound: Boolean = false
    var superBallModel: SuperBallModel? = null
    val listNormal = mutableStateListOf<BallTypeModel>()
    val listSuper = mutableStateListOf<BallTypeModel>()
    var listSelected = mutableStateListOf<BallTypeModel>()

    val updateNormal = MutableStateFlow<Boolean>(false);
    val updateSuper = MutableStateFlow<Boolean>(false);
    val updateSelected = MutableStateFlow<Boolean>(false);
    val saveStatus = MutableStateFlow<Boolean>(false);


    var countNormal = 0
    var countSuper = 0
    var maxNormal = 0
    var maxSuper = 0

    fun setupData(normal: Int, supers: Int) {
        viewModelScope.launch {
            listNormal.clear()
            listSuper.clear()
            listSelected.clear()
            maxNormal = normal
            maxSuper = supers
            if (superBallModel?.nRangeMax != 0)
                for (index: Int in superBallModel!!.nRangeMin..superBallModel!!.nRangeMax) {
                    listNormal.add(BallTypeModel(true, index, false))
                }
            if (superBallModel?.sRangeMax != 0) for (index: Int in superBallModel!!.sRangeMin..superBallModel!!.sRangeMax) {
                listSuper.add(BallTypeModel(false, index, false))
            }
            withContext(Main) {
                updateNormal.value = (true)
                updateSelected.value = (true)
                updateSuper.value = (true)

            }
        }
    }

    fun syncWithDBGameData() {
        viewModelScope.launch {
//            superBallModel = databaseImpl.getArticleStatus(superBallModel!!)

            withContext(IO) {
                if (superBallModel != null) {
                    isDatabaseFound = true
                    listSelected.addAll(superBallModel?.selectedList!!);

                    listSelected?.let { selectedList ->
                        listNormal.modifyEachIndexed { index, item ->
                            selectedList.find { it.isNormal && it.number == item.number && it.selected }
                                ?.let { item.selected = true }
                        }
                        countNormal = listNormal.toList().count { it.selected }

                        listSuper.modifyEachIndexed { index, item ->
                            selectedList.find { !it.isNormal && it.number == item.number && it.selected }
                                ?.let { item.selected = true }
                        }
                        countSuper = listSuper.toList().count { it.selected }
                    }

                    updateNormal.value = (true)
                    updateSelected.value = (true)
                    updateSuper.value = (true)
                }
            }
        }

    }

    inline fun <T> MutableList<T>.modifyEachIndexed(mutator: (index: Int, item: T) -> Unit) {
        forEachIndexed { index, item -> mutator(index, item) }
    }

    fun addToNormal(position1: Int, model: BallTypeModel) {
        viewModelScope.launch {
            var position = listNormal.indexOf(model)
            var isRemoved = false
            listSelected.find { ballTypeModel ->

                (ballTypeModel.number == model.number && ballTypeModel.isNormal == model.isNormal)
            }?.let {
                if (it.selected) {
                    listSelected.remove(it)
                    isRemoved = true
                }
            }
            if (isRemoved) {
                countNormal = listSelected.filter { it.isNormal }.count()
                model.selected = !model.selected
                listNormal.set(position, model)
            } else if (countNormal < maxNormal) {
                model.selected = !model.selected
                listNormal.set(position, model)
                listSelected.add(model)
            }
            countNormal = listSelected.toList().filter { it.isNormal }.count()
            updateNormal()
        }
    }

    private suspend fun updateNormal() {
        withContext(Main) {
            updateSelected.value = (true)
            updateNormal.value = (true)
        }
    }

    private suspend fun updateSuper() {
        withContext(Main) {
            updateSelected.value = (true)
            updateSuper.value = (true)
        }
    }

    fun addToSuper(position1: Int, model: BallTypeModel) {
        viewModelScope.launch {
            var position = listSuper.indexOf(model)
            var isRemoved = false
            listSelected.find { ballTypeModel ->
                (ballTypeModel.number == model.number && ballTypeModel.isNormal == model.isNormal)
            }?.let {
                if (it.selected) {
                    listSelected.remove(it)
                    isRemoved = true
                }
            }
            if (isRemoved) {
                countSuper = listSelected.filter { !it.isNormal }.count()
                model.selected = !model.selected
                listSuper.set(position, model)

            } else if (countSuper < maxSuper) {
                model.selected = !model.selected
                listSuper.set(position, model)
                listSelected.add(model)

            }
            countSuper = listSelected.toList().filter { !it.isNormal }.count()
            updateSuper()
        }
    }

    private fun calculateSelectedBall(isNormal: Boolean) {

        if (isNormal) {
            listNormal.forEach { it ->
                if (it.selected) {
                    listSelected.add(it)
                }
            }
            updateNormal.value = (true)
        } else {
            listSuper.forEach { it ->
                if (it.selected) {
                    listSelected.add(it)
                }
            }
            updateSuper.value = (true)
        }
        updateSelected.value = (true)
    }

    fun saveToDB() {
        viewModelScope.launch {
            superBallModel?.let {
                it.selectedList = arrayListOf()
                it.selectedList!!.addAll(listSelected.toList())
                val long = databaseImpl.insertNews(it)
                Log.d("TAG", "getGameList: " + databaseImpl.getList().toString())
                saveStatus.value = (true)
            }
        }
    }
}
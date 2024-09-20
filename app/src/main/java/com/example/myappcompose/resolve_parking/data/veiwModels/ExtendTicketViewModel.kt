package com.example.myappcompose.resolve_parking.data.veiwModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myappcompose.resolve_parking.data.models.ErrorResponse
import com.example.myappcompose.resolve_parking.data.models.SuccessResponse
import com.example.myappcompose.resolve_parking.data.models.PayRequestModel
import com.example.myappcompose.resolve_parking.data.models.TicketPrints
import com.example.myappcompose.resolve_parking.data.repository.TicketHistoryRepository
import kotlinx.coroutines.launch


class ExtendTicketViewModel(private val ticketRepository: TicketHistoryRepository) : ViewModel() {

    val showToast = MutableLiveData<String>()
    var userToken: String? = null
    val apiDataList = MutableLiveData<Boolean>()
    val showProgress = MutableLiveData(false)
    val authentication = MutableLiveData(false)


    fun callExtendTicketDuration(id: Int, request: PayRequestModel) {
        viewModelScope.launch {
            showProgress.postValue(true)
            val data = ticketRepository.getPayOnExitOrExceed(userToken!!, id.toString(), request)
            showProgress.postValue(false)
            when (data) {
                is SuccessResponse -> {
                    apiDataList.postValue(true)
                }

                is ErrorResponse -> {
                    showToast.postValue(data.message)
                    apiDataList.postValue(false)
                    data.statusCode?.let {
                        if (it == 401) {
                            authentication.postValue(true)
                        }
                    }
                }
            }

        }
    }
    fun callReprintExtendTicket(id: Int, request: TicketPrints) {
        viewModelScope.launch {
            showProgress.postValue(true)
            val data = ticketRepository.getExtendTicketReprint(userToken!!, id.toString(), request)
            showProgress.postValue(false)
            when (data) {
                is SuccessResponse -> {
                    apiDataList.postValue(true)
                    showToast.postValue(data.data.message.toString())
                }

                is ErrorResponse -> {
                    showToast.postValue(data.message)
                    apiDataList.postValue(false)
                    data.statusCode?.let {
                        if (it == 401) {
                            authentication.postValue(true)
                        }
                    }
                }
            }

        }
    }

}
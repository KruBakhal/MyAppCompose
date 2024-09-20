package com.example.myappcompose.resolve_parking.data.veiwModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myappcompose.resolve_parking.data.models.ApiResponse
import com.example.myappcompose.resolve_parking.data.models.DeviceConfigValueResponse
import com.example.myappcompose.resolve_parking.data.models.ErrorResponse
import com.example.myappcompose.resolve_parking.data.models.Response
import com.example.myappcompose.resolve_parking.data.models.SuccessResponse
import com.example.myappcompose.resolve_parking.data.models.TicketHistoryModel
import com.example.myappcompose.resolve_parking.data.repository.LookupRepository
import com.example.myappcompose.resolve_parking.data.repository.TicketHistoryRepository
import com.example.myappcompose.resolve_parking.utils.CommonUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TicketHistoryViewModel(
    private val ticketRepository: TicketHistoryRepository,
    private val lookRepository: LookupRepository,
) : ViewModel() {


    private var job: Job? = null
    var isOpenPosition: Int = 0
    val responseData = MutableLiveData<TicketHistoryModel?>()
    val showProgress = MutableLiveData(false)
    val showToast = MutableLiveData<String>(null)
    val authentication = MutableLiveData(false)
    private val _deviceConfigValuesLiveData =
        MutableLiveData<ApiResponse<DeviceConfigValueResponse>?>()
    val deviceConfigValuesLiveData: LiveData<ApiResponse<DeviceConfigValueResponse>?> get() = _deviceConfigValuesLiveData

    fun onStartApiCalls(
        authToken: String
    ) {
        viewModelScope.launch {
            showProgress.postValue(true)
            val resultConfig = this.async { lookRepository.getDeviceConfigValue(authToken) }
            val deviceConfigValue = resultConfig.await()
            _deviceConfigValuesLiveData.value = deviceConfigValue
            _deviceConfigValuesLiveData.postValue(deviceConfigValue)
            if (deviceConfigValue.statusCode == 401) {
                authentication.postValue(true)
            }
            val resultTicketList = this.async {
                ticketRepository.searchTicket(
                    authToken,
                    isOpen = true, null, "1", "20", "1",
                    orderByDirection = 1, null
                )
            }
            val responseTicketList = resultTicketList.await()
            showProgress.postValue(false)
            assignDataToTicketList(responseTicketList)
        }
    }


    fun searchTicket(
        authToken: String,
        page: Int,
        pageSize: Int,
        orderBy: String,
        orderByDirection: Int,
        isSearchActive: Boolean,
        searchText: String?
    ) {
        job = viewModelScope.launch {
            var isOpen: Boolean? = null
            var isVoid: Boolean? = null
            when (isOpenPosition) {
                0 -> {// open
                    isOpen = true
                    isVoid = false
                }

                1 -> {// close

                    isOpen = false
                    isVoid = false
                }

                2 -> {// void

                    isOpen = null
                    isVoid = true
                }

                3 -> {// all

                    isOpen = null
                    isVoid = null
                }
            }
            val returnResponse = ticketRepository.searchTicket(
                authToken,
                isOpen = isOpen, isVoid = isVoid, page.toString(), pageSize.toString(), orderBy,
                orderByDirection = orderByDirection, if (isSearchActive) searchText else null
            )
            assignDataToTicketList(returnResponse)
        }
    }

    fun recentTicket(authToken: String) {
        job = viewModelScope.launch {
            val isOpen = true
            val isVoid: Boolean? = null
            showProgress.postValue(true)
            val returnResponse = ticketRepository.searchTicket(
                authToken,
                isOpen = isOpen, isVoid, "1", "20", "1",
                orderByDirection = 1, null
            )
            assignDataToTicketList(returnResponse)
        }
    }

    fun cancelCoRoutine() {
        job?.cancel()
        responseData.postValue(null)
    }

    private fun assignDataToTicketList(responseTicketList: Response<TicketHistoryModel>) {
        when (responseTicketList) {
            is SuccessResponse -> {
                showProgress.postValue(false)
                responseData.postValue(responseTicketList.data)
            }

            is ErrorResponse -> {
                showProgress.postValue(false)
                responseData.postValue(null)
                if (responseTicketList.statusCode == 401) {
                    authentication.postValue(true)
                }
            }
        }
    }

    fun getDeviceConfigValue(authToken: String) {
        viewModelScope.launch {
            _deviceConfigValuesLiveData.value = null
            try {
                val deviceConfigValue = lookRepository.getDeviceConfigValue(authToken)
                _deviceConfigValuesLiveData.value = deviceConfigValue
                if (deviceConfigValue.statusCode == 401) {
                    authentication.postValue(true)
                }
            } catch (e: retrofit2.HttpException) {
                // Handle HTTP errors
                val errorMessage = CommonUtil.handleServerErrorResponse(e)
                _deviceConfigValuesLiveData.value =
                    ApiResponse(false, null, errorMessage.supportMessage)
            } catch (e: Exception) {
                // Handle other exceptions
                _deviceConfigValuesLiveData.value = ApiResponse(false, null, "${e.message}")
            }
        }
    }
}
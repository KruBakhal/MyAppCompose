package com.example.myappcompose.resolve_parking.data.veiwModels

import com.example.myappcompose.resolve_parking.utils.CommonUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myappcompose.resolve_parking.data.models.ApiResponse
import com.example.myappcompose.resolve_parking.data.models.TicketRequest
import com.example.myappcompose.resolve_parking.data.models.TicketResponse
import com.example.myappcompose.resolve_parking.data.repository.TicketRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TicketViewModel(private val ticketRepository: TicketRepository) : ViewModel() {


    private val _ticketInfoLiveData = MutableLiveData<ApiResponse<TicketResponse>?>()
    val ticketInfoLiveData: LiveData<ApiResponse<TicketResponse>?> get() = _ticketInfoLiveData
    val showProgress = MutableLiveData(false)
    fun createTicket(authToken: String, ticketRequest: TicketRequest) {
        viewModelScope.launch {
            showProgress.postValue(true)
            _ticketInfoLiveData.value = null
            try {
                val deviceConfigValue = ticketRepository.createTicket(authToken, ticketRequest)
                showProgress.postValue(false)
                _ticketInfoLiveData.value = deviceConfigValue
            } catch (e: HttpException) {
                // Handle HTTP errors
                showProgress.postValue(false)
                val errorMessage = CommonUtil.getErrorMessage(e)
                _ticketInfoLiveData.value = ApiResponse(false, null, errorMessage)
            } catch (e: Exception) {
                // Handle other exceptions
                showProgress.postValue(false)
                _ticketInfoLiveData.value = ApiResponse(false, null, "${e.message}")
            }
        }
    }

}
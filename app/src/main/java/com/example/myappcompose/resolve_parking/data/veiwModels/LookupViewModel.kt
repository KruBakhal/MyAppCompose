package com.example.myappcompose.resolve_parking.data.veiwModels

import com.example.myappcompose.resolve_parking.utils.CommonUtil.handleServerErrorResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myappcompose.resolve_parking.data.models.ApiResponse
import com.example.myappcompose.resolve_parking.data.models.DeviceConfigValueResponse
import com.example.myappcompose.resolve_parking.data.repository.LookupRepository
import kotlinx.coroutines.launch

class LookupViewModel(private val lookupRepository: LookupRepository) : ViewModel() {


    private val _deviceConfigValuesLiveData =
        MutableLiveData<ApiResponse<DeviceConfigValueResponse>?>()
    val deviceConfigValuesLiveData: LiveData<ApiResponse<DeviceConfigValueResponse>?> get() = _deviceConfigValuesLiveData
    val authentication = MutableLiveData(false)

    fun getDeviceConfigValue(authToken: String) {
        viewModelScope.launch {
            _deviceConfigValuesLiveData.value = null
            try {
                val deviceConfigValue = lookupRepository.getDeviceConfigValue(authToken)
                _deviceConfigValuesLiveData.value = deviceConfigValue
                if (deviceConfigValue.statusCode == 401) {
                    authentication.postValue(true)
                }
            } catch (e: retrofit2.HttpException) {
                // Handle HTTP errors
                val errorMessage = handleServerErrorResponse(e)
                _deviceConfigValuesLiveData.value =
                    ApiResponse(false, null, errorMessage.supportMessage)
            } catch (e: Exception) {
                // Handle other exceptions
                _deviceConfigValuesLiveData.value = ApiResponse(false, null, "${e.message}")
            }
        }
    }

}
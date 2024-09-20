package com.example.myappcompose.resolve_parking.data.veiwModels

import com.example.myappcompose.resolve_parking.utils.CommonUtil
import com.example.myappcompose.resolve_parking.data.repository.ShiftRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myappcompose.resolve_parking.data.models.SuccessResponse
import com.example.myappcompose.resolve_parking.data.models.ActiveShiftResponse
import com.example.myappcompose.resolve_parking.data.models.ApiResponse
import com.example.myappcompose.resolve_parking.data.models.EndShift
import com.example.myappcompose.resolve_parking.data.models.ErrorResponse
import com.example.myappcompose.resolve_parking.data.models.ForceEndShiftRequest
import com.example.myappcompose.resolve_parking.data.models.ShiftResponse
import com.example.myappcompose.resolve_parking.data.models.ShiftSummary
import com.example.myappcompose.resolve_parking.data.models.VerifyPinResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ShiftViewModel(private val shiftRepository: ShiftRepository) : ViewModel() {
    private val _startShiftLiveData =
        MutableLiveData<ApiResponse<ShiftResponse>?>()
    val startShiftLiveData: LiveData<ApiResponse<ShiftResponse>?> get() = _startShiftLiveData

    private val _endShiftLiveData = MutableLiveData<ApiResponse<EndShift>?>()
    val endShiftLiveData: LiveData<ApiResponse<EndShift>?> get() = _endShiftLiveData

    private val _shiftSummaryLiveData = MutableLiveData<ApiResponse<ShiftSummary>?>()
    val shiftSummaryLiveData: LiveData<ApiResponse<ShiftSummary>?> get() = _shiftSummaryLiveData

    private val _verifyPinLiveData =
        MutableLiveData<ApiResponse<VerifyPinResponse>?>()
    val verifyPinLiveData: LiveData<ApiResponse<VerifyPinResponse>?> get() = _verifyPinLiveData

    private val _activeShiftLiveData =
        MutableLiveData<ApiResponse<ActiveShiftResponse>?>()
    val activeShiftLiveData: LiveData<ApiResponse<ActiveShiftResponse>?> get() = _activeShiftLiveData
    val showToastDialog = MutableLiveData<String>()
    val showProgressDialog = MutableLiveData(false)
    val ticketClose = MutableLiveData(false)
    val authentication = MutableLiveData(false)
    fun verifyPin(deviceSerialNo: String, clientSecret: String, devicePIN: String) {
        viewModelScope.launch {
            try {
                val response = shiftRepository.verifyPin(deviceSerialNo, clientSecret, devicePIN)
                _verifyPinLiveData.value = response
            } catch (e: HttpException) {
                // Handle HTTP errors
                val errorMessage = CommonUtil.getErrorMessage(e)
                _verifyPinLiveData.value = ApiResponse(false, null, errorMessage)
            } catch (e: Exception) {
                // Handle other exceptions
                _verifyPinLiveData.value = ApiResponse(false, null, "${e.message}")
            }
        }
    }

    fun getActiveShift(deviceSerialNo: String, clientSecret: String) {
        viewModelScope.launch {
            try {
                val response = shiftRepository.getActiveShift(deviceSerialNo, clientSecret)
                _activeShiftLiveData.value = response
                response.statusCode?.let {
                    if (it == 401) {
                        authentication.postValue(true)
                    }
                }
            } catch (e: HttpException) {
                // Handle HTTP errors
                val errorMessage = CommonUtil.getErrorMessage(e)
                _activeShiftLiveData.value = ApiResponse(false, null, errorMessage)
            } catch (e: Exception) {
                // Handle other exceptions
                _activeShiftLiveData.value = ApiResponse(false, null, "${e.message}")
            }
        }
    }

    fun startShift(deviceSerialNo: String, clientSecret: String) {
        viewModelScope.launch {
            try {
                val shift = shiftRepository.startShift(deviceSerialNo, clientSecret)
                _startShiftLiveData.value = shift
                shift.statusCode?.let {
                    if (it == 401) {
                        authentication.postValue(true)
                    }
                }
            } catch (e: HttpException) {
                // Handle HTTP errors
                val errorMessage = CommonUtil.getErrorMessage(e)
                _startShiftLiveData.value = ApiResponse(false, null, errorMessage)
            } catch (e: Exception) {
                // Handle other exceptions
                _startShiftLiveData.value = ApiResponse(false, null, "${e.message}")
            }
        }
    }

    fun endShift(deviceSerialNo: String, authToken: String) {
        viewModelScope.launch {
            try {
                val endShift = shiftRepository.endShift(deviceSerialNo, authToken)
                _endShiftLiveData.value = endShift
                endShift.statusCode?.let {
                    if (it == 401) {
                        authentication.postValue(true)
                    }
                }
            } catch (e: HttpException) {
                // Handle HTTP errors
                val errorMessage = CommonUtil.getErrorMessage(e)
                _endShiftLiveData.value = ApiResponse(false, null, errorMessage)
            } catch (e: Exception) {
                // Handle other exceptions
                _endShiftLiveData.value = ApiResponse(false, null, "${e.message}")
            }
        }
    }

    fun forceEndShift(token: String, deviceSerialNo: String, model: ForceEndShiftRequest) {
        viewModelScope.launch {
            showProgressDialog.postValue(true)
            val data = shiftRepository.forceEndShift(token, deviceSerialNo, model)
            showProgressDialog.postValue(false)
            when (data) {
                is SuccessResponse -> {
                    showToastDialog.postValue(data.data.message)
                    ticketClose.postValue(true)
                }

                is ErrorResponse -> {
                    showToastDialog.postValue(data.message)
                    ticketClose.postValue(false)
                    data.statusCode?.let {
                        if (it == 401) {
                            authentication.postValue(true)
                        }
                    }
                }
            }

        }
    }

    fun getShiftSummary(authToken: String) {
        viewModelScope.launch {
            try {
                val shiftSummary = shiftRepository.getShiftSummary(authToken)
                _shiftSummaryLiveData.value = shiftSummary
                shiftSummary.statusCode?.let {
                    if (it == 401) {
                        authentication.postValue(true)
                    }
                }
            } catch (e: HttpException) {
                // Handle HTTP errors
                val errorMessage = CommonUtil.getErrorMessage(e)
                _shiftSummaryLiveData.value = ApiResponse(false, null, errorMessage)

            } catch (e: Exception) {
                // Handle other exceptions
                _shiftSummaryLiveData.value = ApiResponse(false, null, "${e.message}")
            }
        }
    }
}
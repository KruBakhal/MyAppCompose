package com.example.myappcompose.resolve_parking.data.veiwModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import com.example.myappcompose.resolve_parking.data.models.Response
import com.example.myappcompose.resolve_parking.data.models.ShiftResponse
import com.example.myappcompose.resolve_parking.data.models.ShiftSummary
import com.example.myappcompose.resolve_parking.data.models.VerifyPinResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val shiftRepository: ShiftRepository) :
    ViewModel() {
    private val _verifyPinLiveData =
        mutableStateOf<Response<VerifyPinResponse>?>(null)
    val verifyPinLiveData: State<Response<VerifyPinResponse>?> get() = _verifyPinLiveData

    private val _showProgressDialog = mutableStateOf(false)
    val showProgressDialog: State<Boolean> get() = _showProgressDialog

    fun verifyPin(deviceSerialNo: String, clientSecret: String, devicePIN: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _showProgressDialog.value = true
                val response = shiftRepository.verifyPin(deviceSerialNo, clientSecret, devicePIN)
                delay(3000)
                _showProgressDialog.value = false
                _verifyPinLiveData.value = response
            } catch (e: HttpException) {
                // Handle HTTP errors
                _showProgressDialog.value = false
                val errorMessage = CommonUtil.getErrorMessage(e)
                _verifyPinLiveData.value = ErrorResponse("", e)
            }
        }
    }


}
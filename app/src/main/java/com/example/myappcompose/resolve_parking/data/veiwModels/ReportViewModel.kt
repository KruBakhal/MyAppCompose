package com.example.myappcompose.resolve_parking.data.veiwModels

import com.example.myappcompose.resolve_parking.utils.CommonUtil.convertTimeStampToDateString
import com.example.myappcompose.resolve_parking.utils.Constants.DATE_TIME_TIMEZONE_FORMAT
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myappcompose.resolve_parking.data.models.ErrorResponse
import com.example.myappcompose.resolve_parking.data.models.Response
import com.example.myappcompose.resolve_parking.data.models.SuccessResponse
import com.example.myappcompose.resolve_parking.data.models.ReportDataSharedResponse
import com.example.myappcompose.resolve_parking.data.models.ReportModel
import com.example.myappcompose.resolve_parking.data.repository.ReportRepository
import com.example.myappcompose.resolve_parking.utils.CommonUtil
import kotlinx.coroutines.launch

class ReportViewModel(private val repository: ReportRepository) : ViewModel() {

    val showToast = MutableLiveData<String>()
    var userToken: String? = null
    val apiDataList = MutableLiveData<Response<ReportModel>>()
    val splitShiftCallback = MutableLiveData<Response<ReportModel>>()
    val showProgress = MutableLiveData(false)
    var reportModel = ReportModel()
    var splitShiftReportModel = ReportModel()
    val shareReportData = MutableLiveData<ReportDataSharedResponse?>()
    val authentication = MutableLiveData(false)
    fun reportData() {
        viewModelScope.launch {
            showProgress.postValue(true)
            val data = repository.reportTicket(userToken!!)
            showProgress.postValue(false)
            when (data) {
                is SuccessResponse -> {
                    reportModel = data.data
                    apiDataList.postValue(data)
                }

                is ErrorResponse -> {
//                    showToast.postValue(data.message)
                    if (data.statusCode == 401) {
                        authentication.postValue(true)
                    } else {
                        apiDataList.postValue(data)
                    }
                }
            }

        }
    }

    fun splitShiftReportData() {
        viewModelScope.launch {
            showProgress.postValue(true)
            val currentTime= CommonUtil.getCurrentSystemTimeByTimeZone().convertTimeStampToDateString(inputFormat = DATE_TIME_TIMEZONE_FORMAT)
            val data = repository.splitShiftReport(userToken!!,currentTime)
            showProgress.postValue(false)
            when (data) {
                is SuccessResponse -> {
                    splitShiftReportModel = data.data
                    splitShiftCallback.postValue(data)
                }

                is ErrorResponse -> {
//                    showToast.postValue(data.message)
                    if (data.statusCode == 401) {
                        authentication.postValue(true)
                    } else {
                        splitShiftCallback.postValue(data)
                    }
                }
            }
        }
    }

    fun sharedReportData() {
        viewModelScope.launch {
            shareReportData.value = null
            showProgress.postValue(true)
            val data = repository.shareReportData(userToken!!)
            showProgress.postValue(false)
            when (data) {
                is SuccessResponse -> {
                    shareReportData.value = data.data
                }

                is ErrorResponse -> {
                    showToast.postValue(data.message)
                    if (data.statusCode == 401) {
                        authentication.postValue(true)
                    }
                }
            }

        }
    }

}

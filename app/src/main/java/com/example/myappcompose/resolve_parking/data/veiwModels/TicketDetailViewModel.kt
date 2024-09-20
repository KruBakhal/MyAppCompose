package com.example.myappcompose.resolve_parking.data.veiwModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myappcompose.resolve_parking.data.models.Response
import com.example.myappcompose.resolve_parking.data.models.SuccessResponse
import com.example.myappcompose.resolve_parking.data.models.CloseTicketRequest
import com.example.myappcompose.resolve_parking.data.models.EditVehicleDetailRequest
import com.example.myappcompose.resolve_parking.data.models.ErrorResponse
import com.example.myappcompose.resolve_parking.data.models.IsPrintedRequest
import com.example.myappcompose.resolve_parking.data.models.PayOnExitResponseModel
import com.example.myappcompose.resolve_parking.data.models.PayRequestModel
import com.example.myappcompose.resolve_parking.data.models.QRScannerRequest
import com.example.myappcompose.resolve_parking.data.models.TicketDetailModel
import com.example.myappcompose.resolve_parking.data.models.VoidRefundRequest
import com.example.myappcompose.resolve_parking.data.repository.TicketHistoryRepository
import com.example.myappcompose.resolve_parking.utils.enums.TicketCloseTypeEnum
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class TicketDetailViewModel(private val ticketRepository: TicketHistoryRepository) : ViewModel() {

    var qrText1: String? = null
    val showToast = MutableLiveData<String>()
    val showToastDialog = MutableLiveData<String>()
    var userToken: String? = null
    val apiDataList = MutableLiveData<TicketDetailModel?>()
    val ticketClose = MutableLiveData(false)
    val payOnExitORExceed = MutableLiveData<Boolean>()
    val editVehicleDetail = MutableLiveData(false)
    val authenticateVoidRefundTicket = MutableLiveData<Response<PayOnExitResponseModel>?>(null)
    val voidRefund = MutableLiveData(false)
    val isPrintedStatus = MutableLiveData(false)
    val showProgress = MutableLiveData(false)
    val showProgressDialog = MutableLiveData(false)
    val authentication = MutableLiveData(false)
    fun getRequestDetail(id: String) {
        viewModelScope.launch {
            showProgress.postValue(true)
            val data = ticketRepository.getTicketDetailById(userToken!!, id)
            showProgress.postValue(false)
            when (data) {
                is SuccessResponse -> {
                    apiDataList.postValue(data.data)
                }

                is ErrorResponse -> {
                    showToast.postValue(data.message)
                    apiDataList.postValue(null)
                    if (data.statusCode == 401) {
                        authentication.postValue(true)
                    }
                }
            }
        }
    }

    fun closeTicketManually(id: Int, name: String, notes: String, file: File?) {
        viewModelScope.launch {
            showProgressDialog.postValue(true)
            val uploadCategory = "TicketClose"
            val fullName = uploadCategory.toRequestBody(MultipartBody.FORM)
            val cameraImage: RequestBody = file!!.asRequestBody("image/*".toMediaType())
            val image = MultipartBody.Part.createFormData("File", file.getName(), cameraImage)
            when (val dataImage = ticketRepository.uploadImage(userToken!!, fullName, image)) {
                is SuccessResponse -> {
                    ticketClose.postValue(true)
                    val closeModel =
                        CloseTicketRequest(
                            ticketId = id,
                            note = notes,
                            closedBy = name,
                            image = dataImage.data.url.toString()
                        )

                    val data = ticketRepository.closeTicketManually(
                        userToken!!,
                        closeModel

                    )
                    showProgressDialog.postValue(false)
                    when (data) {
                        is SuccessResponse -> {
                            showToastDialog.postValue(data.data.message.toString())
                            ticketClose.postValue(true)
                        }

                        is ErrorResponse -> {
                            showToast.postValue(data.message)
                            ticketClose.postValue(false)
                        }
                    }
                }

                is ErrorResponse -> {
                    showToastDialog.postValue(dataImage.message)
                    showProgressDialog.postValue(false)
                    ticketClose.postValue(false)
                    if (dataImage.statusCode == 401) {
                        authentication.postValue(true)
                    }
                }
            }

        }
    }

    fun closeTicketByQrScanner(scannedQrContent: String, qrScannerFlow: Boolean) {
        viewModelScope.launch {
            showProgressDialog.postValue(true)
            val closeModel = QRScannerRequest(
                scannedQrContent = scannedQrContent,
                if (qrScannerFlow) TicketCloseTypeEnum.SCAN.id else TicketCloseTypeEnum.CLOSED.id
            )

            val data = ticketRepository.closeTicketByQrScanned(userToken!!, closeModel)
            showProgressDialog.postValue(false)
            when (data) {
                is SuccessResponse -> {
                    showToastDialog.postValue(data.data.message.toString())
                    ticketClose.postValue(true)
                }

                is ErrorResponse -> {
                    showToast.postValue(data.message)
                    ticketClose.postValue(false)

                    if (data.statusCode == 401) {
                        authentication.postValue(true)
                    }
                }
            }

        }
    }

    fun updateVehicleDetail(ticketId: Int, notes: String) {
        viewModelScope.launch {
            showProgress.postValue(true)
            val detailRequest =
                EditVehicleDetailRequest(note = notes)

            val data =
                ticketRepository.editVehicleDetail(userToken!!, ticketId.toString(), detailRequest)

            when (data) {
                is SuccessResponse -> {
                    showProgress.postValue(false)
                    editVehicleDetail.postValue(true)
                }

                is ErrorResponse -> {
                    showProgress.postValue(false)
                    showToast.postValue(data.message)
                    editVehicleDetail.postValue(false)

                    if (data.statusCode == 401) {
                        authentication.postValue(true)
                    }
                }
            }
        }
    }

    fun scanTicketDetail(qrText: String) {
        viewModelScope.launch {
            showProgress.postValue(true)
            qrText1 = qrText.replace(
                "https://www.website.com/ticket/",
                "https://dev.resolveparking.com/ticket/"
            )
            val model = QRScannerRequest(qrText1)
            val data = ticketRepository.scanTicketDetail(userToken!!, model)
            showProgress.postValue(false)
            when (data) {
                is SuccessResponse -> {
                    apiDataList.postValue(data.data)
                }

                is ErrorResponse -> {
                    showToast.postValue("Invalid ticket. Try to scan again.")
                    apiDataList.postValue(null)

                    if (data.statusCode == 401) {
                        authentication.postValue(true)
                    }
                }
            }
        }
    }

    fun performPayOnExit(id: Int, request: PayRequestModel) {
        viewModelScope.launch {
            showProgress.postValue(true)
            val data = ticketRepository.getPayOnExitOrExceed(userToken!!, id.toString(), request)
            showProgress.postValue(false)
            when (data) {
                is SuccessResponse -> {
                    payOnExitORExceed.postValue(true)
                    showToast.postValue(data.data.message.toString())
                    showProgress.postValue(true)

                }

                is ErrorResponse -> {
                    showToast.postValue(data.message)
                    payOnExitORExceed.postValue(false)

                    if (data.statusCode == 401) {
                        authentication.postValue(true)
                    }
                }
            }

        }
    }

    fun updateStatusIsPrinted(id: Int?, ticketSerialNo: String?) {
        viewModelScope.launch {
            showProgress.postValue(true)
            val request = IsPrintedRequest(ticketSerialNo, true)
            val data = ticketRepository.updateStatusIsPrinted(userToken!!, id.toString(), request)
            showProgress.postValue(false)
            when (data) {
                is SuccessResponse -> {
                    isPrintedStatus.postValue(true)
                }

                is ErrorResponse -> {
                    showToast.postValue(data.message)
                    isPrintedStatus.postValue(false)

                    if (data.statusCode == 401) {
                        authentication.postValue(true)
                    }
                }
            }
        }
    }

    fun voidRefundTicket(id: String, password: String, reason: String?) {
        viewModelScope.launch {
            showProgress.postValue(true)
            val request = VoidRefundRequest(reason, password, false)
            val data = ticketRepository.voidRefundTicket(userToken!!, id, request)
            showProgress.postValue(false)
            when (data) {
                is SuccessResponse -> {
                    voidRefund.postValue(true)
                    showToast.postValue(data.data.message.toString())
                }

                is ErrorResponse -> {
                    showToast.postValue(data.message)
                    voidRefund.postValue(false)

                    if (data.statusCode == 401) {
                        authentication.postValue(true)
                    }
                }
            }

        }
    }

    fun authenticateVoidRefundTicket(id: String, password: String, reason: String?) {
        viewModelScope.launch {
            showProgressDialog.postValue(true)
            val request = VoidRefundRequest(reason, password, true)
            val data = ticketRepository.voidRefundTicket(userToken!!, id, request)
            showProgressDialog.postValue(false)
            authenticateVoidRefundTicket.postValue(data)
        }
    }

}
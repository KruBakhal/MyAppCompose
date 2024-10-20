package com.example.myappcompose.resolve_parking.data.repository

import com.example.myappcompose.resolve_parking.utils.CommonUtil.handleServerErrorResponse
import com.example.myappcompose.resolve_parking.data.models.ErrorResponse
import com.example.myappcompose.resolve_parking.data.models.Response
import com.example.myappcompose.resolve_parking.data.models.SuccessResponse
import com.example.myappcompose.resolve_parking.data.api.ResolveParkingApiService
import com.example.myappcompose.resolve_parking.data.models.CloseTicketRequest
import com.example.myappcompose.resolve_parking.data.models.CloseTicketVehicleDetailModel
import com.example.myappcompose.resolve_parking.data.models.EditVehicleDetailRequest
import com.example.myappcompose.resolve_parking.data.models.IsPrintedRequest
import com.example.myappcompose.resolve_parking.data.models.PayOnExitResponseModel
import com.example.myappcompose.resolve_parking.data.models.PayRequestModel
import com.example.myappcompose.resolve_parking.data.models.QRScannerRequest
import com.example.myappcompose.resolve_parking.data.models.TicketDetailModel
import com.example.myappcompose.resolve_parking.data.models.TicketHistoryModel
import com.example.myappcompose.resolve_parking.data.models.TicketPrints
import com.example.myappcompose.resolve_parking.data.models.VoidRefundRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody


class TicketHistoryRepository(private val apiService: ResolveParkingApiService) {
        suspend fun searchTicket(
        authToken: String,
        isOpen: Boolean?,
        isVoid: Boolean?,
        page: String,
        total: String,
        orderBy: String,
        orderByDirection: Int,
        searchText: String?
    ): Response<TicketHistoryModel> {
        return try {
            val response = apiService.searchTicket(
                authToken, isOpen, isVoid, searchText, page, total, orderBy, orderByDirection
            )
            SuccessResponse(response)
        } catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
                model.messages.firstOrNull().toString(), ee, ee.code()
            )
        } catch (ee: Exception) {
            ErrorResponse("", ee)
        }


    }

    suspend fun getTicketDetailById(
        authToken: String, ticketRequest: String
    ): Response<TicketDetailModel> {
        return try {
            val response = this.apiService.getTicketDetailById(authToken, ticketRequest)
            SuccessResponse(response)
        } catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
               model.messages.firstOrNull().toString(), ee, ee.code()
            )
        } catch (ee: Exception) {
            ErrorResponse("", ee)
        }

    }

    suspend fun uploadImage(
        authToken: String, categoryType: RequestBody, image: MultipartBody.Part
    ): Response<CloseTicketVehicleDetailModel> {
        return try {
            val response = this.apiService.uploadImage(authToken, categoryType, image)
            if (!response.url.isNullOrEmpty() && response.status == null) {
                SuccessResponse(response)
            } else {
                ErrorResponse("uploadImage api error: MSG: ${response.message} \n Title:${response.title}\n Title:${response.detail}")
            }
        }catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
               model.messages.firstOrNull().toString(), ee, ee.code()
            )
        }  catch (ee: Exception) {
            ErrorResponse("Catch: ${ee.message}", ee)
        }


    }

    suspend fun closeTicketManually(
        authToken: String,
        closeTicketRequest: CloseTicketRequest,
    ): Response<CloseTicketVehicleDetailModel> {
        return try {
            val response = this.apiService.closeTicketManually(authToken, closeTicketRequest)
            if (!response.message.isNullOrEmpty() && response.statusCode == null) {
                SuccessResponse(response)
            } else {
                ErrorResponse(
                    "closeTicketCall api error: msg:${response.message.toString()}" + "\n supposeMsg: ${response.supportMessage.toString()}" + "\n errorId: ${response.errorId.toString()}" + "\n statusCode: ${response.statusCode.toString()}" + "\n stackTrace: ${response.stackTrace.toString()}" + "\n exp:${response.exception.toString()}"
                )
            }
        } catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
               model.messages.firstOrNull().toString(), ee, ee.code()
            )
        } catch (ee: Exception) {
            ErrorResponse("Catch: " + ee.message, ee)
        }

    }

    suspend fun closeTicketByQrScanned(
        authToken: String,
        qrScannerRequest: QRScannerRequest,
    ): Response<CloseTicketVehicleDetailModel> {
        return try {
            val response = this.apiService.closeTicket(authToken, qrScannerRequest)
            if (!response.message.isNullOrEmpty() && response.statusCode == null) {
                SuccessResponse(response)
            } else {
                ErrorResponse(
                    "closeTicketCall api error: msg:${response.message.toString()}" + "\n supposeMsg: ${response.supportMessage.toString()}" + "\n errorId: ${response.errorId.toString()}" + "\n statusCode: ${response.statusCode.toString()}" + "\n stackTrace: ${response.stackTrace.toString()}" + "\n exp:${response.exception.toString()}"
                )
            }
        } catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
                model.messages.firstOrNull().toString(), ee
            )
        } catch (ee: Exception) {
            ErrorResponse("Catch: " + ee.message, ee)
        }

    }

    suspend fun scanTicketDetail(
        authToken: String, qrText: QRScannerRequest
    ): Response<TicketDetailModel> {
        return try {
            val response = this.apiService.scanTicketDetail(authToken, qrText)
            SuccessResponse(response)
        }catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
               model.messages.firstOrNull().toString(), ee, ee.code()
            )
        }  catch (ee: Exception) {
            ErrorResponse(ee.message + "", ee)
        }

    }

    suspend fun editVehicleDetail(
        authToken: String,
        ticketID: String,
        vehicleDetailRequest: EditVehicleDetailRequest,
    ): Response<CloseTicketVehicleDetailModel> {
        return try {
            val response =
                this.apiService.editVehicleDetail(authToken, ticketID, vehicleDetailRequest)
            SuccessResponse(response)
        } catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
               model.messages.firstOrNull().toString(), ee, ee.code()
            )
        } catch (ee: Exception) {
            ErrorResponse(ee.message.toString(), ee)
        }

    }

    suspend fun getPayOnExitOrExceed(
        userToken: String, ticketID: String, request: PayRequestModel
    ): Response<PayOnExitResponseModel> {
        return try {
            val response = this.apiService.payOnExitOrExceed(userToken, ticketID, request)
            SuccessResponse(response)
        }catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
               model.messages.firstOrNull().toString(), ee, ee.code()
            )
        }  catch (ee: Exception) {
            ErrorResponse(ee.message.toString(), ee)
        }
    }

    suspend fun getExtendTicketReprint(
        userToken: String, ticketID: String, request: TicketPrints
    ): Response<PayOnExitResponseModel> {
        return try {
            val response = this.apiService.extendTicketReprint(userToken, ticketID, request)
            SuccessResponse(response)
        }catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
               model.messages.firstOrNull().toString(), ee, ee.code()
            )
        }  catch (ee: Exception) {
            ErrorResponse(ee.message.toString(), ee)
        }
    }

    suspend fun updateStatusIsPrinted(
        userToken: String, ticketID: String, request: IsPrintedRequest
    ): Response<PayOnExitResponseModel> {
        return try {
            val response = this.apiService.updateStatusIsPrinted(userToken, ticketID, request)
            SuccessResponse(response)
        } catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
                model.messages.firstOrNull().toString(), ee
            )
        } catch (ee: Exception) {
            ErrorResponse(ee.message.toString(), ee)
        }
    }

    suspend fun voidRefundTicket(
        userToken: String, ticketID: String, request: VoidRefundRequest
    ): Response<PayOnExitResponseModel> {
        return try {
            val response = this.apiService.voidRefundTicket(userToken, ticketID, request)
            SuccessResponse(response)
        } catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
                model.messages.firstOrNull().toString(), ee
            )
        } catch (ee: Exception) {
            ErrorResponse(ee.toString(), ee)
        }
    }


}
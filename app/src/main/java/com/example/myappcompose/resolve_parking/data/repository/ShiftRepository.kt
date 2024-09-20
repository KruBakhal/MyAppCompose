package com.example.myappcompose.resolve_parking.data.repository

import com.example.myappcompose.resolve_parking.utils.CommonUtil.getErrorMessage
import com.example.myappcompose.resolve_parking.utils.CommonUtil.handleServerErrorResponse
import com.example.myappcompose.resolve_parking.data.models.ErrorResponse
import com.example.myappcompose.resolve_parking.data.models.Response
import com.example.myappcompose.resolve_parking.data.models.SuccessResponse
import com.example.myappcompose.resolve_parking.data.models.ShiftResponse
import com.example.myappcompose.resolve_parking.data.api.ApiService
import com.example.myappcompose.resolve_parking.data.models.ActiveShiftResponse
import com.example.myappcompose.resolve_parking.data.models.ApiResponse
import com.example.myappcompose.resolve_parking.data.models.EndShift
import com.example.myappcompose.resolve_parking.data.models.ForceEndShiftRequest
import com.example.myappcompose.resolve_parking.data.models.ShiftSummary
import com.example.myappcompose.resolve_parking.data.models.VerifyPinRequest
import com.example.myappcompose.resolve_parking.data.models.VerifyPinResponse
import retrofit2.HttpException

class ShiftRepository(private val apiService: ApiService) {

    suspend fun startShift(deviceSerialNo: String, clientSecret: String): ApiResponse<ShiftResponse> {
        return try {
            val response = apiService.startShift(deviceSerialNo, clientSecret)
            ApiResponse(true, response, "")
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = handleServerErrorResponse(e)
            ApiResponse(false, null, errorMessage.messages.firstOrNull().toString(),e.code())
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = getErrorMessage(e)
            ApiResponse(false, null, errorMessage)
        } catch (e: Exception) {
            // Handle other exceptions
            ApiResponse(false, null, "${e.message}")
        }
    }

    suspend fun verifyPin(
        deviceSerialNo: String,
        clientSecret: String,
        devicePIN: String
    ): ApiResponse<VerifyPinResponse> {
        return try {
            val verifyPinRequest = VerifyPinRequest(devicePIN)
            val response = apiService.verifyPin(deviceSerialNo, clientSecret, verifyPinRequest)
            ApiResponse(true, response, "")
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = handleServerErrorResponse(e)
            ApiResponse(false, null, errorMessage.messages.firstOrNull().toString(),e.code())
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = getErrorMessage(e)
            ApiResponse(false, null, errorMessage)
        } catch (e: Exception) {
            // Handle other exceptions
            ApiResponse(false, null, "${e.message}")
        }
    }

    suspend fun getActiveShift(
        deviceSerialNo: String,
        clientSecret: String
    ): ApiResponse<ActiveShiftResponse> {
        return try {
            val response = apiService.getActiveShift(deviceSerialNo, clientSecret)
            val model = ActiveShiftResponse(
                response.isShiftActiveAtCurrentLocation,
                response.currentDeviceShift
            )
            ApiResponse(true, model, "")
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = handleServerErrorResponse(e)
            ApiResponse(false, null, errorMessage.messages.firstOrNull().toString(),e.code())
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = getErrorMessage(e)
            ApiResponse(false, null, errorMessage)
        } catch (e: Exception) {
            // Handle other exceptions
            ApiResponse(false, null, "${e.message}")
        }
    }

    suspend fun endShift(deviceSerialNo: String, authToken: String): ApiResponse<EndShift> {
        return try {
            val response = apiService.endShift(deviceSerialNo, authToken)
            val model = EndShift(response.message)
            ApiResponse(true, model, "")
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = handleServerErrorResponse(e)
            ApiResponse(false, null, errorMessage.messages.firstOrNull().toString(),e.code())
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = getErrorMessage(e)
            ApiResponse(false, null, errorMessage)
        } catch (e: Exception) {
            // Handle other exceptions
            ApiResponse(false, null, "${e.message}")
        }
    }

    suspend fun getShiftSummary(authToken: String): ApiResponse<ShiftSummary> {
        return try {
            val response = apiService.getShiftSummary(authToken)
            ApiResponse(true, response, "")
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = handleServerErrorResponse(e)
            ApiResponse(false, null, errorMessage.messages.firstOrNull().toString(),e.code())
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = getErrorMessage(e)
            ApiResponse(false, null, errorMessage)
        } catch (e: Exception) {
            // Handle other exceptions
            ApiResponse(false, null, "${e.message}")
        }
    }

    @Suppress("UNUSED_PARAMETER")
    suspend fun forceEndShift(token: String, deviceSerialNo: String, model: ForceEndShiftRequest):
            Response<EndShift> {
        return try {
            val response = apiService.forceEndShift(token, model)
            SuccessResponse(response)
        } catch (ee: HttpException) {
            val model1 = handleServerErrorResponse(ee)
            ErrorResponse(
                model1.messages.firstOrNull().toString(), ee
            )
        } catch (ee: Exception) {
            ErrorResponse("Catch: " + ee.message, ee)
        }
    }

}
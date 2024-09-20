package com.example.myappcompose.resolve_parking.data.repository

import android.util.Log
import com.example.myappcompose.resolve_parking.data.api.ApiService
import com.example.myappcompose.resolve_parking.data.models.ApiResponse
import com.example.myappcompose.resolve_parking.data.models.CrashDataRequestModel
import com.example.myappcompose.resolve_parking.data.models.DeviceConfigValueResponse
import com.example.myappcompose.resolve_parking.data.models.PayOnExitResponseModel
import com.example.myappcompose.resolve_parking.utils.CommonUtil
import retrofit2.HttpException

class LookupRepository(private val apiService: ApiService) {
    suspend fun getDeviceConfigValue(authToken: String): ApiResponse<DeviceConfigValueResponse> {
        return try {
            val response = apiService.getDeviceConfigValue( authToken)
            ApiResponse(true, response, "")
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = CommonUtil.handleServerErrorResponse(e)
            ApiResponse(false, null, errorMessage.messages.firstOrNull().toString(),errorMessage.statusCode)
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = CommonUtil.getErrorMessage(e)
            ApiResponse(false, null, errorMessage)
        } catch (e: Exception) {
            // Handle other exceptions
            ApiResponse(false, null, "${e.message}")
        }
    }
    suspend fun sendCrashLog(sendData: CrashDataRequestModel): ApiResponse<PayOnExitResponseModel> {
        return try {
            Log.d("TAG", "send: sendCrashLog() called")
            val response = apiService.sendCrashLog(sendData)
            ApiResponse(true, response, "")
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = CommonUtil.handleServerErrorResponse(e)
            ApiResponse(false, null, errorMessage.messages.firstOrNull().toString(),errorMessage.statusCode)
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = CommonUtil.getErrorMessage(e)
            ApiResponse(false, null, errorMessage)
        } catch (e: Exception) {
            // Handle other exceptions
            ApiResponse(false, null, "${e.message}")
        }
    }
}
package com.example.myappcompose.resolve_parking.data.repository

import com.example.myappcompose.resolve_parking.utils.CommonUtil.handleServerErrorResponse
import com.example.myappcompose.resolve_parking.data.models.ErrorResponse
import com.example.myappcompose.resolve_parking.data.models.Response
import com.example.myappcompose.resolve_parking.data.models.SuccessResponse
import com.example.myappcompose.resolve_parking.data.api.ResolveParkingApiService
import com.example.myappcompose.resolve_parking.data.models.ReportDataSharedResponse
import com.example.myappcompose.resolve_parking.data.models.ReportModel
import com.example.myappcompose.resolve_parking.data.models.SplitShiftRequest

class ReportRepository(private val apiService: ResolveParkingApiService) {
    suspend fun reportTicket(authToken: String): Response<ReportModel> {
        return try {
            val response = apiService.reportData(authToken)
            SuccessResponse(response)
        }catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
                model.messages.firstOrNull().toString(), ee, ee.code()
            )
        } catch (ee: Exception) {
            ErrorResponse("Catch: ${ee.message}", ee)
        }
    }
    suspend fun shareReportData(authToken: String): Response<ReportDataSharedResponse> {
        return try {
            val response = apiService.shareReportData(authToken)
            SuccessResponse(response)
        } catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
                model.messages.firstOrNull().toString(), ee, ee.code()
            )
        }catch (ee: Exception) {
            ErrorResponse("Catch: ${ee.message}", ee)
        }
    }

    suspend fun splitShiftReport(authToken: String,time:String?=null): Response<ReportModel> {
        return try {
            val response = apiService.splitShiftReport(authToken, SplitShiftRequest(time))
            SuccessResponse(response)
        } catch (ee: retrofit2.HttpException) {
            val model = handleServerErrorResponse(ee)
            ErrorResponse(
                model.messages.firstOrNull().toString(), ee, ee.code()
            )
        }catch (ee: Exception) {
            ErrorResponse("Catch: ${ee.message}", ee)
        }
    }
}

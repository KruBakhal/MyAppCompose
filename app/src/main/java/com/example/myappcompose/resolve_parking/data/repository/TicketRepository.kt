package com.example.myappcompose.resolve_parking.data.repository

import com.example.myappcompose.resolve_parking.utils.CommonUtil.logRequestBody
import com.example.myappcompose.resolve_parking.data.api.ApiService
import com.example.myappcompose.resolve_parking.data.models.ApiResponse
import com.example.myappcompose.resolve_parking.data.models.TicketRequest
import com.example.myappcompose.resolve_parking.data.models.TicketResponse
import com.example.myappcompose.resolve_parking.utils.CommonUtil
import retrofit2.HttpException

class TicketRepository(private val apiService: ApiService) {
    suspend fun createTicket(authToken: String, ticketRequest: TicketRequest): ApiResponse<TicketResponse> {
        return try {
            logRequestBody(ticketRequest)
            val response= apiService.createTicket(authToken, ticketRequest)
            ApiResponse(true, response, "")
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = CommonUtil.handleServerErrorResponse(e)
            ApiResponse(false, null, errorMessage.messages.firstOrNull().toString(),errorMessage.statusCode)
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorMessage = CommonUtil.getErrorMessage(e)
            ApiResponse(false, null, errorMessage,e.code())
        } catch (e: Exception) {
            // Handle other exceptions
            ApiResponse(false, null, "${e.message}")
        }
    }

}
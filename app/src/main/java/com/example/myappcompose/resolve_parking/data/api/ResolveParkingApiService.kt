package com.example.myappcompose.resolve_parking.data.api

import com.example.myappcompose.resolve_parking.utils.ResolveParkingConstants.BASE_URL
import com.example.myappcompose.resolve_parking.data.models.*
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ResolveParkingApiService {
    // shift Api`s
    @POST("api/device/validate-pin/{deviceSerialNo}")
    suspend fun verifyPin(
        @Path("deviceSerialNo") deviceSerialNo: String,
        @Header("client-secret") clientSecret: String,
        @Body request: VerifyPinRequest
    ): VerifyPinResponse

    @POST("api/shift/start/{deviceSerialNo}")
    suspend fun startShift(
        @Path("deviceSerialNo") deviceSerialNo: String,
        @Header("client-secret") clientSecret: String,
    ): ShiftResponse

    @POST("api/shift/active/{deviceSerialNo}")
    suspend fun getActiveShift(
        @Path("deviceSerialNo") deviceSerialNo: String,
        @Header("client-secret") clientSecret: String
    ): ActiveShiftResponse

    @POST("api/shift/close/{deviceSerialNo}")
    suspend fun endShift(
        @Path("deviceSerialNo") deviceSerialNo: String,
        @Header("Authorization") authToken: String
    ): EndShift

    @POST("api/shift/force-close")
    suspend fun forceEndShift(
        @Header("Authorization") authToken: String,

        @Body body: ForceEndShiftRequest
    ): EndShift

    @GET("api/shift/shift-group/summary")
    suspend fun getShiftSummary(
        @Header("Authorization") authToken: String,
    ): ShiftSummary

    // lookup config api`s
    @GET("api/lookup/config")
    suspend fun getDeviceConfigValue(
        @Header("Authorization") authToken: String
    ): DeviceConfigValueResponse

    // ticket api`s
    @POST("api/tickets/new")
    suspend fun createTicket(
        @Header("Authorization") authToken: String,
        @Body ticketRequest: TicketRequest
    ): TicketResponse

    @POST("api/tickets/scan")
    suspend fun scanTicketDetail(
        @Header("Authorization") authToken: String,
        @Body scannedQrContent: QRScannerRequest
    ): TicketDetailModel

    @GET("api/tickets/{ticketId}")
    suspend fun getTicketDetailById(
        @Header("Authorization") authToken: String,
        @Path("ticketId") ticketId: String
    ): TicketDetailModel

    @GET("api/tickets")
    suspend fun searchTicket(
        @Header("Authorization") authToken: String,
        @Query("IsOpened") onlyOpenTickets: Boolean? = null,
        @Query("IsVoid") isVoid: Boolean? = null,
        @Query("Keyword") keyword: String? = null,
        @Query("PageIndex") pageIndex: String,
        @Query("PageSize") pageSize: String,
        @Query("OrderBy") orderBy: String,
        @Query("OrderByDirection") orderByDirection: Int?
    ): TicketHistoryModel


    @POST("api/tickets/close/manual")
    suspend fun closeTicketManually(
        @Header("Authorization") authToken: String,
        @Body closeTicketRequest: CloseTicketRequest
    ): CloseTicketVehicleDetailModel


    @POST("api/tickets/close")
    suspend fun closeTicket(
        @Header("Authorization") authToken: String,
        @Body scannedQrContent: QRScannerRequest
    ): CloseTicketVehicleDetailModel

    @Suppress("SpellCheckingInspection")
    @Multipart
    @POST("api/fileupload/upload")
    suspend fun uploadImage(
        @Header("Authorization") authToken: String,
        @Part("UploadCategory") uploadCategory: RequestBody,
        @Part image: MultipartBody.Part?
    ): CloseTicketVehicleDetailModel

    @PATCH("api/tickets/{ticketId}/note")
    suspend fun editVehicleDetail(
        @Header("Authorization") authToken: String,
        @Path("ticketId") ticketId: String,
        @Body editVehicleDetailRequest: EditVehicleDetailRequest
    ): CloseTicketVehicleDetailModel

    @POST("api/tickets/{ticketId}/pay")
    suspend fun payOnExitOrExceed(
        @Header("Authorization") authToken: String,
        @Path("ticketId") ticketId: String,
        @Body payRequestModel: PayRequestModel
    ): PayOnExitResponseModel

    @POST("api/tickets/{ticketId}/reprint")
    suspend fun extendTicketReprint(
        @Header("Authorization") authToken: String,
        @Path("ticketId") ticketId: String,
        @Body requestModel: TicketPrints
    ): PayOnExitResponseModel

    @PATCH("api/tickets/{ticketId}/printed")
    suspend fun updateStatusIsPrinted(
        @Header("Authorization") authToken: String,
        @Path("ticketId") ticketId: String,
        @Body requestModel: IsPrintedRequest
    ): PayOnExitResponseModel

    @POST("api/shift/shift-group/report")
    suspend fun reportData(
        @Header("Authorization") authToken: String,
    ): ReportModel

    @POST("/api/tickets/{ticketId}/cancel")
    suspend fun voidRefundTicket(
        @Header("Authorization") authToken: String,
        @Path("ticketId") ticketId: String,
        @Body requestModel: VoidRefundRequest
    ): PayOnExitResponseModel

    @POST("api/shift/shift-group/report/share")
    suspend fun shareReportData(
        @Header("Authorization") authToken: String,
    ): ReportDataSharedResponse

    @POST("api/shift/split-shift")
    suspend fun splitShiftReport(
        @Header("Authorization") authToken: String,@Body requestModel: SplitShiftRequest
    ): ReportModel

    @POST("api/device/device-crash")
    suspend fun sendCrashLog(
        @Body requestModel: CrashDataRequestModel
    ): PayOnExitResponseModel


}
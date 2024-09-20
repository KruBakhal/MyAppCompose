package com.example.myappcompose.resolve_parking.utils

import android.annotation.SuppressLint
import com.example.myappcompose.resolve_parking.utils.Constants.AUTH_TOKEN_KEY
import com.example.myappcompose.resolve_parking.utils.Constants.DATE_TIME_FORMAT_MMM_DD_YYYY
import com.example.myappcompose.resolve_parking.utils.Constants.DEVICE_CONFIG_VALUE_KEY
import com.example.myappcompose.resolve_parking.utils.Constants.DEVICE_SERIAL_NO_KEY
import com.example.myappcompose.resolve_parking.utils.Constants.IS_CLOVER_DEVICE
import com.example.myappcompose.resolve_parking.utils.Constants.IS_PAY_ON_EXIST
import com.example.myappcompose.resolve_parking.utils.Constants.IS_SELF
import com.example.myappcompose.resolve_parking.utils.Constants.IS_SHIFT_KEY
import com.example.myappcompose.resolve_parking.utils.Constants.IS_VALLET
import com.example.myappcompose.resolve_parking.utils.Constants.LAST_PRINTED_SERIAL_NO_KEY
import com.example.myappcompose.resolve_parking.utils.Constants.LOGIN_DATE
import com.example.myappcompose.resolve_parking.utils.Constants.QRContentTemplate
import com.example.myappcompose.resolve_parking.utils.Constants.SHARED_PREFERENCES_NAME
import com.example.myappcompose.resolve_parking.utils.Constants.SHIFT_ID_KEY
import com.example.myappcompose.resolve_parking.utils.Constants.TICKET_LIST_KEY
import com.example.myappcompose.resolve_parking.utils.Constants.TIMEZONEIDENTIFIER
import com.example.myappcompose.resolve_parking.utils.Constants.UNKNOWN
import com.example.myappcompose.resolve_parking.utils.Constants.ISTip
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myappcompose.R
import com.example.myappcompose.resolve_parking.ResolveParkingActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.myappcompose.resolve_parking.data.models.Address
import com.example.myappcompose.resolve_parking.data.models.ApiResponse
import com.example.myappcompose.resolve_parking.data.models.DeviceConfigValueResponse
import com.example.myappcompose.resolve_parking.data.models.ErrorResponseModel
import com.example.myappcompose.resolve_parking.data.models.TicketRequest
import retrofit2.HttpException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

object CommonUtil {

    fun saveIsShiftStarted(context: Context, isShiftStarted: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_SHIFT_KEY, isShiftStarted)
        editor.apply()
    }

    fun saveLoginDate(context: Context, isShiftStarted: String) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(LOGIN_DATE, isShiftStarted)
        editor.apply()
    }

    fun getLoginDate(context: Context): String? {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(LOGIN_DATE, null)
    }

    fun getIsShiftStarted(context: Context): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(IS_SHIFT_KEY, false)
    }

    fun saveIsCloverDevice(context: Context, isCloverDevice: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_CLOVER_DEVICE, isCloverDevice)
        editor.apply()
    }

    @Suppress("unused")
    fun getIsCloverDevice(context: Context): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(IS_CLOVER_DEVICE, false)
    }

    fun saveShiftId(context: Context, shiftId: String) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SHIFT_ID_KEY, shiftId)
        editor.apply()
    }

    fun getShiftId(context: Context): String? {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(SHIFT_ID_KEY, null)
    }

    fun getAuthToken(context: Context): String? {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(AUTH_TOKEN_KEY, null)
    }

    fun saveAuthToken(context: Context, authToken: String) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(AUTH_TOKEN_KEY, "Bearer $authToken")
        editor.apply()
    }

    fun getDeviceSerialNo(context: Context): String? {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(DEVICE_SERIAL_NO_KEY, null)
    }

    fun saveDeviceSerialNo(context: Context, deviceSerialNo: String) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(DEVICE_SERIAL_NO_KEY, deviceSerialNo)
        editor.apply()
    }

    fun saveDeviceConfigValue(context: Context, device: DeviceConfigValueResponse) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val deviceJson = gson.toJson(device)
        editor.putString(DEVICE_CONFIG_VALUE_KEY, deviceJson)
        editor.apply()
    }

    fun getDeviceConfigValue(context: Context): DeviceConfigValueResponse? {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val deviceJson = sharedPreferences.getString(DEVICE_CONFIG_VALUE_KEY, null)
        return gson.fromJson(deviceJson, DeviceConfigValueResponse::class.java)
    }

    fun openHomeActivity(context: Context) {
        // Start the home activity
        val intent = Intent(context, ResolveParkingActivity::class.java)
        context.startActivity(intent)

        // If called from an activity, finish the current activity to prevent going back to it
        if (context is AppCompatActivity) {
            context.finish()
        }
    }

    fun saveTicketList(context: Context, ticketList: List<TicketRequest>) {
        val gson = Gson()
        val ticketListJson = gson.toJson(ticketList)

        val preferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(TICKET_LIST_KEY, ticketListJson)
        editor.apply()
    }

    fun getTicketList(context: Context): List<TicketRequest> {
        val gson = Gson()
        val preferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val ticketListJson = preferences.getString(TICKET_LIST_KEY, null)

        return if (ticketListJson != null) {
            val type: Type = object : TypeToken<List<TicketRequest>>() {}.type
            gson.fromJson(ticketListJson, type)
        } else {
            emptyList()
        }
    }

    fun getLastPrintedSerialNo(context: Context): String? {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(LAST_PRINTED_SERIAL_NO_KEY, null)
    }

    // Function to save the last printed serial number in SharedPreferences
    fun saveLastPrintedSerialNo(context: Context, lastPrintedSerialNo: String) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(LAST_PRINTED_SERIAL_NO_KEY, lastPrintedSerialNo)
        editor.apply()
    }

    fun getQRContentTemplate(context: Context): String? {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(QRContentTemplate, "")
    }

    // Function to save the last printed serial number in SharedPreferences
    fun saveQRContentTemplate(context: Context, qrCode: String) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(QRContentTemplate, qrCode)
        editor.apply()
    }

    fun saveUpdateConfigData(
        context: Context,
        deviceConfigValueResponse: ApiResponse<DeviceConfigValueResponse>
    ) {
        saveDeviceConfigValue(context, deviceConfigValueResponse.data!!)
        val configParentSerialNos = deviceConfigValueResponse.data.lastPrintedSerialNoParent
        val list = getTicketList(context)
        Log.d("TAG", "saveUpdateConfigData: ${list.size}")
        val model = list.maxByOrNull { it.generatedOn }
        val lastOfflineSerialNos = model?.printedSerialNo
        if (configParentSerialNos != null) {
            if (!lastOfflineSerialNos.isNullOrEmpty()) {
                saveLatestByCompareOfflineSerialNosWithConfigSerialNos(
                    context, lastOfflineSerialNos,
                    configParentSerialNos, deviceConfigValueResponse.data.dayOfTheYear!!
                )
            } else {
                saveLastPrintedSerialNo(
                    context, replaceWithDayOfYear(
                        configParentSerialNos,
                        deviceConfigValueResponse.data.dayOfTheYear!!
                    )
                )
            }
        } else {
            saveLastPrintedSerialNo(
                context,
                "${deviceConfigValueResponse.data.device.ticketSerialPrefix}" + "-${deviceConfigValueResponse.data.dayOfTheYear}" + "-${
                    context.getString(R.string.default_serial_no_0000)
                }"
            )
        }
        saveTipFeatureEnable(
            context,
            deviceConfigValueResponse.data.device.location?.isTipEnabled ?: true
        )
        saveQRContentTemplate(
            context,
            deviceConfigValueResponse.data.qrContentTemplate
        )
        TIMEZONEIDENTIFIER =
            deviceConfigValueResponse.data.device.location!!.masterTimeZone.timeZoneIdentifier!!
    }

    private fun saveLatestByCompareOfflineSerialNosWithConfigSerialNos(
        context: Context,
        lastOfflineSerialNos: String,
        configParentSerialNos: String,
        dayOfTheYear: String
    ) {
        var listSplit = lastOfflineSerialNos.split("-")
        var value = "${listSplit[0]}-${listSplit[1]}-${listSplit[2]}"
        val latest = compareStrings(value, configParentSerialNos)
        listSplit = latest.split("-")
        value =
            if (listSplit.size > 2) "${listSplit[0]}-${listSplit[2]}" else configParentSerialNos
        saveLastPrintedSerialNo(
            context, replaceWithDayOfYear(
                value,
                dayOfTheYear
            )
        )
    }

    private fun compareStrings(string1: String, string2: String): String {
        // Extract the last element from each string
        val lastElement1 = string1.substringAfterLast("-")
        val lastElement2 = string2.substringAfterLast("-")

        // Convert the last elements to integers for comparison
        val int1 = lastElement1.toInt()
        val int2 = lastElement2.toInt()

        // Return the string with the larger integer
        return if (int1 > int2) string1 else string2
    }

    fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showMessage(message: String, view: View, color: Int) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        val snackBarView = snackBar.view
        val params = snackBarView.layoutParams as FrameLayout.LayoutParams
        snackBarView.setBackgroundColor(color)
        params.gravity = Gravity.TOP
        snackBarView.layoutParams = params
        snackBar.show()
    }

    fun getErrorMessage(e: HttpException): String {
        val errorBody = e.response()?.errorBody()?.string()
        Log.i("TAG", "getErrorMessage: $errorBody")
        val errorResponse = Gson().fromJson(errorBody, ErrorResponseModel::class.java)
        return errorResponse?.messages?.firstOrNull() ?: UNKNOWN
    }


    fun logRequestBody(ticketRequest: Any) {
        val gson = Gson()
        val json = gson.toJson(ticketRequest)
        Log.i("TAG", "logRequestBody: $json")
    }

    fun String.toDateTimeToInputFormatString(inputFormat: String): String {
        return try {
            val format = SimpleDateFormat(Constants.DATE_TIME_TIMEZONE_FORMAT, Locale.getDefault())
            val targetTimeZone = TimeZone.getTimeZone(TIMEZONEIDENTIFIER)
            format.timeZone = targetTimeZone
            val date: Date = format.parse(this)!!
            // Use SimpleDateFormat with desired output format
            val outputFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
            outputFormat.timeZone = targetTimeZone
            outputFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }.toString()
    }

    fun String.toDateTimeToInputFormatTimeStamp(): Long {
        return try {
            val format = SimpleDateFormat(Constants.DATE_TIME_TIMEZONE_FORMAT, Locale.getDefault())
            val targetTimeZone = TimeZone.getTimeZone(TIMEZONEIDENTIFIER)
            format.timeZone = targetTimeZone
            val date: Date = format.parse(this)!!
            date.time
        } catch (e: Exception) {
            e.printStackTrace()
            0L
        }.toLong()
    }

    fun String.checkIsPresentDate(): Boolean {
        val formatter = SimpleDateFormat(DATE_TIME_FORMAT_MMM_DD_YYYY, Locale.getDefault())
        try {
            val targetTimeZone = TimeZone.getTimeZone(TIMEZONEIDENTIFIER)
            formatter.timeZone = targetTimeZone
            val givenDate = formatter.parse(this)
            val today = Date()

            // Compare only year, month, and day
            val calendarGiven = Calendar.getInstance()
            calendarGiven.timeZone = targetTimeZone
            calendarGiven.time = givenDate as Date
            val calendarToday = Calendar.getInstance()
            calendarToday.timeZone = targetTimeZone
            calendarToday.time = today

            return (calendarGiven.get(Calendar.YEAR) == calendarToday.get(Calendar.YEAR) && calendarGiven.get(
                Calendar.MONTH
            ) == calendarToday.get(Calendar.MONTH) && calendarGiven.get(Calendar.DAY_OF_MONTH) == calendarToday.get(
                Calendar.DAY_OF_MONTH
            ))
        } catch (e: ParseException) {
            e.printStackTrace()
            return false
        }
    }

    fun Long.convertTimeStampToDateString(): String? {
        return try {
            val format = SimpleDateFormat(DATE_TIME_FORMAT_MMM_DD_YYYY, Locale.getDefault())
            val targetTimeZone = TimeZone.getTimeZone(TIMEZONEIDENTIFIER)
            format.timeZone = targetTimeZone
            format.format(Date(this))
        } catch (e: Exception) {
            "${e.message}"
        }
    }

    fun Long.convertTimeStampToTimeString(): String? {
        return try {
            val format = SimpleDateFormat(Constants.TIME_FORMAT_HH_MM_SS, Locale.getDefault())
            val targetTimeZone = TimeZone.getTimeZone(TIMEZONEIDENTIFIER)
            format.timeZone = targetTimeZone
            format.format(Date(this))
        } catch (e: Exception) {
            "${e.message}"
        }
    }

    fun Long.convertTimeStampToDateString(
        inputFormat: String = Constants.DATE_TIME_TIMEZONE_FORMAT,
        timeZoneIdentifier: String = TIMEZONEIDENTIFIER
    ): String? {
        return try {
            val format = SimpleDateFormat(inputFormat, Locale.getDefault())
            val targetTimeZone = TimeZone.getTimeZone(timeZoneIdentifier)
            format.timeZone = targetTimeZone
            format.format(Date(this))
        } catch (e: Exception) {
            "${e.message}"
        }
    }

    fun incrementString(originalString: String): String {
        val separator = "-"
        val parts = originalString.split(separator)
        if (parts.size < 2) {
            return originalString // Handle case where there's no separator or only one part
        }

        val endPartIndex = parts.lastIndex
        val endPart = parts[endPartIndex].toIntOrNull()
            ?: return originalString // Handle non-numeric end part

        val newEndPart = endPart + 1
        val endString = String.format("%04d", newEndPart)
        return parts.subList(0, endPartIndex)
            .joinToString(separator) + separator + endString
    }

    fun addTimeStringAndDuration(timeString: String, durationString: String): String {
        val sdf = SimpleDateFormat(Constants.DATE_TIME_TIMEZONE_FORMAT, Locale.getDefault())
        val targetTimeZone = TimeZone.getTimeZone(TIMEZONEIDENTIFIER)
        sdf.timeZone = targetTimeZone
        val calendar = Calendar.getInstance()
        calendar.timeZone = targetTimeZone
        calendar.time = sdf.parse(timeString)!!
        val parts = durationString.split(":")
        val hoursToAdd = parts[0].toInt()
        val minutesToAdd = parts[1].toInt()
        val secondsToAdd = parts[2].toInt()

        calendar.add(Calendar.HOUR_OF_DAY, hoursToAdd)
        calendar.add(Calendar.MINUTE, minutesToAdd)
        calendar.add(Calendar.SECOND, secondsToAdd)

        return sdf.format(calendar.time)
    }

    fun decodeBase64ToBitmap(base64String: String): Bitmap {
        val decodedBytes = android.util.Base64.decode(base64String, android.util.Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    @Suppress("unused")
    private fun View.hideKeyboards() {
        val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }

    fun convertAddressToString(address: Address): Pair<String, String> {
        val sb = StringBuilder()
        val sb1 = StringBuilder()

        address.address1.let { sb.append("$it, ") }

        address.address2.let { sb.append("$it, ") }
        address.city.let { sb1.append("$it, ") }

        address.state.let { sb1.append(it) }

        address.zip.let { sb1.append(" $it") }
        return Pair(sb.toString(), sb1.toString())
    }

    fun convertHeaderSubHeaderToString(
        title: String,
        ticketSubhead: String?,
        ticketSubhead2: String?
    ): Triple<String, String, String> {
        val sb = StringBuilder()
        val sb1 = StringBuilder()
        val sb2 = StringBuilder()

        title.let { sb.append("$it, ") }
        ticketSubhead.let { sb1.append("$it, ") }
        ticketSubhead2.let { sb2.append("$it") }


        return Triple(sb.toString(), sb1.toString(), sb2.toString())
    }

    fun checkCloverDeviceOrNot(context: Context): Boolean {
        return when {
            isCloverDevice(context) -> {
                // Special handling for Clover devices using Clover SDK
                true
            }

            else -> {
                // Handle other cases or provide a default value
                false
            }
        }
    }


    fun isCloverDevice(context: Context): Boolean {
        // Check the manufacturer of the device
        val manufacturer = Build.MANUFACTURER
        return manufacturer.equals(context.getString(R.string.clover), ignoreCase = true)
    }

    fun isValetFeatureEnable(context: Context): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(IS_VALLET, false)
    }

    fun saveValetFeatureEnable(context: Context, isShiftStarted: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_VALLET, isShiftStarted)
        editor.apply()

    }

    fun isSelfFeatureEnable(context: Context): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(IS_SELF, true)
    }

    fun isTipFeatureEnable(context: Context): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(ISTip, true)
    }

    fun saveTipFeatureEnable(context: Context, value: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(ISTip, value)
        editor.apply()

    }

    fun saveSelfFeatureEnable(context: Context, isShiftStarted: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_SELF, isShiftStarted)
        editor.apply()

    }

    fun isPayOnExitFeatureEnable(context: Context): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(IS_PAY_ON_EXIST, true)
    }

    fun savePayOnExitFeatureEnable(context: Context, isShiftStarted: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_PAY_ON_EXIST, isShiftStarted)
        editor.apply()

    }

    fun calculatePercentage(percentage: Int, total: Double = 100.0): Double {
        val value = total * (percentage / 100.0)
        val formattedPrice = DecimalFormat("0.00").format(value)
        return formattedPrice.toDouble() // Format to 2 decimal places (optional)
    }

    @Suppress("DEPRECATION")
    @SuppressLint("ObsoleteSdkInt")
    fun Context.checkInternetConnection(): Boolean {
        var result = 0 // Returns connection type. 0: none; 1: mobile data; 2: wifi
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    result = 2
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    result = 1
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    result = 3
                }
            }
        } else {
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) {
                // connected to the internet
                when (activeNetwork.type) {
                    ConnectivityManager.TYPE_WIFI -> {
                        result = 2
                    }

                    ConnectivityManager.TYPE_MOBILE -> {
                        result = 1
                    }

                    ConnectivityManager.TYPE_VPN -> {
                        result = 3
                    }
                }
            }
        }
        return result != 0
    }

    @Suppress("SpellCheckingInspection")
    fun Long.convertTimestampIntoHourMinSecond(): String {
        // milliseconds in a second
        val msInSec: Long = 1000
        // milliseconds in a minute
        val msInMin = msInSec * 60
        // milliseconds in an hour
        val msInHour = msInMin * 60

        // Calculate difference in milliseconds

        // Calculate difference in hours, minutes and seconds
        val hours = this / msInHour
        val remaining = this % msInHour
        val minutes = remaining / msInMin
        val seconds = remaining % msInMin / msInSec
        return "$hours hrs $minutes mins $seconds secs"
    }

    fun convertTimeDiffInChargedDurationFormat(startDate: Long, endDate: Long): String {

        // milliseconds in a second
        val msInSec: Long = 1000
        // milliseconds in a minute
        val msInMin = msInSec * 60
        // milliseconds in an hour
        val msInHour = msInMin * 60

        // Calculate difference in milliseconds
        val diff = endDate - startDate

        // Calculate difference in hours, minutes and seconds
        val hours = diff / msInHour
        val remaining = diff % msInHour
        val minutes = remaining / msInMin
        val seconds = remaining % msInMin / msInSec
        return "$hours:$minutes:$seconds"
    }

    @Suppress("unused")
    fun String.toDateFormat(inputFormat: String, outputFormat: String): String {
        return try {
            val format = SimpleDateFormat(inputFormat, Locale.getDefault())
            val targetTimeZone = TimeZone.getTimeZone(TIMEZONEIDENTIFIER)
            format.timeZone = targetTimeZone
            val date: Date = format.parse(this)!!
            // Use SimpleDateFormat with desired output format
            val outputFormat1 = SimpleDateFormat(outputFormat, Locale.getDefault())
            outputFormat1.timeZone = targetTimeZone
            outputFormat1.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }.toString()
    }

    fun Long.convertMilleSecond(): Long {
        return try {
            return TimeUnit.MINUTES.toMillis(this)
        } catch (e: Exception) {
            0L
        }
    }

    fun replaceWithDayOfYear(input: String, dayOfYear: String): String {
        if (input.isEmpty()) {
            return input // Handle empty input gracefully
        }
        val hyphenIndex = input.indexOf('-')
        return if (hyphenIndex == -1 || input.indexOf('-', hyphenIndex + 1) != -1) {
            input // Handle cases with no hyphen or multiple hyphens
        } else input.substring(
            0, hyphenIndex
        ) + "-" + dayOfYear + "-" + input.substring(hyphenIndex + 1)
    }

    fun String.convertExceedTimeToMilliseconds(): Long {
        return if (this.isEmpty()) {
            0 // Handle invalid input gracefully
        } else try {
            // Split the string into hours, minutes, and seconds
            val timeParts = this.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (timeParts.size != 3) {
                return -1 // Handle invalid format (not HH:MM:SS)
            }

            // Parse each part to double (handles leading zeros)
            val hours = timeParts[0].toDouble()
            val minutes = timeParts[1].toDouble()
            val seconds = timeParts[2].toDouble()

            // Validate time components (within valid ranges)
            if (hours < 0 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59) {
                return -1 // Handle invalid time values
            }

            // Convert to milliseconds
            (hours * 3600 * 1000 + minutes * 60 * 1000 + seconds * 1000).toLong()
        } catch (e: NumberFormatException) {
            0// Handle parsing errors
        }
    }

    fun convertDateIntoAdminORLocalTimeZone(
        originalTimestamp: String, adminTimeZoneID: String? = null
    ): String {
        // Parse the original timestamp
        val originalDateTime = ZonedDateTime.parse(originalTimestamp)
        // Define the target time zone
        val targetZoneId = if (adminTimeZoneID.isNullOrEmpty()) {
            ZoneId.of(TimeZone.getDefault().toZoneId().id)
        } else {
            // Create a TimeZone object for the desired time zone
            val targetTimeZone =
                TimeZone.getTimeZone(adminTimeZoneID)// Replace with your actual time zone ID
            ZoneId.of(targetTimeZone.id)
        }

        // Convert to the target time zone
        val targetDateTime = originalDateTime.withZoneSameInstant(targetZoneId)
        // Define the desired format
        val formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_TIMEZONE_FORMAT)
        // Format the converted timestamp
        return targetDateTime.format(formatter)
    }

    fun getCurrentSystemTimeByTimeZone(): Long {
        val calendarGiven = Calendar.getInstance()
        val targetTimeZone = TimeZone.getTimeZone(TIMEZONEIDENTIFIER)
        calendarGiven.timeZone = targetTimeZone
        return calendarGiven.time.time
    }

    fun handleServerErrorResponse(ee: HttpException): ErrorResponseModel {
        val msg = arrayListOf("Server Down, Please Retry Again!")
        val asd = ee.response()?.errorBody()?.source().let {
            try {
                val errorBodySb = StringBuilder()
                try {
                    BufferedReader(
                        InputStreamReader(
                            ee.response()?.errorBody()?.byteStream()
                        )
                    ).use { reader ->
                        var line: String?
                        while (reader.readLine().also { line = it } != null) {
                            errorBodySb.append(line)
                        }
                    }
                    Log.d("TAG", "errorBodySb: $errorBodySb")
                    return@let Gson().fromJson<ErrorResponseModel?>(
                        errorBodySb.toString(),
                        ErrorResponseModel::class.java
                    ) ?: ErrorResponseModel(
                        statusCode = ee.code(),
                        messages = if (ee.code() == 500) msg else arrayListOf()
                    )
                } catch (e1: IOException) {
                    Log.e("RetrofitError", "Error reading error body", e1)
                }
            } catch (eeee: Exception) {
                if (ee.code() == 500) {
                    ErrorResponseModel(statusCode = 500, messages = msg)
                }
                Log.d("TAG", "eeee   : $eeee")
            }
            ErrorResponseModel(statusCode = ee.code())
        }
        return asd
    }

    fun String.toMilliseconds(): Long {
        val inputFormat = SimpleDateFormat(Constants.DATE_TIME_TIMEZONE_FORMAT, Locale.getDefault())
        val targetTimeZone = TimeZone.getTimeZone(TIMEZONEIDENTIFIER)
        inputFormat.timeZone = targetTimeZone
        return try {
            val date = inputFormat.parse(this)
            date?.time ?: 0
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    fun logoutDevice(context: Context, isReport: Boolean = false) {
        if (!isReport)
            saveAuthToken(context, "")
        saveLoginDate(context, "")
        saveIsShiftStarted(context, false)
        saveShiftId(context, "")
        saveLastPrintedSerialNo(context, "")
    }

    fun showLoginAutoShiftEndDialog(context: Activity) {
        AlertDialog.Builder(context)
            .setTitle(context.resources.getString(R.string.auto_shift_end_title))
            .setMessage(context.resources.getString(R.string.auto_shift_end_message))
            .setPositiveButton(
                android.R.string.ok
            ) { dialogInterface, _ ->
                dialogInterface.dismiss()
                logoutDevice(context)
                context.startActivity(Intent(context, ResolveParkingActivity::class.java)) //pin activity
                context.finish()
            }.setCancelable(false).show()
    }
}
fun getTodayDate(): String {
    val sdf = SimpleDateFormat(Constants.DATE_TIME_FORMAT_E_MMM_DD_YYYY, Locale.getDefault())
    val targetTimeZone = TimeZone.getTimeZone(TIMEZONEIDENTIFIER)
    sdf.timeZone = targetTimeZone
    val formattedDateTime = sdf.format(Calendar.getInstance().time)
    return formattedDateTime
}
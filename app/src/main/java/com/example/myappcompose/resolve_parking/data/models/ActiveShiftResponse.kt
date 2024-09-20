package com.example.myappcompose.resolve_parking.data.models

data class ActiveShiftResponse(
    val isShiftActiveAtCurrentLocation: Boolean,
    val currentDeviceShift : ShiftResponse ?
)

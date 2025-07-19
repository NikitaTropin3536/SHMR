package com.example.core.error

class OfflineDataException(
    val data: List<Any>
) : Exception("Offline cached data loaded")

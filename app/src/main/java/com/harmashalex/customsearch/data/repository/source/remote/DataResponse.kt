package com.harmashalex.customsearch.data.repository.source.remote

sealed class DataResponse

data class SuccessResponse<T> (val data: T): DataResponse()

data class ErrorResponse(val errorCode: Int, val message:String): DataResponse()
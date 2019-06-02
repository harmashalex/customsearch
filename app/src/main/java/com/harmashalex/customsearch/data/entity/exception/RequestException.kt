package com.harmashalex.customsearch.data.entity.exception

import java.lang.Exception

data class RequestException(val errorCode: Int, val errorMessage: String): Exception()
package com.efedaniel.spotifystats.network.interceptors

import com.efedaniel.spotifystats.network.exceptions.BadRequestException
import com.efedaniel.spotifystats.network.exceptions.NotFoundException
import com.efedaniel.spotifystats.network.exceptions.UnauthorizedException
import com.efedaniel.spotifystats.network.exceptions.UnknownException
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.isSuccessful) {
            return response
        } else {
            throw getException(response)
        }
    }

    private fun getException(response: Response): Exception = when (response.code) {
        HTTP_BAD_REQUEST -> BadRequestException(getErrorMessage(response.body))
        HTTP_UNAUTHORIZED -> UnauthorizedException(getErrorMessage(response.body))
        HTTP_NOT_FOUND -> NotFoundException(getErrorMessage(response.body))
        else -> UnknownException(getErrorMessage(response.body))
    }

    private fun getErrorMessage(body: ResponseBody?): String? {
        body ?: return null

        val errorObject = JSONObject(body.string()).getJSONObject(ERROR_KEY)
        return if (errorObject.has(MESSAGE_KEY)) {
            errorObject.getString(MESSAGE_KEY)
        } else {
            null
        }
    }

    private companion object {
        const val ERROR_KEY = "error"
        const val MESSAGE_KEY = "message"
    }
}
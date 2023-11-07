package com.sopp.auth.service

import com.sopp.auth.client.TcknClient
import com.sopp.auth.model.TcknVerifyRequest
import org.springframework.stereotype.Service
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
@Service
class VerifyService(
    private val tcknClient: TcknClient
) {

    suspend fun sendSoapRequest(request: TcknVerifyRequest): Boolean? {
        val response = tcknClient.verifyTckn(request)
        val responseBody = response.body?.string()

        if (response.isSuccessful && responseBody != null) {
            return extractResultFromSoapResponse(responseBody)
        }

        return null
    }


    private fun extractResultFromSoapResponse(responseBody: String): Boolean? {
        val document: Document = Jsoup.parse(responseBody)
        val resultElement = document.select("TCKimlikNoDogrulaResult").first()
        val resultText = resultElement?.text()

        return resultText?.equals("true", ignoreCase = true)
    }
}
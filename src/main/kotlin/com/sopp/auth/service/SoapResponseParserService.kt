package com.sopp.auth.service

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service

@Service
class SoapResponseParserService {
    fun extractResultFromSoapResponse(responseBody: String): Boolean {
        val document: Document = Jsoup.parse(responseBody)
        val resultElement = document.select("TCKimlikNoDogrulaResult").first()
        val resultText = resultElement?.text()

        return resultText?.equals("true", ignoreCase = true)!!
    }
}

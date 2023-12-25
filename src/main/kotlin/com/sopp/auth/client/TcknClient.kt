package com.sopp.auth.client

import com.sopp.auth.model.TcknVerifyRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.springframework.stereotype.Component

@Component
class TcknClient {
    fun verifyTckn(request: TcknVerifyRequest): Response {
        val client = OkHttpClient()

        val mediaType = "application/soap+xml; charset=utf-8".toMediaTypeOrNull()

        val body = RequestBody.create(mediaType, generateSoapRequestXml(request))

        val request =
            Request.Builder()
                .url("https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx")
                .method("POST", body)
                .addHeader("Content-Type", "application/soap+xml; charset=utf-8")
                .build()
        return client.newCall(request).execute()
    }

    private fun generateSoapRequestXml(request: TcknVerifyRequest): String {
        return """
            <?xml version="1.0" encoding="utf-8"?>
            <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
              <soap12:Body>
                <TCKimlikNoDogrula xmlns="http://tckimlik.nvi.gov.tr/WS">
                  <TCKimlikNo>${request.tc}</TCKimlikNo>
                  <Ad>${request.firstname}</Ad>
                  <Soyad>${request.surname}</Soyad>
                  <DogumYili>${request.birthYear}</DogumYili>
                </TCKimlikNoDogrula>
              </soap12:Body>
            </soap12:Envelope>
            """.trimIndent()
    }
}

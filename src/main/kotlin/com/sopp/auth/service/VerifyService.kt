package com.sopp.auth.service

import com.sopp.auth.client.TcknClient
import com.sopp.auth.entity.VerifiedUserEntity
import com.sopp.auth.model.TcknVerifyRequest
import com.sopp.auth.model.VerifyUserModel
import com.sopp.auth.repository.VerifiedUserRepository
import org.springframework.stereotype.Service
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
@Service
class VerifyService(
    private val tcknClient: TcknClient,
    private val verifiedUserRepository: VerifiedUserRepository
) {

    suspend fun isCustomerVerified(customerId: String): Boolean{
        return verifiedUserRepository.existsById(customerId)
    }

    suspend fun getCustomerVerificationInfo(customerId: String): VerifiedUserEntity {
        return verifiedUserRepository.findById(customerId).get()
    }

    suspend fun verifyCustomer(request: VerifyUserModel): Boolean {
        val response = tcknClient.verifyTckn(TcknVerifyRequest(request))
        val responseBody = response.body?.string()

        if (! response.isSuccessful || responseBody == null) {
            return false
        }

        if (extractResultFromSoapResponse(responseBody) == true){
            verifiedUserRepository.save(VerifiedUserEntity(request))
            //ToDo: Send request to wallet service to create wallet for verified user
            return true
        }

        return false

    }


    private fun extractResultFromSoapResponse(responseBody: String): Boolean? {
        val document: Document = Jsoup.parse(responseBody)
        val resultElement = document.select("TCKimlikNoDogrulaResult").first()
        val resultText = resultElement?.text()

        return resultText?.equals("true", ignoreCase = true)
    }
}
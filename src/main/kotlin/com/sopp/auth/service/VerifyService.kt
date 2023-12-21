package com.sopp.auth.service

import com.sopp.auth.client.TcknClient
import com.sopp.auth.entity.VerifiedUserEntity
import com.sopp.auth.model.ResponseModel
import com.sopp.auth.model.TcknVerifyRequest
import com.sopp.auth.model.VerifyUserModel
import com.sopp.auth.repository.VerifiedUserRepository
import org.springframework.stereotype.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Service
class VerifyService(
    private val tcknClient: TcknClient,
    private val verifiedUserRepository: VerifiedUserRepository,
    private val walletService: WalletService,
    private val soapResponseParserService: SoapResponseParserService
) {
    val logger: Logger = LoggerFactory.getLogger(VerifyService::class.java)

    suspend fun isCustomerVerified(customerId: String): Boolean{
        return verifiedUserRepository.existsById(customerId)
    }

    suspend fun getCustomerVerificationInfo(customerId: String): VerifiedUserEntity {
        return verifiedUserRepository.findById(customerId).get()
    }

    suspend fun verifyCustomer(request: VerifyUserModel): Boolean {

        if(verifiedUserRepository.findByUserId(request.userId).isPresent){
            println("Entered..")
            return false
        }

        val response = tcknClient.verifyTckn(TcknVerifyRequest(request))
        val responseBody = response.body?.string()

        if (! response.isSuccessful || responseBody == null) {
            return false
        }

        if (soapResponseParserService.extractResultFromSoapResponse(responseBody)){
            verifiedUserRepository.save(VerifiedUserEntity(request))
            try {
                walletService.createWallet(customerId = request.userId)
            }catch (e: Exception){
                logger.error("Wallet creation error!")
            }
            return true
        }
        return true
    }
}
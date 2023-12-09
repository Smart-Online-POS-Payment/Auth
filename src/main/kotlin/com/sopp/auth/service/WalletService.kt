package com.sopp.auth.service

import com.sopp.auth.client.WalletClient
import org.springframework.stereotype.Service

@Service
class WalletService(
    private val walletClient: WalletClient
) {

    suspend fun createWallet(customerId: String){
        walletClient.createWallet(customerId)
    }
}
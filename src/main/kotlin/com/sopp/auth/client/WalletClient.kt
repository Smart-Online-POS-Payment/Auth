package com.sopp.auth.client

import com.sopp.auth.model.WalletModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class WalletClient(
    @Qualifier("walletWebClient")
    val client: WebClient,
) {
    suspend fun createWallet(customerId: String) {
        client
            .post()
            .uri("/$customerId")
            .retrieve().awaitBody<WalletModel>()
    }
}

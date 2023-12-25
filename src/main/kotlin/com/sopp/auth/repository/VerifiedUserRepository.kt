package com.sopp.auth.repository

import com.sopp.auth.entity.VerifiedUserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface VerifiedUserRepository : CrudRepository<VerifiedUserEntity, String> {
    fun findByUserId(userId: String): Optional<VerifiedUserEntity>
}

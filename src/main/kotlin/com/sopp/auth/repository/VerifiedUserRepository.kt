package com.sopp.auth.repository

import com.sopp.auth.entity.VerifiedUserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VerifiedUserRepository: CrudRepository<VerifiedUserEntity, String> {
}
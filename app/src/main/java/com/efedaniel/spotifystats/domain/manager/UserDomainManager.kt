package com.efedaniel.spotifystats.domain.manager

import com.efedaniel.spotifystats.domain.mapper.UserMapper
import com.efedaniel.spotifystats.domain.model.User
import com.efedaniel.spotifystats.network.service.UserApi
import com.efedaniel.spotifystats.persistence.cache.UserCache
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserDomainManager @Inject constructor(
    private val userApi: UserApi,
    private val mapper: UserMapper,
    private val cache: UserCache,
) {

    fun fetchCurrentUser(): Single<User> = userApi
        .getCurrentUser()
        .map(mapper::dtoToDomain)
        .doOnSuccess { cache.storeUser(mapper.domainToEntity(it)) }

    fun retrieveCurrentUserFromCache(): Single<User> =
        cache
            .retrieveUser()
            ?.let { Single.just(mapper.mapEntityToDomain(it)) }
            ?: Single.error(Exception("User does not exist in cache"))
}
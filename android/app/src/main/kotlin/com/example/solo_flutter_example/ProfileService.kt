package com.example.solo_flutter_example

import com.example.solo_flutter_example.Profile
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface ProfileService {

    @GET("user/context")
    fun getProfile(): Observable<Profile>
}
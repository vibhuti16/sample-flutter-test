package com.example.solo_flutter_example

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugins.GeneratedPluginRegistrant
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import androidx.annotation.NonNull
import android.util.Log


class MainActivity: FlutterActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        FlutterMain.startInitialization(this);
//        super.onCreate(savedInstanceState);
//        GeneratedPluginRegistrant.registerWith(this);
//        MethodChannel(
//            getFlutterView(),
//            CHANNEL
//        ).setMethodCallHandler(
//            object : MethodCallHandler {
//                override fun onMethodCall(methodCall: MethodCall, result: Result) {
//                    if (methodCall.method.equals("getUserDetails")) {
//                        val accessToken = methodCall.arguments
//                        Log.d("accessToken", accessToken)
//                        RetrofitClientInstance.setToken("")
//                         val profileService: ProfileService =
//                            RetrofitClientInstance.retrofitInstance.create(
//                                ProfileService::class.java
//                            )
//                        profileService.getProfile()
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(
//                                { profile ->
//                                    val profileName = profile.first_name
//                                    result.success(profileName)
//                                },
//                                { throwable ->
////                                    Log.e("HomeActivity", throwable.message ?: "onError")
//                                }
//                            )
//                        val title: String = methodCall.argument("title")
//                        val id: Int = methodCall.argument("id")
//                        val model: com.deremakif.methodchannel.NewModel =
//                            com.deremakif.methodchannel.NewModel()
//                        model.id = 1
//                        model.title = "Android model is here! And passed argument: $title"
//                        result.success(model.toString())
//                    }
//                }
//            }
//        )
//    }

    private val CHANNEL = "com.example.solo_flutter_example/getUserDetails"


    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            if (call.method.equals("getUserDetails")) {
                val accessToken = (call.arguments as Map<String,String>)["access_token"].toString()
                Log.d("accessToken******", accessToken)
                RetrofitClientInstance.setToken(accessToken)
                val profileService: ProfileService =
                    RetrofitClientInstance.retrofitInstance.create(
                        ProfileService::class.java
                    )
                profileService.getProfile()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { profile ->
                            val profileName = profile.first_name
                            result.success(profileName)
                        },
                        { throwable ->
                                    Log.d("HomeActivity", throwable.message ?: "onError")
                        }
                    )
//                        val title: String = methodCall.argument("title")
//                        val id: Int = methodCall.argument("id")
//                        val model: com.deremakif.methodchannel.NewModel =
//                            com.deremakif.methodchannel.NewModel()
//                        model.id = 1
//                        model.title = "Android model is here! And passed argument: $title"
//                        result.success(model.toString())
            }
        }
    }
}


/*
 *    Copyright (C) 2016 Amit Shekhar
 *    Copyright (C) 2011 Android Open Source Project
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.nightshade.forwarding

import android.app.Application
import android.content.Context
import android.graphics.BitmapFactory
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ConnectionQuality
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


/**
 * Created by amitshekhar on 22/03/16.
 */
class MyApplication : Application() {



    override fun onCreate() {
        super.onCreate()




        val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()
        AndroidNetworking.initialize(applicationContext, okHttpClient)
        val options = BitmapFactory.Options()
        AndroidNetworking.setBitmapDecodeOptions(options)
        AndroidNetworking.enableLogging()

        AndroidNetworking.setConnectionQualityChangeListener { connectionQuality, _ ->
            when (connectionQuality) {
                ConnectionQuality.EXCELLENT -> System.out.println("Tot")

                ConnectionQuality.POOR -> System.out.println("Duoc")
                else -> {
                    System.out.println("Te")
                }
            }
        }


    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }


}

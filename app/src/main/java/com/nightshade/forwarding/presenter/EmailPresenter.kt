package com.nightshade.forwarding.presenter

import android.os.Looper
import android.util.Log
import com.androidnetworking.error.ANError
import com.nightshade.lolproject.model.Response
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject


/**
 * Created by sega4 on 27/07/2017.
 */

class EmailPresenter(view: emailDetail) {
    val BASE_URL = "http://45.77.36.109:8000/api/v2/"
    internal var mEmailListView = view
    var userdetail = "USERDETAIL"
    private val disposables = CompositeDisposable()
    val jsonObject = JSONObject()
    fun sendToEmail(email : String) {
        try {
            jsonObject.put("email", email)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        disposables.add(getObservableEmail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getDisposableObserver()))


    }
   
  
    private fun getObservableEmail(): Observable<Response> {
        return  Rx2AndroidNetworking.post(BASE_URL +"data/{id}")
                .addJSONObjectBody(jsonObject)
                .setTag(this)
                .build()
                .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
                    Log.d(userdetail, " timeTakenInMillis : " + timeTakenInMillis)
                    Log.d(userdetail, " bytesSent : " + bytesSent)
                    Log.d(userdetail, " bytesReceived : " + bytesReceived)
                    Log.d(userdetail, " isFromCache : " + isFromCache)
                }
                .getObjectObservable(Response::class.java)
    }

    private fun getDisposableObserver(): DisposableObserver<Response> {

        return object : DisposableObserver<Response>() {

            override fun onNext(response: Response) {
                Log.d(userdetail, "onResponse isMainThread : " + (Looper.myLooper() == Looper.getMainLooper()).toString())
            //*/    if(response?.listproduct?.size!! > 0)
           //     {
                Log.d("ssssss","!1")

                    mEmailListView.isSuccessful(true)
              //  }else{
           //         mEmailListView.setErrorNotFound()*/
             //   }
            }
            override fun onError(e: Throwable) {
                
                if (e is ANError) {


                    Log.d(userdetail, "onError errorCode : " + e.errorCode)
                    Log.d(userdetail, "onError errorBody : " + e.errorBody)
                    Log.d(userdetail, e.errorDetail + " : " + e.message)
                 


                } else {
                    Log.d(userdetail, "onError errorMessage : " + e.message)
                 
                }
                mEmailListView.isSuccessful(false)
            }

            override fun onComplete() {

            }
        }
    }
  //================





    interface emailDetail {
        fun isSuccessful(isSuccessful: Boolean)


    }
    fun stopRequest() {
        disposables.clear()
        println("cancel")
    }
}

package com.example.gosy_mobilka

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.VK



class VKontakteActivity : AppCompatActivity() {
    private var access_token: VKAccessToken? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_v_kontakte)
/*
        //Получаем токен
        access_token = VKAccessToken.tokenFromSharedPreferences(this, "VK_ACCESS_TOKEN");

        //Инициализируем
        VKSdk.initialize(object : VKSdkListener() {
            override fun onCaptchaError(captchaError: VKError?) {
                VKCaptchaDialog(captchaError).show()
                Log.d("onCaptchaError")
            }

            override fun onTokenExpired(expiredToken: VKAccessToken?) {
                Log.d("onTokenExpired")
                VKSdk.authorize()
            }

            override fun onAccessDenied(authorizationError: VKError) {
                Builder(this@VKontakteActivity).setMessage(authorizationError.errorMessage).show()
            }

            override fun onReceiveNewToken(token: VKAccessToken) {
                access_token = token
                access_token.saveTokenToSharedPreferences(this@VKontakteActivity, "VK_ACCESS_TOKEN")
                getMeInfo()
            }
        }, 7862524, access_token)

        //Сохраняем активити
        VKUIHelper.onResume(this);

        //Запускаем авторизацию
        Log.w("access_token: " + access_token);
        VKSdk.authorize();*/
    }



}
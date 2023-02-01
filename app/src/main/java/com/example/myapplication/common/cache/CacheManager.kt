package com.example.myapplication.common.cache

import android.annotation.SuppressLint
import android.content.Context
import com.example.myapplication.common.appController.AppControllerContract
import java.lang.UnsupportedOperationException


object CacheManager {

    private const val prefsName: String = "prefsName"

    private enum class CacheKeys {
        FIREBASE_URL,
    }


    @SuppressLint("CommitPrefEdits")
    private fun <T : Any> setInCache(key: String, value: T) {
        val sharedPreferences =
            AppControllerContract.get().properContext.getSharedPreferences(
                prefsName, 0
            )
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key, value as String)
            is Int -> editor.putInt(key, value as Int)
            is Boolean -> editor.putBoolean(key, value as Boolean)
            is Float -> editor.putFloat(key, value as Float)
            is Long -> editor.putLong(key, value as Long)
        }
        editor.apply()
    }

    private inline fun <reified T : Any> getFromCache(
        key: String,
        defaultValue: T? = null,
        ctx: Context? = null
    ): T {
        val mCtx =
            ctx ?: AppControllerContract.get().properContext
        val sharedPreferences =
            mCtx.getSharedPreferences(prefsName, 0)
        return when (T::class) {
            String::class -> sharedPreferences.getString(key, defaultValue as? String ?: "") as T
            Int::class -> sharedPreferences.getInt(key, defaultValue as? Int ?: 0) as T
            Boolean::class -> sharedPreferences.getBoolean(
                key,
                defaultValue as? Boolean ?: false
            ) as T
            Float::class -> sharedPreferences.getFloat(key, defaultValue as? Float ?: -1f) as T
            Long::class -> sharedPreferences.getLong(key, defaultValue as? Long ?: -1) as T
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    private fun clearAllCache() {
        AppControllerContract.get().properContext.getSharedPreferences(
            prefsName, 0
        ).edit().clear()
            .apply()
    }


}
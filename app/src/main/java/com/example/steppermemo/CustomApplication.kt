package com.example.steppermemo

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
//        val config = RealmConfiguration.Builder().build()
        // Realmのモデルクラスを変更した場合
        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
    }
}
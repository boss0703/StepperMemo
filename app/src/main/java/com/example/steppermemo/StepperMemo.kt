package com.example.steppermemo

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class StepperMemo : RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var date: String = ""
    var count: Long = 0
    var time: String = ""
    // 消費kcalの計算式は 体重 * 0.094 * 時間(分) * 補正係数
    // 補正係数は 男性 20代 1.0 30代 0.96 40代 0.94 女性 20代 0.95 30代 0.87 40代 0.85
    var kcal: Double = 0.0
    var memo: String = ""
}
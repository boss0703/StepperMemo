package com.example.steppermemo.util

import java.util.*

class UtilCalendar() {
    // 年月日から曜日を取得する為の変数
    fun getDayOfTheWeek(year: Int, month: Int,day: Int): String{
        val cal = Calendar.getInstance()
        cal.set(year, month, day)
        return when(cal.get(Calendar.DAY_OF_WEEK)){
            1 -> "日曜日"
            2 -> "月曜日"
            3 -> "火曜日"
            4 -> "水曜日"
            5 -> "木曜日"
            6 -> "金曜日"
            else -> "土曜日"
        }
    }
}
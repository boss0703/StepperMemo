package com.example.steppermemo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.steppermemo.util.UtilCalendar
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {
    private val tag = "StepperMemo"
    private lateinit var realm: Realm

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        // dbのインスタンス取得
        realm = Realm.getDefaultInstance()
        // NumberPickerの設定
        np1.minValue = 0
        np1.maxValue = 99
        np1.value = 20
        np2.minValue = 0
        np2.maxValue = 59
        np2.value = 30

        // 日付のEditView押下時
        editDate.setOnClickListener {
            val calender = Calendar.getInstance()
            val year = calender.get(Calendar.YEAR)
            val month = calender.get(Calendar.MONTH)
            val day = calender.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ _, y, m, d ->
                val dayOfTheWeek = UtilCalendar().getDayOfTheWeek(y,m,d)
                Toast.makeText(this, "日付を選択しました ${y}/${m}/${d} $dayOfTheWeek", Toast.LENGTH_LONG).show()
                editDate.setText("${y}/${m}/${d} $dayOfTheWeek", TextView.BufferType.NORMAL)
            }, year,month,day
            )
            dpd.show()
        }

        // 作成ボタン押下時
        create_button.setOnClickListener {
            // TODO 詳細な入力バリデーションは最後に作成
            when {
                editDate.text.isNullOrEmpty() -> {
                    editDate.error = "入力が必須です"
                }
                editCount.text.isNullOrEmpty() -> {
                    editCount.error = "入力が必須です"
                }
                else -> {
                    realm.executeTransaction {
                        val maxId = realm.where<StepperMemo>().max("id")
                        val nextId = (maxId?.toLong() ?: 0L) +1L
                        val stepperMemo = realm.createObject<StepperMemo>(nextId)
                        stepperMemo.date = editDate.text.toString()
                        stepperMemo.count = editCount.text.toString().toLong()
                        stepperMemo.time = "${np1.value}:${np2.value}"
                        // 消費kcalの計算式は 体重 * 0.094 * 時間(分) * 補正係数
                        // 補正係数は 男性 20代 1.0 30代 0.96 40代 0.94 女性 20代 0.95 30代 0.87 40代 0.85
                        stepperMemo.kcal = 90.0 * 0.094 * (np1.value.toDouble() + np2.value.toDouble() / 60) * 1.0
                        stepperMemo.memo = editMemo.text.toString()
                    }
                    Toast.makeText(this, "保存しました", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

        // キャンセルボタン押下時
        cancel_button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}

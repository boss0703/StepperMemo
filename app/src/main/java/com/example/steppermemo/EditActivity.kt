package com.example.steppermemo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.steppermemo.util.UtilCalendar
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        editDate.setOnClickListener {
            val calender = Calendar.getInstance()
            val year = calender.get(Calendar.YEAR)
            val month = calender.get(Calendar.MONTH)
            val day = calender.get(Calendar.DAY_OF_MONTH)
            val dtp = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,y,m,d ->
                val dayOfTheWeek = UtilCalendar().getDayOfTheWeek(y,m,d)
                Toast.makeText(this, "日付を選択しました ${y}/${m}/${d} ${dayOfTheWeek}",Toast.LENGTH_LONG).show()
                editDate.setText("${y}/${m}/${d} ${dayOfTheWeek}", TextView.BufferType.NORMAL)
            }, year,month,day
            )
            dtp.show()

        }
    }
}

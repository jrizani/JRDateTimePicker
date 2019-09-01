package io.github.jrizani.jrdatetimepicker.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.jrizani.jrdatetimepicker.DateTimePicker
import io.github.jrizani.jrdatetimepicker.DateTimePicker.OnDateTimeSelectListener
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

/*=============================*/
/*            AUTHOR           */
/*          JULIAN NR          */
/* juliannoorrizani@gmail.com  */
/*         01 Sep 2019         */
/*=============================*/

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = DateTimePicker.Builder(
            this,
            R.style.AppTheme_Dialog
        )
        val date = Calendar.getInstance()
        builder.setInitialDateTime(
            date.get(Calendar.YEAR),
            date.get(Calendar.MONTH),
            date.get(Calendar.DAY_OF_MONTH),
            date.get(Calendar.HOUR_OF_DAY),
            date.get(Calendar.MINUTE),
            true
        )
        builder.setOnDateTimeChangeListener(object : OnDateTimeSelectListener {
            override fun onDateTimeSelected(year: Int, monthOfYear: Int, dayOfMonth: Int, hourOfDay: Int, minute: Int) {
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth, hourOfDay, minute, 0)
                val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
                et_date.setText(formatter.format(selectedDate.time))
            }
        })

        val dialog = builder.create()

        et_date.setOnClickListener {
            dialog.show()
        }
    }
}
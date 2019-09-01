package io.github.jrizani.jrdatetimepicker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.annotation.StyleRes

/*=============================*/
/*            AUTHOR           */
/*          JULIAN NR          */
/* juliannoorrizani@gmail.com  */
/*         01 Sep 2019         */
/*=============================*/

class DateTimePicker private constructor(private val context: Context, @StyleRes private val themeResId: Int) {

    var year: Int? = null
    var monthOfYear: Int? = null
    var dayOfMonth: Int? = null
    var hourOfDay: Int? = null
    var minute: Int? = null
    var is24HourView: Boolean = false
    var minDate: Long? = null
    var maxDate: Long? = null
    var listener: OnDateTimeSelectListener? = null

    fun show() {
        if (year.isNull || monthOfYear.isNull || dayOfMonth.isNull || hourOfDay.isNull || minute.isNull || is24HourView.isNull) {
            throw ExceptionInInitializerError(
                "Cannot show before set initial date and time,"
                        + " use setInitialDateTime() on Builder or set it one by one on DateTimePicker instance"
            )
        }

        val datePickerDialog = DatePickerDialog(context, themeResId)

        datePickerDialog.updateDate(year!!, monthOfYear!!, dayOfMonth!!)

        datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
            TimePickerDialog(
                context,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    this.year = year
                    this.monthOfYear = month
                    this.dayOfMonth = dayOfMonth
                    this.hourOfDay = hourOfDay
                    this.minute = minute

                    listener?.onDateTimeSelected(year, month, dayOfMonth, hourOfDay, minute)
                },
                hourOfDay!!,
                minute!!,
                is24HourView
            ).show()
        }

        minDate?.let { datePickerDialog.datePicker.minDate = it }
        maxDate?.let { datePickerDialog.datePicker.maxDate = it }

        datePickerDialog.show()
    }


    class Builder(context: Context, @StyleRes private val styleResId: Int) {
        private val instance = DateTimePicker(context, styleResId)

        fun setInitialDateTime(
            year: Int,
            monthOfYear: Int,
            dayOfMonth: Int,
            hourOfDay: Int,
            minute: Int,
            is24HourView: Boolean
        ): Builder {
            instance.year = year
            instance.monthOfYear = monthOfYear
            instance.dayOfMonth = dayOfMonth
            instance.hourOfDay = hourOfDay
            instance.minute = minute
            instance.is24HourView = is24HourView
            return this
        }


        fun setMinDate(minDate: Long): Builder {
            instance.minDate = minDate
            return this
        }

        fun setMaxDate(maxDate: Long): Builder {
            instance.maxDate = maxDate
            return this
        }

        fun setOnDateTimeChangeListener(
            listener: OnDateTimeSelectListener
        ): Builder {
            instance.listener = listener
            return this
        }

        fun create() = instance

        fun show(): DateTimePicker {
            instance.show()
            return instance
        }
    }

    interface OnDateTimeSelectListener {
        fun onDateTimeSelected(
            year: Int,
            monthOfYear: Int,
            dayOfMonth: Int,
            hourOfDay: Int,
            minute: Int
        )
    }
}
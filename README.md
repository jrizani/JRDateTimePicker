# JRDateTimePicker
[![](https://jitpack.io/v/jrizani/JRDateTimePicker.svg)](https://jitpack.io/#jrizani/JRDateTimePicker)

Helper Library to Make Date Time Picker Dialog Easily

# Installation
`implementation 'com.github.jrizani:JRDateTimePicker:$version'`

# How To
## Kotlin
```
val builder = DateTimePicker.Builder(context, themeResId)
        
builder.setInitialDateTime( //you must call this method
    year,
    month_of_year,
    day_of_month,
    hour_of_day,
    minute,
    is24HourView
    )
    
    
builder.setOnDateTimeChangeListener(object : OnDateTimeSelectListener {
    override fun onDateTimeSelected(year: Int, monthOfYear: Int, dayOfMonth: Int, hourOfDay: Int, minute: Int) {
        //do whatever you want with selected date time when it selected
        }
    })

val dialog = builder.create() //method to create DateTimePicker instance

et_date.setOnClickListener {
    //method to show dialog, you can directly call show on builder instance like 'builder.show' that also return DateTimePicker instance
    dialog.show()
    }
```

## Java
```
final DateTimePicker.Builder builder = new DateTimePicker.Builder(context, themeResId);

builder.setInitialDateTime(//you must call this method
        year,
        month_of_year,
        day_of_month,
        hour_of_day,
        minute,
        is24HourView                                                                    
);
builder.setOnDateTimeChangeListener(new DateTimePicker.OnDateTimeSelectListener() {
    @Override
    public void onDateTimeSelected(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute) {
        //do whatever you want with selected date time when it selected
    }
});

final DateTimePicker dialog = builder.create(); //method to create DateTimePicker instance
                                                                                                              
etDate.setOnClickListener(new View.OnClickListener() {
    @Overrid
    public void onClick(View v) {
       //method to show dialog, you can directly call show on builder instance like 'builder.show' that also return DateTimePicker instance
        dialog.show();
    }
});
```

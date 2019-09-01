package io.github.jrizani.jrdatetimepicker.example;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.github.jrizani.jrdatetimepicker.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/*=============================*/
/*            AUTHOR           */
/*          JULIAN NR          */
/* juliannoorrizani@gmail.com  */
/*         01 Sep 2019         */
/*=============================*/

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etDate = findViewById(R.id.et_date);

        final DateTimePicker.Builder builder = new DateTimePicker.Builder(this, R.style.AppTheme_Dialog);
        Calendar date = Calendar.getInstance();
        builder.setInitialDateTime(
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH),
                date.get(Calendar.HOUR_OF_DAY),
                date.get(Calendar.MINUTE),
                true
        );
        builder.setOnDateTimeChangeListener(new DateTimePicker.OnDateTimeSelectListener() {
            @Override
            public void onDateTimeSelected(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, monthOfYear, dayOfMonth, hourOfDay, minute, 0);
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault());
                etDate.setText(formatter.format(selectedDate.getTime()));
            }
        });

        final DateTimePicker dialog = builder.create();

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }
}

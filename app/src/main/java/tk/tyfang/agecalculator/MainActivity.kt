package tk.tyfang.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View;
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variables
        var date:String = "";

        //component
        val btnDatePicker = findViewById<Button>(R.id.btnDateButton);
        val calculateBtn = findViewById<Button>(R.id.calculateBtn)
        val selectedDateText = findViewById<TextView>(R.id.tvDate);
        val dateInYearsText = findViewById<TextView>(R.id.AgeInMinute);

        //display the calender to show the date
        fun clickDatePicker(view: View){
            val myCalender = Calendar.getInstance();
            val year = myCalender.get(Calendar.YEAR);
            val month = myCalender.get(Calendar.MONTH);
            val day = myCalender.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{
                        view, year, month, dayOfMonth ->
                    date = "${dayOfMonth}/${month}/${year}";
                    selectedDateText.text = "${dayOfMonth}/${month}/${year}";
                }, year, month, day).show()
        }

       //calculate the date into time
       fun convertDateIntoMin(givenDate:String): Date? {
           try {
               val date = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
               val formattedDated = date.parse(givenDate);
               return formattedDated;
           }catch (e: Exception){
               println("Error $e")
           }
           return null
       }

        btnDatePicker.setOnClickListener{ view ->
            clickDatePicker(view);
        }

        calculateBtn.setOnClickListener{ view ->
            val data = convertDateIntoMin(date);
            //val timeLinePoint = LocalDateTime.parse(data.toString())
            Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show();
        }


    }
}
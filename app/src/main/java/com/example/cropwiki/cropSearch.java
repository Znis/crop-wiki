package com.example.cropwiki;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class cropSearch extends AppCompatActivity {

    RadioButton summer;
    RadioButton winter;
    RadioGroup radiogroup;
    Button search;
    //  DatabaseAccess db;
int temp;
int humid;
StringBuffer buffer = new StringBuffer();;



    String season;

    String season1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_search);

        // db= new DatabaseAccess(this);



        final EditText temperature = (EditText) findViewById(R.id.editText11);
        final EditText humidity = (EditText) findViewById(R.id.editText);
        TextView temp1 = (TextView) findViewById(R.id.textView2);
        TextView humid1 = (TextView) findViewById(R.id.textView9);
        TextView season2 = (TextView) findViewById(R.id.textView3);
        search = (Button) findViewById(R.id.btnSearch);
        summer = (RadioButton) findViewById(R.id.checkBox);
        winter = (RadioButton) findViewById(R.id.checkBox2);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
season="";

        summer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (summer.isChecked()) {
                   season = "Summer";

                }
            }
        });
        winter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (winter.isChecked()) {
                    season = "Winter";

                }
            }
        });
search.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if ((temperature.length() != 0) && (humidity.length() != 0) && (!(season.equals("")))) {
            temp = Integer.parseInt(temperature.getText().toString());
            humid = Integer.parseInt(humidity.getText().toString());

            getData();




        } else {
            Toast.makeText(cropSearch.this, "Please enter all the parameters", Toast.LENGTH_LONG).show();

        }
    }
});





    }
    public void getData(){
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

       Cursor cursor= databaseAccess.allData();
        //Cursor cursor= db.allData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(),"NO DATA",Toast.LENGTH_SHORT).show();

        }
        while (cursor.moveToNext()) {



            int temp_init =Integer.parseInt(cursor.getString(2));
            int temp_final=Integer.parseInt(cursor.getString(3));
            int humid_init=Integer.parseInt(cursor.getString(4));

            int humid_final=Integer.parseInt(cursor.getString(5));
            season1=cursor.getString(6);
            if((temp>=temp_init) && (temp<=temp_final) && (humid>=humid_init) &&(humid<=humid_final) &&(season.equals(season1))){

                buffer.append("Name of the crop    : " + cursor.getString(1) + "\n");
                buffer.append("Temperature Initial : " + cursor.getString(2) + " C"+"\n");
                buffer.append("Temperature Final  : " + cursor.getString(3) + " C"+"\n");
                buffer.append("Humidity Initial       : " + cursor.getString(4) + " %"+"\n");
                buffer.append("Humidity Final        : " + cursor.getString(5) + " %"+"\n");
                buffer.append("Season                     : " + cursor.getString(6) + "\n");
                buffer.append("Description              : " + cursor.getString(7) + "\n\n\n\n");

            }










        }

        showMessage("Search Result", buffer.toString());

        databaseAccess.close();
        buffer.setLength(0);

    }
    public void showMessage(String Title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();


    }

    }


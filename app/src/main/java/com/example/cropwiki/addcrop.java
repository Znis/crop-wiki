package com.example.cropwiki;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class addcrop extends AppCompatActivity {
    DatabaseOpenHelper mydb;
    RadioButton summer;
    RadioButton winter;
    RadioGroup radiogroup;
    Button saveBtn;
    EditText temperature_init;
    EditText temperature_final;
    EditText humidity_init;
    EditText humidity_final;
    TextView temp;
    TextView humid;
    TextView season;
    TextView name;

    EditText name1;
    String season1;
    TextView description;
    EditText description1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcrop);
        mydb = new DatabaseOpenHelper(this);
        description = (TextView) findViewById(R.id.Textview5);
        description1 = (EditText) findViewById(R.id.editText8);
        humidity_init = (EditText) findViewById(R.id.editText4);
        humidity_final = (EditText) findViewById(R.id.editText7);
        temperature_final = (EditText) findViewById(R.id.editText2);
        temperature_init = (EditText) findViewById(R.id.editText11);
        temp = (TextView) findViewById(R.id.textView2);

        season = (TextView) findViewById(R.id.textview4);
        name = (TextView) findViewById(R.id.name);


        name1 = (EditText) findViewById(R.id.name1);

        saveBtn = (Button) findViewById(R.id.saveBtn);

        summer = (RadioButton) findViewById(R.id.checkBox);
        winter = (RadioButton) findViewById(R.id.checkBox2);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        season1 = "";
        summer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                season1 = "Summer";

            }
        });
        winter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                season1 = "Winter";

            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogevent();
            }
        });

    }
    public void dialogevent(){


        AlertDialog.Builder altDial = new AlertDialog.Builder(addcrop.this);
        altDial.setMessage("Confirm?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if ((name1.length() != 0) && (temperature_init.length() != 0) && (temperature_final.length() != 0) && (humidity_init.length() != 0)&& (humidity_final.length() != 0)&& (description1.length() != 0) && (!(season1.equals("")))) {
                            boolean updated = mydb.addData(name1.getText().toString(),
                                    temperature_init.getText().toString(),
                                    temperature_final.getText().toString(),
                                    humidity_init.getText().toString(),
                                    humidity_final.getText().toString(),
                                    description1.getText().toString(),
                                    season1);
                            if (updated){
                                Toast.makeText(addcrop.this, "Crop Profile Added", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(addcrop.this, MainMenu.class);

                                startActivity(intent);}
                            else {
                                Toast.makeText(addcrop.this, "Crop Profile Addition Failed", Toast.LENGTH_LONG).show();
                            }



                        } else {
                            Toast.makeText(addcrop.this, "Please enter all the parameters", Toast.LENGTH_LONG).show();
                        }
                    }


                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = altDial.create();
        alert.setTitle("Add Crop");
        alert.show();

    }



}








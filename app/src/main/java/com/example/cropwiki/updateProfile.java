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

public class updateProfile extends AppCompatActivity {
    DatabaseHelper mydb;
    RadioButton summer;
    RadioButton winter;
    RadioGroup radiogroup;
    Button updateBtn;
    EditText temperature;
    EditText humidity;
    TextView temp;
    TextView humid;
    TextView season;
    TextView name;
    TextView password;
    TextView field;
    EditText name1;
    EditText password1;
    String season1;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        Intent intent = getIntent();
        id = intent.getStringExtra(profileDetail.EXTRA_TEXT);
        mydb = new DatabaseHelper(this);


        temperature = (EditText) findViewById(R.id.editText11);
        humidity = (EditText) findViewById(R.id.humidbro);
        temp = (TextView) findViewById(R.id.textView2);
        humid = (TextView) findViewById(R.id.textView9);
        season = (TextView) findViewById(R.id.seasonbro);
        name = (TextView) findViewById(R.id.name);
        password = (TextView) findViewById(R.id.password);
        field = (TextView) findViewById(R.id.field);
        name1 = (EditText) findViewById(R.id.name1);

        password1 = (EditText) findViewById(R.id.password1);
        updateBtn = (Button) findViewById(R.id.updateBtn);

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
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogevent();
            }
        });
    }
        public void dialogevent(){


                            AlertDialog.Builder altDial = new AlertDialog.Builder(updateProfile.this);
                            altDial.setMessage("Confirm?").setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if ((name1.length() != 0) && (password1.length() != 0) && (temperature.length() != 0) && (humidity.length() != 0) && (!(season1.equals("")))) {
                                                boolean updated = mydb.updateData(id, name1.getText().toString(),
                                                        password1.getText().toString(),
                                                        temperature.getText().toString(),
                                                        humidity.getText().toString(),
                                                        season1);
                                                if (updated){
                                                    Toast.makeText(updateProfile.this, "Profile Updated", Toast.LENGTH_LONG).show();

                                                    Intent intent = new Intent(updateProfile.this, MainMenu.class);

                                                    startActivity(intent);}
                                                    else {
                                                    Toast.makeText(updateProfile.this, "Profile Update Failed", Toast.LENGTH_LONG).show();
                                                }



                                            } else {
                                                Toast.makeText(updateProfile.this, "Please enter all the parameters", Toast.LENGTH_LONG).show();
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
                            alert.setTitle("Update Profile");
                            alert.show();

                        }

    }




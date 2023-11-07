package com.example.cropwiki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class newProfile extends AppCompatActivity {
    DatabaseHelper mydb;
    RadioButton summer;
    RadioButton winter;
    RadioGroup radiogroup;
Button saveBtn;
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
    int a=1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);
        mydb= new DatabaseHelper(this);


         temperature = (EditText) findViewById(R.id.editText11);
         humidity = (EditText) findViewById(R.id.editText3);
         temp=(TextView)findViewById(R.id.textView2);
         humid=(TextView)findViewById(R.id.textView9);
        season=(TextView)findViewById(R.id.textView4);
        name=(TextView)findViewById(R.id.name);
         password=(TextView)findViewById(R.id.password);
         field=(TextView)findViewById(R.id.field);
        name1 = (EditText) findViewById(R.id.name1);
        password1 = (EditText) findViewById(R.id.password1);
        saveBtn=(Button) findViewById(R.id.saveBtn);

        summer =(RadioButton) findViewById(R.id.checkBox);
        winter =(RadioButton) findViewById(R.id.checkBox2);
        radiogroup=(RadioGroup)findViewById(R.id.radiogroup);
        season1="";
        summer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    season1="Summer";

            }
        });
        winter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                season1="Winter";

            }
        });

        saveBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if ((name1.length()!=0)&&(password1.length()!=0)&&(temperature.length()!=0)&&(humidity.length()!=0)&&(!(season1.equals("")))) {
                            boolean isInserted= mydb.insertData(name1.getText().toString(),
                                    password1.getText().toString(),
                                    temperature.getText().toString(),
                                    humidity.getText().toString(),
                                    season1);
                            if(isInserted)
                                Toast.makeText(newProfile.this,"Profile Inserted",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(newProfile.this,"Profile Entry Failed",Toast.LENGTH_LONG).show();


                            name1.setText("");
                            password1.setText("");
                            humidity.setText("");
                            temperature.setText("");
                            season1="";
                            radiogroup.clearCheck();

                        }
                        else{
                            Toast.makeText(newProfile.this, "Please enter all the parameters", Toast.LENGTH_LONG).show();
                        }







    }




                }
        );
    }
}

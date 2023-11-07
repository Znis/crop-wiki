package com.example.cropwiki;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class existingProfile extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {
    Button[] newBtn=new Button[100];
    String buffer;


    public static final String EXTRA_NUMBER ="com.example.myapplication5.EXTRA_NUMBER";
    StringBuffer[] buffer1 =new StringBuffer[100];


    int i=0;
    int k;






    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_profile);
        mydb = new DatabaseHelper(this);
        viewAll();


    }
    @Override
    public void applyTexts(String passwords) {
        Cursor res = mydb.getAllprofiles();
        final String omg;
        final boolean b = res.move(k);
        omg = res.getString(2);

        if (omg.equals(passwords)) {
            Intent intent = new Intent(existingProfile.this, profileDetail.class);

intent.putExtra(EXTRA_NUMBER,k);

            startActivity(intent);



        } else {
            Toast.makeText(existingProfile.this, "Password Mismatch", Toast.LENGTH_LONG).show();
        }
    }

   public void dynabutton(String buffer,final int i) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.rootLayout);
            newBtn[i] = new Button(this);
            newBtn[i].setText(buffer);
            layout.addView(newBtn[i]);

            newBtn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
k=i;
                    openDialog();





                }
            });


    }

    public void viewAll() {
        Cursor res = mydb.getAllprofiles();
        if (res.getCount() == 0) {
            showMessage("Error", "No Data Found");
            return;
        }


        while (res.moveToNext()) {
if((!(res.getString(1).equals("0")))) {
                buffer1[i] = new StringBuffer();
                buffer1[i].append("Id :" + res.getString(0) + "\n");
                buffer1[i].append("Name :" + res.getString(1) + "\n");
                buffer1[i].append("Password :" + res.getString(2) + "\n");
                buffer1[i].append("Temperature :" + res.getString(3) + "\n");
                buffer1[i].append("Humidity :" + res.getString(4) + "\n");
                buffer1[i].append("Season :" + res.getString(5) + "\n");
                i = Integer.parseInt(res.getString(0));
                buffer = (res.getString(1));
                dynabutton(buffer, i);
            }







        }








    }

    public void showMessage(String Title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();


    }


        public void openDialog(){
            ExampleDialog exampleDialog = new ExampleDialog();
            exampleDialog.show(getSupportFragmentManager(),"example dialog");
        }
    }




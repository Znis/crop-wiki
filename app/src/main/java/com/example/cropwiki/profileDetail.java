package com.example.cropwiki;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class profileDetail extends AppCompatActivity {
    public static final String EXTRA_TEXT ="com.example.myapplication5.EXTRA_TEXT";
    StringBuffer buffer1;
    Button delBtn;
    Button updateBtn;
    DatabaseHelper mydb;
    String id;
    StringBuffer buffer;
    int temp;
    int humid;
    String season1;
    String season;
    TextView cropShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        cropShow=(TextView)findViewById(R.id.cropDetailshow);
        delBtn=(Button)findViewById(R.id.delBtn);
        TextView cropMsg=(TextView)findViewById(R.id.Suitablecrop);
        updateBtn=(Button)findViewById(R.id.updateBtn);
        mydb = new DatabaseHelper(this);
        TextView detailView = (TextView) findViewById(R.id.detailView);
        Intent intent = getIntent();
        int i = intent.getIntExtra(existingProfile.EXTRA_NUMBER, 0);

        Cursor res = mydb.getAllprofiles();


        final boolean b = res.move(i);

        buffer1 = new StringBuffer();
        buffer1.append("ID Number    : " + res.getString(0) + "\n");
        buffer1.append("Name            : " + res.getString(1) + "\n");
        buffer1.append("Password     : " + res.getString(2) + "\n");
        buffer1.append("Temperature : " + res.getString(3) + " C"+"\n");
        buffer1.append("Humidity       : " + res.getString(4) + " %"+"\n");
        buffer1.append("Season          : " + res.getString(5) + "\n");
id= res.getString(0);
        detailView.setText(buffer1);
        temp=Integer.parseInt(res.getString(3));
        humid=Integer.parseInt(res.getString(4));
        season=res.getString(5);
        getData();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profileDetail.this, updateProfile.class);
                intent.putExtra(EXTRA_TEXT, id);
                startActivity(intent);
            }

        });

    }

    public void dialogevent(View view){


                        AlertDialog.Builder altDial = new AlertDialog.Builder(profileDetail.this);
                        altDial.setMessage("Confirm?").setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Cursor res = mydb.getAllprofiles();

                                        Boolean deletedRows = mydb.deleteData(id);

                                                if (deletedRows) {
                                                    Toast.makeText(profileDetail.this, "Profile Deleted", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(profileDetail.this, existingProfile.class);

                                                    startActivity(intent);
                                                }else{
                                                    Toast.makeText(profileDetail.this,"Profile Not Deleted",Toast.LENGTH_SHORT).show();
                                                }
                                            }




                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = altDial.create();
                        alert.setTitle("Delete Profile");
                        alert.show();

                    }
    public void getData(){
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        buffer = new StringBuffer();

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

       cropShow.setText(buffer);

        databaseAccess.close();
        buffer.setLength(0);

    }

    }




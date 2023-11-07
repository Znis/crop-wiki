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


public class cropDetail extends AppCompatActivity {
    public static final String EXTRA_TEXT ="com.example.myapplication5.EXTRA_TEXT";


    String id;

    StringBuffer buffer;

    DatabaseOpenHelper mydb;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_detail);
        Button delBtn=(Button)findViewById(R.id.delBtn);
        TextView txtview= (TextView)findViewById(R.id.detailView);
        Button updateBtn=(Button)findViewById(R.id.updateBtn);
        mydb=new DatabaseOpenHelper(this);
        Cursor cursor = mydb.allData();
        Intent intent = getIntent();

        int i = intent.getIntExtra(allData.EXTRA_NUMBER, 0);

        final boolean b = cursor.move(i);

        buffer=new StringBuffer();





        buffer.append("Name    : " + cursor.getString(1) + "\n");
        buffer.append("Temperature Initial : " + cursor.getString(2) + " C"+"\n");
        buffer.append("Temperature Final  : " + cursor.getString(3) + " C"+"\n");
        buffer.append("Humidity Initial       : " + cursor.getString(4) + " %"+"\n");
        buffer.append("Humidity Final        : " + cursor.getString(5) + " %"+"\n");
        buffer.append("Season                     : " + cursor.getString(6) + "\n");
        buffer.append("Description              : " + cursor.getString(7) + "\n\n\n\n");

        id= cursor.getString(0);





        txtview.setText(buffer);


        buffer.setLength(0);

updateBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(cropDetail.this, updateCropProfile.class);
        intent.putExtra(EXTRA_TEXT, id);
        startActivity(intent);

    }
});
delBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {




            AlertDialog.Builder altDial = new AlertDialog.Builder(cropDetail.this);
            altDial.setMessage("Confirm?").setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Cursor res = mydb.allData();
                            boolean deletedRows = mydb.deleteData(id);
                            if (deletedRows) {
                                Toast.makeText(cropDetail.this, "Crop Profile Deleted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(cropDetail.this,allData.class);

                                startActivity(intent);
                            }else{
                                Toast.makeText(cropDetail.this,"Crop Profile Not Deleted",Toast.LENGTH_SHORT).show();
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
            alert.setTitle("Delete Crop Profile");
            alert.show();

        }

});

    }





}

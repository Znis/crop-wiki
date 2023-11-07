/*package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication5.DatabaseAccess;
import com.example.myapplication5.R;

public class allData extends AppCompatActivity {
    StringBuffer buffer;
    TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data);
      txtview=(TextView)findViewById(R.id.allData);
        getData();
    }
    public void getData(){
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        Cursor cursor= databaseAccess.allData();

        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(),"NO DATA",Toast.LENGTH_SHORT).show();

        }
        buffer=new StringBuffer();
        while (cursor.moveToNext()) {





                buffer.append("Name of the crop    : " + cursor.getString(1) + "\n");
                buffer.append("Temperature Initial : " + cursor.getString(2) + " C"+"\n");
                buffer.append("Temperature Final  : " + cursor.getString(3) + " C"+"\n");
                buffer.append("Humidity Initial       : " + cursor.getString(4) + " %"+"\n");
                buffer.append("Humidity Final        : " + cursor.getString(5) + " %"+"\n");
                buffer.append("Season                     : " + cursor.getString(6) + "\n");
                buffer.append("Description              : " + cursor.getString(7) + "\n\n\n\n");

            }





        txtview.setText(buffer);

        databaseAccess.close();
        buffer.setLength(0);

        }





    }
*/
package com.example.cropwiki;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class allData extends AppCompatActivity  {
    public static final String EXTRA_NUMBER ="com.example.myapplication5.EXTRA_NUMBER";
    Button[] newBtn=new Button[100];
    String buffer;




    StringBuffer[] buffer1 =new StringBuffer[100];


    int i=0;
    int k;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_profile);

        viewAll();


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
                Intent intent = new Intent(getApplicationContext(), cropDetail.class);
                intent.putExtra(allData.EXTRA_NUMBER,k);

                startActivity(intent);







            }
        });



    }

    public void viewAll() {
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        Cursor res= databaseAccess.allData();
        if (res.getCount() == 0) {
            showMessage("Error", "No Data Found");
            return;
        }


        while (res.moveToNext()) {
if(!(res.getString(1).equals("0"))) {
    buffer1[i] = new StringBuffer();
    buffer1[i].append("Id :" + res.getString(0) + "\n");

i=Integer.parseInt(res.getString(0));
    buffer = (res.getString(1));
    dynabutton(buffer, i);


}


        }
        databaseAccess.close();








    }

    public void showMessage(String Title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();


    }



}




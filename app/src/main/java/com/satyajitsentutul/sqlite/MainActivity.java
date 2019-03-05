package com.satyajitsentutul.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etxtId,etxtName,etxtCell;
    Button btnSave, btnView,btnUpdate,btnDelete ;

    sqlightDB mydb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtId=findViewById(R.id.etxt_id);
        etxtName=findViewById(R.id.etxt_name);
        etxtCell=findViewById(R.id.etxt_cell);

        btnSave=findViewById(R.id.btn_save);
        btnView=findViewById(R.id.btn_view);
        btnUpdate=findViewById(R.id.btn_update);
        btnDelete=findViewById(R.id.btn_delete);

        mydb=new sqlightDB(MainActivity.this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=etxtId.getText().toString().trim();
                String name=etxtName.getText().toString().trim();
                String cell=etxtCell.getText().toString().trim();

                if(id.isEmpty())
                {
                    etxtId.setError("Plesae Enter Id");
                    etxtId.requestFocus();
                }
                else if(name.isEmpty()){
                    etxtName.setError("Plesae Enter Name");
                    etxtName.requestFocus();
                }
                else if(cell.length()!=11)
                {
                    etxtCell.setError("Plesae Enter Cell");
                    etxtCell.requestFocus();
                }
                else
                {
                    boolean check=mydb.insertData(id,name,cell);
                    if(check==true)
                    {
                        Toast.makeText(MainActivity.this,"Data inserted successfully",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }
    // showData method is used while view button will be pressed all info of table will be visible.
    public void showData(View v){
        Cursor result=mydb.viewData();
        if(result.getCount()==0)
        {
            Toast.makeText(this, "No Data found!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StringBuffer buffer=new StringBuffer();
            result.moveToFirst(); //for pointing first raw
            do{

                buffer.append("Id:"+result.getString(0)+"\n");
                buffer.append("name:"+result.getString(1)+"\n");
                buffer.append("cell:"+result.getString(2)+"\n");
        }
        while (result.moveToNext());
            displayData("Information",buffer.toString());
    }
}
    private void displayData(String information, String s) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle(information);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setCancelable(true);
        dialog.setMessage(s);
        dialog.show();
    }
    public void update(View v){
        String id=etxtId.getText().toString().trim();
        String name=etxtName.getText().toString().trim();
        String cell=etxtCell.getText().toString().trim();
        if(id.isEmpty())
        {
            etxtId.setError("Plesae Enter Id");
            etxtId.requestFocus();
        }
        else if(name.isEmpty()){
            etxtName.setError("Plesae Enter Name");
            etxtName.requestFocus();
        }
        else if(cell.length()!=11)
        {
            etxtCell.setError("Plesae Enter Cell");
            etxtCell.requestFocus();
        }
        else
        {
            boolean check=mydb.updateData(id,name,cell);
            if(check==true)
            {
                Toast.makeText(MainActivity.this,"Data updated successfully",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this,"Data not updated",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void delete(View v){
        String id=etxtId.getText().toString();
        int check=mydb.deleteData(id);
        if(check==1)
        {
            Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Data not deleted", Toast.LENGTH_SHORT).show();
        }
    }
    }



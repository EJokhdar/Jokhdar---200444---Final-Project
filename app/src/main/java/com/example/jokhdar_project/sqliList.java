package com.example.jokhdar_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.github.muddz.styleabletoast.StyleableToast;

public class sqliList extends AppCompatActivity {

    final DatabaseHelper myDB = new DatabaseHelper(this);
    ListView listView;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqli_list);

        listView = findViewById(R.id.sqliListView);

        List<Student> arrayList = new ArrayList<>();
        Cursor cur = myDB.viewStudents();

        if(cur.getCount() == 0){
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cur.moveToNext()){
                String id = cur.getString(0);
                String name = cur.getString(1);
                String surname = cur.getString(2);
                String fname = cur.getString(3);
                String natID = cur.getString(4);
                String dob = cur.getString(5);
                String gender = cur.getString(6);

                student = new Student(id,name, surname, fname, natID, dob, gender);
                arrayList.add(student);
            }
            MyListAdapter listAdapter = new MyListAdapter(sqliList.this, arrayList);
            listView.setAdapter(listAdapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String name = arrayList.get(i).getName();
                String surname = arrayList.get(i).getSurname();

                Toasty.info(sqliList.this, name+" "+surname, Toast.LENGTH_SHORT, true).show();
                //StyleableToast.makeText(sqliList.this, name+" "+surname, R.style.name_toast).show();
            }
        });
    }
}
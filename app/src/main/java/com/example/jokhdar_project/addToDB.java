package com.example.jokhdar_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;
import io.github.muddz.styleabletoast.StyleableToast;

public class addToDB extends AppCompatActivity {

    EditText id, name, fname, surname, dob, natID;
    Spinner gender;
    Button addToDB, addToSQL;

    DatabaseReference studentDBRef;
    final DatabaseHelper myDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_db);

        id = findViewById(R.id.editID);
        name = findViewById(R.id.editName);
        fname = findViewById(R.id.editFName);
        surname = findViewById(R.id.editSurname);
        dob = findViewById(R.id.editDOB);
        natID = findViewById(R.id.editNatID);
        gender = findViewById(R.id.spinner);
        addToDB = findViewById(R.id.addButton);
        addToSQL = findViewById(R.id.addButtonSQLI);

        studentDBRef = FirebaseDatabase.getInstance("https://jokhdar---project-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Student");

        addToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertStudentData();
                Toasty.success(addToDB.this, "Added to Firebase Database", Toast.LENGTH_SHORT, true).show();
                //StyleableToast.makeText(addToDB.this, "Added to Firebase Database", R.style.success_toast).show();
            }
        });

        addToSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idStr = id.getText().toString();
                String nameStr = name.getText().toString();
                String fnameStr = fname.getText().toString();
                String surnameStr = surname.getText().toString();
                String dobStr = dob.getText().toString();
                String natIDStr = natID.getText().toString();
                String genderStr = gender.getSelectedItem().toString();

                insertStudentDataSQL(idStr, nameStr, surnameStr, fnameStr, natIDStr, dobStr, genderStr);
                Toasty.success(addToDB.this, "Added to SQLite Database", Toast.LENGTH_SHORT, true).show();
                //StyleableToast.makeText(addToDB.this, "Added to SQLite Database", R.style.success_toast).show();
            }
        });
    }

    private void insertStudentDataSQL(String stdID, String name, String surname, String fname, String natID, String dateOfBirth,String gender) {
        myDB.addStudent(stdID, name, surname, fname, natID, dateOfBirth, gender);
    }

    private void insertStudentData(){
        String idStr = id.getText().toString();
        String nameStr = name.getText().toString();
        String fnameStr = fname.getText().toString();
        String surnameStr = surname.getText().toString();
        String dobStr = dob.getText().toString();
        String natIDStr = natID.getText().toString();
        String genderStr = gender.getSelectedItem().toString();

        Student student = new Student(idStr, nameStr, surnameStr, fnameStr, natIDStr, dobStr, genderStr);

        studentDBRef.child(idStr).setValue(student);
    }
}
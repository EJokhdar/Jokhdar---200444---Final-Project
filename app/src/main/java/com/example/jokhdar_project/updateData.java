package com.example.jokhdar_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;
import io.github.muddz.styleabletoast.StyleableToast;

public class updateData extends AppCompatActivity {

    final DatabaseHelper myDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        EditText updateStdID = findViewById(R.id.editIDdelete);
        EditText updateName = findViewById(R.id.editNameUpdate);
        EditText updateFName = findViewById(R.id.editFNameUpdate);
        EditText updateSurname = findViewById(R.id.editSurnameUpdate);
        EditText updateNatID = findViewById(R.id.editNatIDUpdate);
        EditText updateDOB = findViewById(R.id.editDOBUpdate);
        Spinner updateGender = findViewById(R.id.spinnerUpdate);
        Button updateRecord = findViewById(R.id.updateStudentButton);
        Button updateSQL = findViewById(R.id.updateButtonSQL);

        updateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newStdID = updateStdID.getText().toString();
                String newName = updateName.getText().toString();
                String newFName = updateFName.getText().toString();
                String newSurname = updateSurname.getText().toString();
                String newNatID = updateNatID.getText().toString();
                String newDOB = updateDOB.getText().toString();
                String newGender = updateGender.getSelectedItem().toString();

                updateData(newStdID, newName, newSurname, newFName, newNatID, newDOB, newGender);
                Toasty.normal(updateData.this, "Updated "+newStdID+"'s data in Firebase Database", R.drawable.updateicon).show();
                //StyleableToast.makeText(updateData.this, "Updated "+newStdID+" in Firebase Database", R.style.update_toast).show();
            }
        });

        updateSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newStdID = updateStdID.getText().toString();
                String newName = updateName.getText().toString();
                String newFName = updateFName.getText().toString();
                String newSurname = updateSurname.getText().toString();
                String newNatID = updateNatID.getText().toString();
                String newDOB = updateDOB.getText().toString();
                String newGender = updateGender.getSelectedItem().toString();

                updateDataSQL(newStdID, newName, newSurname, newFName, newNatID, newDOB, newGender);
                Toasty.normal(updateData.this, "Updated "+newStdID+"'s data in SQLite Database", R.drawable.updateicon).show();
                //StyleableToast.makeText(updateData.this, "Updated "+newStdID+" in SQLite Database", R.style.update_toast).show();
            }
        });
    }

    private void updateDataSQL(String stdID, String name, String surname, String fname, String natID, String dateOfBirth,String gender) {
        myDB.updateData(stdID, name, surname, fname, natID, dateOfBirth, gender);
    }

    private void updateData(String stdID, String name, String surname, String fname, String natID, String dateOfBirth,String gender){

        DatabaseReference dbRef = FirebaseDatabase.getInstance("https://jokhdar---project-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Student").child(stdID);
        Student student = new Student(stdID, name, surname, fname, natID, dateOfBirth, gender);
        dbRef.setValue(student);
    }
}
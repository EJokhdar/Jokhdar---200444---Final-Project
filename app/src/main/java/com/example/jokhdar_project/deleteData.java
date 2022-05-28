package com.example.jokhdar_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;
import io.github.muddz.styleabletoast.StyleableToast;

public class deleteData extends AppCompatActivity {

    Button delete, deleteSQLI;
    EditText deleteStdID;

    final DatabaseHelper myDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);

        deleteStdID = findViewById(R.id.editIDdelete);

        delete = findViewById(R.id.deleteStudent);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String deletedStdID = deleteStdID.getText().toString();
                deleteRecord(deletedStdID);
                Toasty.error(deleteData.this, "Deleted "+deletedStdID+" from Firebase Database", Toast.LENGTH_SHORT, true).show();
                //StyleableToast.makeText(deleteData.this, "Removed "+deletedStdID+" from Firebase Database", R.style.delete_toast).show();
            }
        });

        deleteSQLI = findViewById(R.id.deleteButtonSQLI);
        deleteSQLI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String deletedStdID = deleteStdID.getText().toString();
                deleteRecordSQLI(deletedStdID);
                Toasty.error(deleteData.this, "Deleted "+deletedStdID+" from SQLite Database", Toast.LENGTH_SHORT, true).show();
                //StyleableToast.makeText(deleteData.this, "Removed "+deletedStdID+" from SQLite Database", R.style.delete_toast).show();
            }
        });
    }

    private void deleteRecordSQLI(String deletedStdID) {
        myDB.deleteStudent(deletedStdID);
    }

    private void deleteRecord(String stdID){

        DatabaseReference dbRef = FirebaseDatabase.getInstance("https://jokhdar---project-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Student").child(stdID);
        dbRef.removeValue();

    }
}
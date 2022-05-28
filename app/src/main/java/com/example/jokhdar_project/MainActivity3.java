package com.example.jokhdar_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    ListView myListView;
    List<Student> studentList;

    DatabaseReference studentDBRef;
    final DatabaseHelper myDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        myListView = findViewById(R.id.myListView);
        studentList = new ArrayList<>();

        studentDBRef = FirebaseDatabase.getInstance("https://jokhdar---project-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Student");


        studentDBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();

                for (DataSnapshot studentSnapshot : snapshot.getChildren()) {
                    Student student = studentSnapshot.getValue(Student.class);
                    studentList.add(student);
                }
                MyListAdapter adapter = new MyListAdapter(MainActivity3.this, studentList);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = studentList.get(i);
                showActionDialog(student.getStdID(), student.getName(), student.getSurname(), student.getfName(), student.getNatID(), student.getDOB(), student.getGender());
            }
        });
    }

    private void showActionDialog(String id, String name, String surname, String fname, String natID, String dob, String gender){

        AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.list_action, null);

        mDialog.setView(mDialogView);

        TextView studentID = mDialogView.findViewById(R.id.listID);
        Button addSQLI = mDialogView.findViewById(R.id.addToSQLI);
        addSQLI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.addStudent(id, name, surname, fname, natID, dob, gender);
                startActivity(new Intent(MainActivity3.this, sqliList.class));
            }
        });
        Button update = mDialogView.findViewById(R.id.updateUser);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, updateData.class));
            }
        });
        Button delete = mDialogView.findViewById(R.id.deleteUser);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, deleteData.class));
            }
        });

        studentID.setText("What do you wanna do with with "+id+"?");

        mDialog.setTitle("What do you wanna do?");
        mDialog.show();
    }
}
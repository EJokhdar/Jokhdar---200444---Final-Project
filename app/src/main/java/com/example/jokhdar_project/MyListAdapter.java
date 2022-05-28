package com.example.jokhdar_project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class MyListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<Student> studentList;
    ImageView img;

    public MyListAdapter(Activity mContext, List<Student> studentList){
        super(mContext, R.layout.list_item, studentList);
        this.mContext = mContext;
        this.studentList = studentList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item, null, true);

        TextView id = listItemView.findViewById(R.id.tvID);
        TextView name = listItemView.findViewById(R.id.tvName);
        TextView surname = listItemView.findViewById(R.id.tvSurname);
        TextView fName = listItemView.findViewById(R.id.tvFatherName);
        TextView natID = listItemView.findViewById(R.id.tvNationalID);
        TextView dob = listItemView.findViewById(R.id.tvDateOfBirth);
        TextView gender = listItemView.findViewById(R.id.tvGender);

        Student student = studentList.get(position);

        id.setText(student.getStdID());
        name.setText(student.getName());
        surname.setText(student.getSurname());
        fName.setText(student.getfName());
        natID.setText(student.getNatID());
        dob.setText(student.getDOB());
        gender.setText(student.getGender());

        img = listItemView.findViewById(R.id.studentIcon);

        if(student.getGender().equals("Male")){
            img.setImageResource(R.drawable.male);
        }
        else if(student.getGender().equals("Female")){
            img.setImageResource(R.drawable.female);
        }

        return listItemView;
    }
}

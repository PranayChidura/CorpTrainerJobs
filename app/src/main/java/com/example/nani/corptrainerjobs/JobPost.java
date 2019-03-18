package com.example.nani.corptrainerjobs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JobPost extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);
        String SelectedJob;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Jobs/");
        FirebaseAuth auth=FirebaseAuth.getInstance();
        final FirebaseUser user=auth.getCurrentUser();
        final EditText title=(EditText)findViewById(R.id.title_jobpost);
        final EditText des=(EditText)findViewById(R.id.des_jobpost);

        final Spinner spinner = findViewById(R.id.spinner_jobpost);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.jobs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button btn=(Button)findViewById(R.id.btn_jobpost);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().equals("") || des.getText().toString().equals("")) {
                    Toast.makeText(JobPost.this,"Please Fill all the details",Toast.LENGTH_SHORT).show();
                }else{

                    String text = spinner.getSelectedItem().toString();
                    myRef.child(text +"/"+title.getText().toString()+ "/id").setValue(user.getDisplayName().toString());
                    myRef.child(text +"/"+title.getText().toString()+ "/title").setValue(title.getText().toString());
                    myRef.child(text +"/"+title.getText().toString()+ "/des").setValue(des.getText().toString());
                    startActivity(new Intent(JobPost.this,Success.class));
                    title.setText("");
                    des.setText("");
                }
            }
        });

    }


    }


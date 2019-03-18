package com.example.nani.corptrainerjobs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Apply extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        final EditText id=(EditText)findViewById(R.id.apply_id);
        final EditText subject=(EditText)findViewById(R.id.apply_subject);
        final EditText des=(EditText)findViewById(R.id.apply_des);
        Button sub=(Button)findViewById(R.id.apply_submit);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Profiles/");
                if(id.getText().toString().equals("") || subject.getText().toString().equals("") || des.getText().toString().equals("")){
                    Toast.makeText(Apply.this,"Fill",Toast.LENGTH_SHORT).show();
                }else{
                    myRef.child(id.getText().toString()+"msgs").setValue(subject.getText().toString()+": "+des.getText().toString());
                    Toast.makeText(Apply.this,"Submitted",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Apply.this,Success.class));
                    id.setText("");
                    subject.setText("");
                    des.setText("");
                }

            }
        });
    }
}

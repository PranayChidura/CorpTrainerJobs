package com.example.nani.corptrainerjobs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dpname extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpname);
        getSupportActionBar().hide();
        final EditText name=(EditText)findViewById(R.id.dp_name);
        final EditText des=(EditText)findViewById(R.id.dp_des);
        Button go=(Button)findViewById(R.id.get_started);

        FirebaseAuth auth=FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser=auth.getCurrentUser();
        final DatabaseReference res= FirebaseDatabase.getInstance().getReference("Profiles/");
        /*if(firebaseUser.getDisplayName().toString()!=""){
            startActivity(new Intent(Dpname.this,Home.class));
        finish();
        }*/
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(name.getText().toString().equals("") || des.getText().toString().equals("")){
    Toast.makeText(Dpname.this,"Please Fill Details",Toast.LENGTH_SHORT).show();
}else {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(name.getText().toString()).build();

                firebaseUser.updateProfile(profileUpdates);
                res.child(name.getText().toString()).setValue(des.getText().toString());
                res.child(name.getText().toString()+"msgs").setValue("Empty");
                startActivity(new Intent(Dpname.this,Home.class));
                finish();}
            }
        });

    }
}

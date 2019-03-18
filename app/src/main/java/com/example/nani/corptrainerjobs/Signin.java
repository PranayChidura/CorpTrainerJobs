package com.example.nani.corptrainerjobs;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Signin extends AppCompatActivity {
    EditText email,pass,dp;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_signin);
        email=(EditText)findViewById(R.id.log_e);
        pass=(EditText)findViewById(R.id.log_p);
        Button reg=(Button)findViewById(R.id.login);
        getSupportActionBar().hide();

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading... ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);




        Button getreg=(Button) findViewById(R.id.getreg);
        getreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signin.this,Register.class));
            }
        });
        final FirebaseUser user=mAuth.getCurrentUser();

        if(user!=null)
        {
            startActivity(new Intent(Signin.this,Home.class));
            finish();
        }

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if(email.getText().toString().equals("") || pass.getText().toString().equals("")){
    Toast.makeText(Signin.this,"Please Fill Details!!!",Toast.LENGTH_SHORT).show();
}
else {
    /////////////////////////////
    progress.show();
    mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

            if (task.isSuccessful()) {
                Toast.makeText(Signin.this, "SignIn Successful", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Signin.this, Dpname.class));
                progress.dismiss();
                finish();

            } else {
                Toast.makeText(Signin.this, "SignIn Failed", Toast.LENGTH_LONG).show();
                progress.dismiss();
            }

        }
    });
    /////////////////////////////


}
            }
        });
    }
}


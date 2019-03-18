package com.example.nani.corptrainerjobs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final EditText des=(EditText)findViewById(R.id.profile_des);
        final Button save=(Button)findViewById(R.id.profile_save);
        final EditText id=(EditText) findViewById(R.id.profile_id);
        final TextView inbox=(TextView)findViewById(R.id.profile_inbox);

       FirebaseAuth auth=FirebaseAuth.getInstance();
        final FirebaseUser user=auth.getCurrentUser();



        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Profiles/"+user.getDisplayName().toString());
        id.setText(user.getDisplayName().toString());
        DatabaseReference refa= FirebaseDatabase.getInstance().getReference().child("Profiles/"+user.getDisplayName().toString()+"msgs");
        refa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                inbox.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

ref.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        des.setText(dataSnapshot.getValue().toString());
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});


       save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(id.getText().toString()).build();

                user.updateProfile(profileUpdates);


                DatabaseReference refer= FirebaseDatabase.getInstance().getReference().child("Profiles/"+user.getDisplayName().toString());
                refer.setValue(des.getText().toString());
            }
        });
    }
}

package com.example.nani.corptrainerjobs;

import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        Button menu=(Button)findViewById(R.id.menu_btn);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Menu.class));
                finish();
            }
        });
        final EditText search=(EditText)findViewById(R.id.search);
        Button go=(Button)findViewById(R.id.searchgo);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=search.getText().toString();
                if(a.equals("Java") ||a.equals("Sql") ||a.equals("Python") || a.equals("Ruby")){
                Intent intent = new Intent(getBaseContext(), JobListing.class);
                intent.putExtra("key", "Jobs/"+a.toString());
                startActivity(intent);}
                else {
                    Toast.makeText(Home.this,"Keyword Incorrect \n (Keywords are Case Sensitive)",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

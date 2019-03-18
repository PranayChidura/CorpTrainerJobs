package com.example.nani.corptrainerjobs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JobListing extends AppCompatActivity {
RecyclerView recyclerView;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listing);
        getSupportActionBar().hide();
        String key= getIntent().getStringExtra("key");
        reference= FirebaseDatabase.getInstance().getReference().child(key);
        reference.keepSynced(true);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog,BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, BlogViewHolder>
                (Blog.class,R.layout.list_row,BlogViewHolder.class,reference) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
            viewHolder.setTitle(model.getTitle());
            viewHolder.setDes(model.getDes());
            viewHolder.setId(model.getId());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public BlogViewHolder(View itemView){
            super(itemView);
            mView=itemView;
        }
        public void setTitle(String title)
        {
            TextView post_title=(TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDes(String des)
        {
            TextView post_des=(TextView)mView.findViewById(R.id.post_des);
            post_des.setText(des);
        }
        public void setId(String id)
        {
            TextView post_id=(TextView)mView.findViewById(R.id.post_id);
            post_id.setText(id);
        }
    }
}

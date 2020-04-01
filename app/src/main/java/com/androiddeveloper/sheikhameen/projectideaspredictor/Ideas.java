package com.androiddeveloper.sheikhameen.projectideaspredictor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class Ideas extends AppCompatActivity {
    Categories_Adapter myAdapter;
    RecyclerView recyclerView;
    //RecyclerView.LayoutManager layoutManager;
    DBaccess dataBaseAccess;
    ArrayList<String> ideas=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);
        recyclerView=findViewById(R.id.RvIdeas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Intent intent=getIntent();
        String category=intent.getStringExtra("category");
        dataBaseAccess= DBaccess.getInstance(getApplicationContext());
        dataBaseAccess.open();
            ideas=dataBaseAccess.getIdeas(category);
            myAdapter =new Categories_Adapter(ideas,getApplicationContext());
            recyclerView.setAdapter(myAdapter);
            myAdapter.setOnItemClick(new Categories_Adapter.onItemClickListener() {
                @Override
                public void onItemClick(int position) {

                    String idea=ideas.get(position);
                    //Toast.makeText(Ideas.this, idea, Toast.LENGTH_SHORT).show();
                    String instruction=dataBaseAccess.getInstruction(idea);
                    Intent intent1=new Intent(getApplicationContext(),Instructions.class);
                    intent1.putExtra("instruction",instruction);
                    startActivity(intent1);
                    dataBaseAccess.close();
                }
            });





    }
}

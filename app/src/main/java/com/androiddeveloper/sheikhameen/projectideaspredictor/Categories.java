package com.androiddeveloper.sheikhameen.projectideaspredictor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class Categories extends AppCompatActivity  {
    Categories_Adapter myAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DBaccess dataBaseAccess;
    ArrayList<String> categories=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        recyclerView=findViewById(R.id.RvCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dataBaseAccess= DBaccess.getInstance(getApplicationContext());
        dataBaseAccess.open();
        categories=dataBaseAccess.getCategories();

        dataBaseAccess.delete("web");
        dataBaseAccess.close();
        myAdapter =new Categories_Adapter(categories,getApplicationContext());
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClick(new Categories_Adapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String category=categories.get(position);
                Intent intent=new Intent(getApplicationContext(),Ideas.class);
                if(category!=null)
                {
                    intent.putExtra("category",category);
                    //Toast.makeText(Categories.this, category, Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Categories.this, "Null category", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


    }


}

package com.androiddeveloper.sheikhameen.projectideaspredictor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Instructions extends AppCompatActivity {
    ImageView feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        TextView instruction;
        feedback=findViewById(R.id.IvFeed);

        instruction=findViewById(R.id.TvInst);
        final Intent intent=getIntent();
        instruction.setText(intent.getStringExtra("instruction"));
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),feedback.class);
                startActivity(intent1);
            }
        });


    }
}

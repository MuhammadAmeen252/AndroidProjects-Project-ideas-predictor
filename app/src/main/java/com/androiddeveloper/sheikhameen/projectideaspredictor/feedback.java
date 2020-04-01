package com.androiddeveloper.sheikhameen.projectideaspredictor;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class feedback extends AppCompatActivity {
    RatingBar ratingBar;
    Button submit;
    EditText review,question;
    String r,q,rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ratingBar=findViewById(R.id.ratingBar);
        review=findViewById(R.id.tvsuggestions);
        question=findViewById(R.id.tvAskQuestion);
        submit=findViewById(R.id.btfeedback);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        ratingBar.setNumStars(5);
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        r=review.getText().toString();
        q=question.getText().toString();
        rate=String.valueOf(ratingBar.getRating());
        //int r=Integer.valueOf(rate);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r=review.getText().toString();
                q=question.getText().toString();
                rate=String.valueOf(ratingBar.getRating());


                if (r.equals("") || q.equals("") || rate.equals("")) {

                    Toast.makeText(feedback.this, "Please Enter all fields!"+r+q+rate, Toast.LENGTH_SHORT).show();


                }
                else
                {
                    Toast.makeText(feedback.this, "Submitted successfully!", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(feedback.this, r+q, Toast.LENGTH_SHORT).show();
                    review.setText("");
                    question.setText("");
                    ratingBar.setRating(0F);
                }

            }
        });




    }
}

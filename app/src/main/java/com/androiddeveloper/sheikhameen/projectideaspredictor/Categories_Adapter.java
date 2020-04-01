package com.androiddeveloper.sheikhameen.projectideaspredictor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.ViewHolder> {

    private ArrayList<String> category=new ArrayList<>();
    //private ArrayList<String> Image=new ArrayList<>();
    private Context context;
    public onItemClickListener Mylistener;

    public Categories_Adapter(ArrayList<String> category,Context context) {

        this.category=category;
        this.context=context;
    }

    public interface onItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClick(onItemClickListener listener)
    {
        Mylistener=listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_layout,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view,this.Mylistener);
        return viewHolder;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView CategoryText;
        CardView Layout;
        ImageView image;

        public ViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            CategoryText =itemView.findViewById(R.id.TvCategory);
            Layout=itemView.findViewById(R.id.CvLayout);
            image=itemView.findViewById(R.id.IvCircle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.CategoryText.setText(category.get(i));
    }

    @Override
    public int getItemCount() {
        return category.size();
    }
}

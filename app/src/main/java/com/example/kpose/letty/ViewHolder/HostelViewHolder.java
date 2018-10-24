package com.example.kpose.letty.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kpose.letty.Interface.ItemClickListener;
import com.example.kpose.letty.R;

public class HostelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView hostel_name;
    public ImageView hostel_imaage;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public HostelViewHolder(View itemView) {
        super(itemView);

        hostel_name= itemView.findViewById(R.id.hostel_name);
        hostel_imaage = itemView.findViewById(R.id.hostel_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}

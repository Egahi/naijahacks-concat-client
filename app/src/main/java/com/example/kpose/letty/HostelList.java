package com.example.kpose.letty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.kpose.letty.Interface.ItemClickListener;
import com.example.kpose.letty.Model.Category;
import com.example.kpose.letty.Model.Hotel;
import com.example.kpose.letty.ViewHolder.HostelViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HostelList extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

        FirebaseDatabase database;
        DatabaseReference hostelList;

        String categoryId = "";

        FirebaseRecyclerAdapter<Hotel, HostelViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        hostelList = database.getReference("Foods");

        recyclerView = findViewById(R.id.recycler_hostel);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get intent here

        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() && categoryId != null)
        {
            loadHostelList(categoryId);

        }
    }

    private void loadHostelList(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Hotel, HostelViewHolder>(Hotel.class,
                R.layout.hostel_item,
                HostelViewHolder.class,
                hostelList.orderByChild("MenuId").equalTo(categoryId)
                ) {
            @Override
            protected void populateViewHolder(HostelViewHolder viewHolder, Hotel model, int position) {
                viewHolder.hostel_name.setText(model.getName());
                Picasso.get().load(model.getImage())
                        .into(viewHolder.hostel_imaage);

                final Hotel local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start New Activity
                        Intent hostelDetail = new Intent(HostelList.this, HostelDetails.class);
                        hostelDetail.putExtra("FoodId",adapter.getRef(position).getKey());
                        startActivity(hostelDetail);
                    }
                });



            }
        };

        //set Adapter
        recyclerView.setAdapter(adapter
        );
    }
}

package com.example.kpose.letty;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kpose.letty.Model.Hotel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HostelDetails extends AppCompatActivity {

    TextView hostel_name, hostel_price,hostel_description;
    ImageView hostel_image;
    CollapsingToolbarLayout collapsingToolbarLayout;


    String hostelId="";

    FirebaseDatabase database;
    DatabaseReference hostels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_details);

        //Firebase
        database = FirebaseDatabase.getInstance();
        hostels = database.getReference("Foods");

        //Initialize view
        hostel_description = findViewById(R.id.hostel_description);
        hostel_price = findViewById(R.id.hostel_price);
        hostel_name = findViewById(R.id.hostel_name);
        hostel_image = findViewById(R.id.img_hostel);

        collapsingToolbarLayout = findViewById(R.id.collasping);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollaspingAppBar);

        //get hostel id from client

        if (getIntent() !=null)
            hostelId = getIntent().getStringExtra("FoodId");
        if (!hostelId.isEmpty())
        {
            getDetailHostel(hostelId);
        }

    }

    private void getDetailHostel(String hostelId) {
        hostels.child(hostelId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Hotel hotel = dataSnapshot.getValue(Hotel.class);

                //set image
                Picasso.get().load(hotel.getImage())
                        .into(hostel_image);

                collapsingToolbarLayout.setTitle(hotel.getName());

                hostel_price.setText(hotel.getPrice());

                hostel_name.setText(hotel.getName());

                hostel_description.setText(hotel.getDescription());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

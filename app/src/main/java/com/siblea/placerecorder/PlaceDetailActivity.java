package com.siblea.placerecorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.siblea.placerecorder.model.Place;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceDetailActivity extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";

    @BindView(R.id.place_name)
    TextView placeName;

    @BindView(R.id.place_description)
    TextView placeDescription;

    @BindView(R.id.latitude)
    TextView placeLat;

    @BindView(R.id.longitude)
    TextView placeLng;

    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        setUpPlace(intent);

        displayData();
    }

    private void setUpPlace(Intent intent) {
        place = new Place();
        place.setName(intent.getStringExtra(NAME));
        place.setDescription(intent.getStringExtra(DESCRIPTION));
        place.setLat(intent.getDoubleExtra(LATITUDE, 0));
        place.setLng(intent.getDoubleExtra(LONGITUDE, 0));
    }

    private void displayData() {
        placeName.setText(place.getName());
        placeDescription.setText(place.getDescription());
        placeLat.setText(String.valueOf(place.getLat()));
        placeLng.setText(String.valueOf(place.getLng()));
    }
}
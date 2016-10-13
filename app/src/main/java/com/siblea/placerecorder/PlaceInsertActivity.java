package com.siblea.placerecorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlaceInsertActivity extends AppCompatActivity {

    @BindView(R.id.place_name_input)
    EditText nameInput;

    @BindView(R.id.place_description_input)
    EditText descriptionInput;

    @BindView(R.id.latitude)
    TextView latitude;

    @BindView(R.id.longitude)
    TextView longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_insert);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_place_button)
    void onAddPlaceClicked() {

    }
}

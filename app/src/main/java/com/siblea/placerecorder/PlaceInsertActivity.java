package com.siblea.placerecorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.siblea.placerecorder.model.Place;
import com.siblea.placerecorder.presenter.PlaceInsertPresenter;
import com.siblea.placerecorder.task.PlaceInsertTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlaceInsertActivity extends AppCompatActivity implements PlaceInsertTask.View {

    @BindView(R.id.place_name_input)
    EditText nameInput;

    @BindView(R.id.place_description_input)
    EditText descriptionInput;

    @BindView(R.id.latitude)
    TextView latitude;

    @BindView(R.id.longitude)
    TextView longitude;

    PlaceInsertTask.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_insert);
        ButterKnife.bind(this);

        presenter = new PlaceInsertPresenter(this, this);
    }

    @OnClick(R.id.add_place_button)
    void onAddPlaceClicked() {
        if (formIsWellFilled()) {
            Place place = new Place();
            place.setName(nameInput.getText().toString().trim());
            place.setDescription(descriptionInput.getText().toString().trim());
            place.setLat((long) Double.parseDouble(this.latitude.getText().toString().trim()));
            place.setLng((long) Double.parseDouble(this.longitude.getText().toString().trim()));
            presenter.add(place);
        } else {
            displayFormErrors();
        }
    }

    private boolean formIsWellFilled() {
        return !nameInput.getText().toString().isEmpty() && !descriptionInput.getText().toString().isEmpty();
    }

    private void displayFormErrors() {
        if (nameInput.getText().toString().isEmpty()) {
            nameInput.setError("Field cannot be empty");
        }

        if (descriptionInput.getText().toString().isEmpty()) {
            descriptionInput.setError("Field cannot be empty");
        }
    }

    @Override
    public void onPlaceAdded(Place place) {
        Toast.makeText(this, place.getName() + " inserted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorAddingPlace(Place place) {
        Toast.makeText(this, "Error inserting " + place.getName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void cleanFields() {
        nameInput.setText("");
        descriptionInput.setText("");
    }
}

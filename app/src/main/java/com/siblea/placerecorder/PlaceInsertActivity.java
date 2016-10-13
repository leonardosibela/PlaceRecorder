package com.siblea.placerecorder;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.siblea.placerecorder.model.Place;
import com.siblea.placerecorder.presenter.PlaceInsertPresenter;
import com.siblea.placerecorder.task.PlaceInsertTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlaceInsertActivity extends AppCompatActivity implements PlaceInsertTask.View,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.place_name_input)
    EditText nameInput;

    @BindView(R.id.place_description_input)
    EditText descriptionInput;

    @BindView(R.id.latitude)
    TextView latitude;

    @BindView(R.id.longitude)
    TextView longitude;

    PlaceInsertTask.Presenter presenter;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_insert);
        ButterKnife.bind(this);

        presenter = new PlaceInsertPresenter(this, this);

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @OnClick(R.id.add_place_button)
    void onAddPlaceClicked() {
        if (formIsWellFilled()) {
            Place place = new Place();
            place.setName(nameInput.getText().toString().trim());
            place.setDescription(descriptionInput.getText().toString().trim());
            place.setLat(lastLocation.getLatitude());
            place.setLng(lastLocation.getLongitude());
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null) {
            latitude.setText(String.valueOf(lastLocation.getLatitude()));
            longitude.setText(String.valueOf(lastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection Suspended", Toast.LENGTH_LONG).show();
        // Called when the client is temporarily in a disconnected state.
        // This can happen if there is a problem with the remote service (e.g. a crash or resource problem causes it to be killed by the system).
        // When called, all requests have been canceled and no outstanding listeners will be executed.
        // GoogleApiClient will automatically attempt to restore the connection.
        // Applications should disable UI components that require the service, and wait for a call to onConnected(Bundle) to re-enable them.
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection Failed", Toast.LENGTH_LONG).show();
        // It is called if there was an error connecting to the device (e.g. if Google Play services needs to be updated).
        // There is nothing that you need to do here, but again you should log a message as an aid during debugging.
    }
}

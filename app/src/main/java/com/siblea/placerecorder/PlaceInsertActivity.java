package com.siblea.placerecorder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @BindView(R.id.place_name_input)
    EditText nameInput;

    @BindView(R.id.place_description_input)
    EditText descriptionInput;

    @BindView(R.id.lat_label)
    TextView latLabel;

    @BindView(R.id.latitude)
    TextView latitude;

    @BindView(R.id.lng_label)
    TextView lngLabel;

    @BindView(R.id.longitude)
    TextView longitude;

    @BindView(R.id.add_place_button)
    Button addPermissionButton;

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

    private boolean mustAskForLocationPermission() {
        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED;
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
        if (mustAskForLocationPermission()) {
            askForLocationPermission();
        } else {
            updateLastLocation();
        }
    }

    private void askForLocationPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_ASK_PERMISSIONS);
    }

    private void updateLastLocation() {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null) {
            latitude.setText(String.valueOf(lastLocation.getLatitude()));
            longitude.setText(String.valueOf(lastLocation.getLongitude()));

            latLabel.setVisibility(View.VISIBLE);
            latitude.setVisibility(View.VISIBLE);
            lngLabel.setVisibility(View.VISIBLE);
            longitude.setVisibility(View.VISIBLE);
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


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateLastLocation();
                } else {
                    displayLocationPermissionDeniedMessage();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void displayLocationPermissionDeniedMessage() {
        Snackbar.make(addPermissionButton, "Location permission denied",
                Snackbar.LENGTH_INDEFINITE).setAction("REASK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForLocationPermission();
            }
        }).show();
    }
}

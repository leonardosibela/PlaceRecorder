package com.siblea.placerecorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.siblea.placerecorder.adapter.PlaceListAdapter;
import com.siblea.placerecorder.model.Place;
import com.siblea.placerecorder.presenter.PlaceListPresenter;
import com.siblea.placerecorder.task.PlaceListTask;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity implements PlaceListTask.View {

    @BindView(R.id.places_recycler)
    RecyclerView recyclerView;

    private PlaceListPresenter presenter;
    private List<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        presenter = new PlaceListPresenter(this, this);
        presenter.getAllPalces();
    }

    @Override
    public void setPlaces(List<Place> places) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new PlaceListAdapter(places));
    }
}

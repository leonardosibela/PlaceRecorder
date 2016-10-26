package com.siblea.placerecorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.siblea.placerecorder.adapter.PlaceListAdapter;
import com.siblea.placerecorder.model.Place;
import com.siblea.placerecorder.presenter.PlaceListPresenter;
import com.siblea.placerecorder.task.PlaceListTask;
import com.siblea.placerecorder.view.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity implements PlaceListTask.View {

    @BindView(R.id.places_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.empty_list_message)
    TextView emptyListMessage;

    private PlaceListPresenter presenter;

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
        hideProgressBar();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new PlaceListAdapter(places));
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayEmptyListMesssage() {
        hideProgressBar();
        emptyListMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar() {
        emptyListMessage.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}

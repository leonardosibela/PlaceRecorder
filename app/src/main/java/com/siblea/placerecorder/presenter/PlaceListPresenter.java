package com.siblea.placerecorder.presenter;

import android.content.Context;

import com.siblea.placerecorder.dao.PlaceDao;
import com.siblea.placerecorder.model.Place;
import com.siblea.placerecorder.task.PlaceListTask;

import java.util.List;

public class PlaceListPresenter implements PlaceListTask.Presenter {

    private PlaceListTask.View view;
    private PlaceDao placeDao;

    public PlaceListPresenter(PlaceListTask.View view, Context context) {
        this.view = view;
        placeDao = new PlaceDao(context);
    }

    @Override
    public void getAllPalces() {
        placeDao.open();
        List<Place> places = placeDao.selectAll();
        placeDao.close();
        view.setPlaces(places);
    }
}

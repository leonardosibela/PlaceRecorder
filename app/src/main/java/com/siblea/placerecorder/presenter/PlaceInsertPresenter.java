package com.siblea.placerecorder.presenter;

import android.content.Context;

import com.siblea.placerecorder.dao.PlaceDao;
import com.siblea.placerecorder.model.Place;
import com.siblea.placerecorder.task.PlaceInsertTask;

public class PlaceInsertPresenter implements PlaceInsertTask.Presenter {

    private PlaceInsertTask.View view;
    private PlaceDao placeDao;

    public PlaceInsertPresenter(PlaceInsertTask.View view, Context context) {
        this.view = view;
        placeDao = new PlaceDao(context);
    }

    @Override
    public void add(Place place) {
        placeDao.open();
        boolean inserted = placeDao.insert(place);
        placeDao.close();
        if (inserted) {
            view.onSuccessAddingPlace(place);
            view.cleanFields();
        } else {
            view.onErrorAddingPlace(place);
        }
    }
}

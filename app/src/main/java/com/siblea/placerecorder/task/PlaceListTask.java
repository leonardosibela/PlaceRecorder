package com.siblea.placerecorder.task;

import com.siblea.placerecorder.model.Place;

import java.util.List;

public interface PlaceListTask {

    interface View {
        void setPlaces(List<Place> places);
    }

    interface Presenter {
        void getAllPalces();
    }
}

package com.siblea.placerecorder.task;

import com.siblea.placerecorder.model.Place;

import java.util.List;

public interface PlaceInsertTask {

    interface View {
        void onPlaceAdded(Place place);

        void errorAddingPlace(Place place);
    }

    interface Presenter {
        void add(Place place);
    }
}

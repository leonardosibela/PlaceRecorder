package com.siblea.placerecorder.task;

import com.siblea.placerecorder.model.Place;

import java.util.List;

public interface PlaceInsertTask {

    interface View {
        void onSuccessAddingPlace(Place place);

        void onErrorAddingPlace(Place place);

        void cleanFields();
    }

    interface Presenter {
        void add(Place place);
    }
}

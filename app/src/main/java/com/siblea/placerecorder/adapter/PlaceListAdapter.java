package com.siblea.placerecorder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.siblea.placerecorder.R;
import com.siblea.placerecorder.model.Place;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.ViewHolder> {

    private List<Place> places;

    public PlaceListAdapter(List<Place> places) {
        this.places = places;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.place_item, null);
        return new PlaceListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        Place place = places.get(i);
        holder.placeName.setText(place.getName());
        holder.placeDescription.setText(place.getDescription());
    }

    @Override
    public int getItemCount() {
        return (places != null ? places.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.place_name)
        TextView placeName;

        @BindView(R.id.place_description)
        TextView placeDescription;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }
}
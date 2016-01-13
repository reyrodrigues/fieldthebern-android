package com.berniesanders.fieldthebern.screens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.berniesanders.fieldthebern.R;
import com.berniesanders.fieldthebern.models.Rankings;
import com.berniesanders.fieldthebern.models.UserAttributes;
import com.berniesanders.fieldthebern.models.UserData;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 */
public class RankingAdapter extends ArrayAdapter<String> {
    private final Context context;
    private List<UserData> userDatas;
    private List<Rankings.Data> datas;

    public RankingAdapter(Context context, List<UserData> userDatas, List<Rankings.Data> datas) {
        super(context, R.layout.screen_profile_row, new String[userDatas.size()]);
        this.context = context;
        this.userDatas = userDatas;
        this.datas = datas;
    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        View newView = LayoutInflater.from(context).inflate(R.layout.screen_profile_row, null, true);

        UserAttributes attributes = this.userDatas.get(position).attributes();
        Rankings.Attributes attributes1 = this.datas.get(position).attributes();

        // position
        TextView positionView = (TextView) newView.findViewById(R.id.position);
        positionView.setText(attributes1.rank());

        // name
        String name = attributes.getFirstName() + " " + attributes.getLastName();
        TextView nameView = (TextView) newView.findViewById(R.id.name);
        nameView.setText(name);

        //score
        TextView scoreView = (TextView) newView.findViewById(R.id.score);
        String score = String.valueOf(attributes1.score());
        scoreView.setText(score);

        //pic
        ImageView avatar = (ImageView) newView.findViewById(R.id.profile_image);
        Picasso.with(newView.getContext())
                .load(attributes.getPhotoThumbUrl())
                .into(avatar);

        return newView;
    }
}
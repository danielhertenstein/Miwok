package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by daniel on 9/30/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check is existing View is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }

        // Get the Word at this location
        Word currentWord = getItem(position);

        // Find the ImageView in the list_item.xml for the word image
        ImageView imageView = listItemView.findViewById(R.id.word_image);
        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        // Find the TextView in the list_item.xml for the miwok translation
        TextView miwokTextView = listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());
        miwokTextView.setBackgroundColor(color);

        // Find the TextView in the list_item.xml for the default translation
        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());
        defaultTextView.setBackgroundColor(color);

        // Find the ImageView in the list_item.xml for the play icon
        ImageView icon = listItemView.findViewById(R.id.play_icon);
        icon.setBackgroundColor(color);

        return listItemView;
    }
}

package com.example.guessmusic.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class Util {

	public static View getView(Context context, int layoutID) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(layoutID, null);

		return view;
	}
}

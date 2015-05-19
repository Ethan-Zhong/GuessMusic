package com.example.guessmusic.myui;

import java.security.Principal;
import java.util.ArrayList;

import com.example.guessmusic.modal.WordButton;
import com.example.guessmusic.utils.Util;
import com.example.guessmusic.*;
import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/*
 * 自定的GridView
 * 
 */
public class MyGridView extends GridView{

	private Context mContext;
	private ArrayList<WordButton> mWordButtonsList;
	private MyGridViewAdapter myGridViewAdapter;

	
	class MyGridViewAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mWordButtonsList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			WordButton holder;
			if(convertView == null){
		
				holder = mWordButtonsList.get(position);
				convertView = Util.getView(mContext,com.example.guessmusic.R.layout.self_grid_ui_btn);
				holder.mIndex = position;
				holder.mButton =(Button) convertView.findViewById(com.example.guessmusic.R.id.wordButton);
		
				convertView.setTag(holder);
			}else {
				holder = (WordButton)convertView.getTag();			}
			holder.mButton.setText(holder.mWord);
			
			return convertView;
		}
		
	}
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mContext = context;
		mWordButtonsList = new ArrayList<WordButton>();
		myGridViewAdapter = new MyGridViewAdapter();
		
		this.setAdapter(myGridViewAdapter);
	}

	
}

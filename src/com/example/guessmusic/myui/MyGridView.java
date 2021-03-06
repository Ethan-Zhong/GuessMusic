package com.example.guessmusic.myui;

import java.security.Principal;
import java.util.ArrayList;

import com.example.guessmusic.modal.IWordButtonClickListener;
import com.example.guessmusic.modal.WordButton;
import com.example.guessmusic.utils.Util;
import com.example.guessmusic.*;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/*
 * 自定的GridView
 * 
 */
public class MyGridView extends GridView {

	private Context mContext;
	private ArrayList<WordButton> mWordButtonsList;
	private MyGridViewAdapter myGridViewAdapter;

	private Animation mAnimation;
	private IWordButtonClickListener listener;

	class MyGridViewAdapter extends BaseAdapter {

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

			final WordButton holder;
			if (convertView == null) {

				mAnimation = AnimationUtils.loadAnimation(mContext,
						com.example.guessmusic.R.anim.scale);

				holder = mWordButtonsList.get(position);
				convertView = Util.getView(mContext,
						com.example.guessmusic.R.layout.self_grid_ui_btn);
				holder.mIndex = position;
				holder.mButton = (Button) convertView
						.findViewById(com.example.guessmusic.R.id.wordButton);
				holder.mButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						listener.onWordButtonClick(holder);

					}
				});

				convertView.setTag(holder);
			} else {
				holder = (WordButton) convertView.getTag();
			}
			mAnimation.setStartOffset(position * 100);
			holder.mButton.setText(holder.mWord);
			convertView.startAnimation(mAnimation);

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

	public void updateData(ArrayList<WordButton> data) {

		// 更新数据 并刷新GridView
		mWordButtonsList = data;
		setAdapter(myGridViewAdapter);
	}

	/*
	 * 注册监听器
	 */
	public void registerIWordButtonClickListen(IWordButtonClickListener listener) {
		this.listener = listener;
	}
}

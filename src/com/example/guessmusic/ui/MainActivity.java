package com.example.guessmusic.ui;

import java.io.UTFDataFormatException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.guessmusic.R;
import com.example.guessmusic.modal.IWordButtonClickListener;
import com.example.guessmusic.modal.WordButton;
import com.example.guessmusic.myui.MyGridView;
import com.example.guessmusic.utils.Util;

public class MainActivity extends Activity implements OnClickListener,IWordButtonClickListener{

	private static final int WORDBUTTON_CONUT = 24;
	// 定义动画
	private Animation mPanAnim;
	private LinearInterpolator mPanLin;

	private Animation mPoleInAnim;
	private LinearInterpolator mPoleInLin;

	private Animation mPoleOutAnim;
	private LinearInterpolator mPoleOutLin;

	private ImageView mPan;
	private ImageView mPole;
	private ImageButton mStartPaly;

	// 当前动画是否正在运行
	private boolean mIsRunning = false;

	private MyGridView mGridView;

	// GridView中的数据源
	private ArrayList<WordButton> mWordButtonList = new ArrayList<WordButton>();

	//
	private ArrayList<WordButton> mSelectList = new ArrayList<WordButton>();
	// 显示已选按钮的对话框
	private LinearLayout mSelect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化动画
		initAnimation();

		mPan = (ImageView) findViewById(R.id.imageView1);
		mPole = (ImageView) findViewById(R.id.imageView2);
		mStartPaly = (ImageButton) findViewById(R.id.btn_play_start);

		mStartPaly.setOnClickListener(this);

		mGridView = (MyGridView) findViewById(R.id.myGridView);
		initGridView();
		mGridView.registerIWordButtonClickListen(this);

		mSelect = (LinearLayout) findViewById(R.id.selectNames);
		initSelcect();
	}

	public void initGridView() {

		// 设置待选按钮的数据源 并更新GridView
		mWordButtonList = initWordButtonList();
		mGridView.updateData(mWordButtonList);

	}

	public void initSelcect() {
		// 初始化已选择框
		mSelectList = initSelectButton();
		LayoutParams layoutParams = new LayoutParams(140, 140);

		
		// for(int i = 0; i < mSelectList.size(); i++){
		// //select 布局添加控件
		// // mSelect.addView(mSelectList.get(i).mButton,layoutParams);
		//
		// String name =
		// mSelectList.get(i).mButton.getParent().getClass().getName();
		// Log.i("ParentName",name);
		// }
	}

	// 初始化所有的wordButton
	public ArrayList<WordButton> initWordButtonList() {

		ArrayList<WordButton> data = new ArrayList<WordButton>();

		for (int i = 0; i < WORDBUTTON_CONUT; i++) {
			WordButton button = new WordButton();
			button.mWord = "好";
			data.add(button);
		}
		return data;
	};

	// 初始化已选的按钮
	public ArrayList<WordButton> initSelectButton() {

		ArrayList<WordButton> data = new ArrayList<WordButton>();

		View view = Util.getView(MainActivity.this, R.layout.self_grid_ui_btn);
		for (int i = 0; i < 2; i++) {

			WordButton button = new WordButton();
			button.mButton = (Button) view.findViewById(R.id.wordButton);
			button.mButton.setText("浩");
			button.mIsAvisiable = false;
			button.mButton.setTextColor(Color.WHITE);
			button.mButton.setBackgroundResource(R.drawable.game_wordblank);

			data.add(button);
		}
		return data;
	}

	// 初始化动画
	public void initAnimation() {

		mPanAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
		mPanLin = new LinearInterpolator();
		mPanAnim.setInterpolator(mPanLin);
		mPanAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mPole.startAnimation(mPoleOutAnim);

			}
		});

		mPoleInAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_45);
		mPoleInAnim.setFillAfter(true);
		mPoleInLin = new LinearInterpolator();
		mPoleInAnim.setInterpolator(mPoleInLin);
		mPoleInAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mPan.startAnimation(mPanAnim);

			}
		});

		mPoleOutAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_d_45);
		mPoleOutAnim.setFillAfter(true);
		mPoleOutLin = new LinearInterpolator();
		mPoleOutAnim.setInterpolator(mPoleOutLin);
		mPoleOutAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mIsRunning = false;
				mStartPaly.setVisibility(View.VISIBLE);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Toast.makeText(MainActivity.this, "HHH", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// 控制音乐播放
		handlePlayButton();
	}

	public void handlePlayButton() {

		if (mPole != null) {
			if (!mIsRunning) {
				mIsRunning = true;
				// 开始动画并且隐藏中心按钮
				mPole.startAnimation(mPoleInAnim);
				mStartPaly.setVisibility(View.GONE);
			}
		}
	}

	@Override
	public void onWordButtonClick(WordButton wordButton) {
		
		Toast.makeText(this, wordButton.mIndex + "", Toast.LENGTH_SHORT).show();
		
	}
}

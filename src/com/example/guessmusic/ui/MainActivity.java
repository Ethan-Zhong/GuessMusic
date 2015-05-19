package com.example.guessmusic.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.guessmusic.R;

public class MainActivity extends Activity implements OnClickListener{

	//定义动画
	private Animation mPanAnim;
	private LinearInterpolator mPanLin;
	
	private Animation mPoleInAnim;
	private LinearInterpolator mPoleInLin;
	
	private Animation mPoleOutAnim;
	private LinearInterpolator mPoleOutLin;
	
	private ImageView mPan;
	private ImageView mPole;
	private ImageButton mStartPaly;
	
	//当前动画是否正在运行
	private boolean mIsRunning = false;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化动画
		initAnimation();
		
		mPan = (ImageView) findViewById(R.id.imageView1);
		mPole = (ImageView) findViewById(R.id.imageView2);
		mStartPaly = (ImageButton) findViewById(R.id.btn_play_start);
		
		mStartPaly.setOnClickListener(this);
	}

	//初始化动画
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
		handlePlayButton();
	}
	
	public void handlePlayButton() {
		
		if(mPole != null){
			if(!mIsRunning){
				mIsRunning = true;
				//开始动画并且隐藏中心按钮
				mPole.startAnimation(mPoleInAnim);
				mStartPaly.setVisibility(View.GONE);
			}
		}
	}
}

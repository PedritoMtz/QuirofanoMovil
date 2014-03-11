package com.hu.quirofano;

import java.util.ArrayList;
import java.util.List;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import android.support.v4.app.*;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class BaseActivity extends SlidingFragmentActivity {
	
	private int mTitleRes;
	protected ListFragment mFrag;
	
	public BaseActivity(int titleRes){
		mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTitle(mTitleRes);
		
		setBehindContentView(R.layout.menu_frame);
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		mFrag = new RandomList();
		ft.replace(R.id.menu_frame, mFrag);
		ft.commit();
		
		final SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidth(25); //originalmente 15
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffset(280); //originalmente 60
		sm.setFadeDegree(0.35f); //originalmente 0.35f
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setBehindCanvasTransformer(new CanvasTransformer(){
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				// TODO Auto-generated method stub
				boolean layer = percentOpen > 0.0f && percentOpen < 1.0f;
				int layerType = layer ? View.LAYER_TYPE_HARDWARE : View.LAYER_TYPE_NONE;
				
				if (layerType != sm.getContent().getLayerType()){
					sm.getContent().setLayerType(layerType, null);
					sm.getMenu().setLayerType(layerType, null);
				}
			}
		});
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case android.R.id.home:
			toggle();
			return true;
		}
		return onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getSupportMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public class BasePagerAdapter extends FragmentPagerAdapter{
		private List<Fragment> mFragments = new ArrayList<Fragment>();
		private ViewPager mPager;
		
		public BasePagerAdapter(FragmentManager fm, ViewPager vp){
			super(fm);
			mPager = vp;
			mPager.setAdapter(this);
			for (int i = 0; i < 3; i++){
				addTab(new RandomList());
			}
		}
		
		public void addTab(Fragment frag){
			mFragments.add(frag);
		}
		
		@Override
		public Fragment getItem(int position){
			return mFragments.get(position);
		}
		
		@Override
		public int getCount(){
			return mFragments.size();
		}
	}

}
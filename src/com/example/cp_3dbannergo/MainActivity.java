package com.example.cp_3dbannergo;



import java.util.ArrayList;
import java.util.List;

import com.example.cp_3dbannergo.JazzyViewPager.TransitionEffect;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends Activity
{
    private List<View> views;
    private static final int MSG_CHANGE_PHOTO = 1;
    /** 图片自动切换时间 */
    private static final int PHOTO_CHANGE_TIME = 5000;
    private  int[]  imgIds=new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
    private JazzyViewPager mViewPager;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);


	mHandler = new Handler(getMainLooper()) {

	    @Override
	    public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		switch (msg.what) {
		case MSG_CHANGE_PHOTO:
		    int index = mViewPager.getCurrentItem();
		    if (index == imgIds.length - 1) {
			index = -1;
		    }
		
			mViewPager.setCurrentItem(index + 1);
		    mHandler.sendEmptyMessageDelayed(MSG_CHANGE_PHOTO,
			    PHOTO_CHANGE_TIME);
		}
	    }

	};


	mViewPager = (JazzyViewPager) findViewById(R.id.index_product_images_container);
	mViewPager.setTransitionEffect(TransitionEffect.CubeOut);
	mViewPager.setCurrentItem(0);
	mHandler.sendEmptyMessageDelayed(MSG_CHANGE_PHOTO, PHOTO_CHANGE_TIME);

	initViews();
	mViewPager.setAdapter(new MyAdapter());
	mViewPager.setOnPageChangeListener(new MyPageChangeListener());
    }


    private void initViews()
    {
	// TODO Auto-generated method stub

	views=new ArrayList<View>();
	views.add(LayoutInflater.from(this).inflate(R.layout.item1, null));
	views.add(LayoutInflater.from(this).inflate(R.layout.item2, null));
	views.add(LayoutInflater.from(this).inflate(R.layout.item3, null));
	views.add(LayoutInflater.from(this).inflate(R.layout.item4, null));
    }


    public class MyAdapter extends PagerAdapter {



	@Override
	public Object instantiateItem(View container, int position)
	{
	    ((ViewPager) container).addView(views.get(position), 0);
	    mViewPager.setObjectForPosition(views.get(position), position);

	    return views.get(position);
	}
	@Override
	public void destroyItem(View container, int position, Object object)
	{
	    ((ViewPager) container).removeView(mViewPager
		    .findViewFromObject(position));
	}
	@Override
	public int getCount()
	{
	    return views.size();
	}
	@Override
	public boolean isViewFromObject(View arg0, Object arg1)
	{
	    if (arg0 instanceof OutlineContainer) {
		return ((OutlineContainer) arg0).getChildAt(0) == arg1;
	    } else {
		return arg0 == arg1;
	    }
	}

    }

    /**
     * 当ViewPager中页面的状态发生改变时调用
     * 
     * @author Administrator
     * 
     */
    private class MyPageChangeListener implements OnPageChangeListener {

	/**
	 * This method will be invoked when a new page becomes selected.
	 * position: Position index of the new selected page.
	 */
	public void onPageSelected(int position) {
	}

	public void onPageScrollStateChanged(int arg0) {

	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}
    }

}

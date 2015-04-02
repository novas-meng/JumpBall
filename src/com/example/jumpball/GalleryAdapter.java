package com.example.jumpball;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter
{

	private Context mcontext;
	private Integer[] mImageIds=
		{
			R.drawable.easy,
			R.drawable.hard,
			R.drawable.crazy,
			R.drawable.crow,
			R.drawable.ghost,
			R.drawable.roll,
			R.drawable.bluetooth,
		};
	public GalleryAdapter(Context c)
	{
		mcontext=c;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mImageIds.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ImageView imageview=new ImageView(mcontext);
		imageview.setImageResource(mImageIds[arg0]);
		imageview.setLayoutParams(new Gallery.LayoutParams(200, 200));
		imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
		return imageview;
	}

}

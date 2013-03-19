package vincent4j.jsectionsearcher;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

@SuppressLint("DrawAllocation")
public class JSectionSearcherSideBar extends View {
	
	private ListView mListView;
	private JSectionSearcherAdapter mAdapter;
	private ArrayList<JSectionSearcherAdapter.IndexEntity> mIndexes;
	private List<Rect> mRectList = new ArrayList<Rect>();
	
	private int mColorUp;
	private int mColorDown;
	
	private Bitmap mSearchIcon;

	public JSectionSearcherSideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mColorUp = getResources().getColor(android.R.color.transparent);
		mColorDown = getResources().getColor(android.R.color.darker_gray);
		mSearchIcon = BitmapFactory.decodeResource(getResources(), R.drawable.j_section_searcher_search_item);
	}
	
	public void setListView(ListView listview) {
		if (listview == null) {
			return;
		}
		
		mListView = listview;
		
		try {
			ListAdapter adapter = mListView.getAdapter();
			
			if (adapter instanceof JSectionSearcherAdapter) {
				// Header not existed in ListView.
				mAdapter = (JSectionSearcherAdapter) adapter;
			} else if (adapter instanceof HeaderViewListAdapter) {
				// Header existed in ListView.
				mAdapter = (JSectionSearcherAdapter) (((HeaderViewListAdapter) adapter).getWrappedAdapter());
			}
			
			mIndexes = mAdapter.getIndexes();
		} catch (ClassCastException e) {
			mListView = null;
			
			Log.e("SectionSearcher", "The adapter of ListView is not SectionSearcherAdapter.");
	        
			throw new IllegalStateException(
	                    "SectionSearcher requires SectionSearcherAdapter in ListView.", e);
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (state==2) {
			canvas.drawColor(mColorDown);
		}else{
			canvas.drawColor(mColorUp);
		}
		
		
		
		super.onDraw(canvas);
		
		if (mIndexes == null) {
			return;
		}
		
		int cellHeight = getMeasuredHeight() / (mIndexes.size() + 1);
		
		Paint paint = new Paint();
		paint.setTextSize((int) (cellHeight * 0.75));
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		
<<<<<<< HEAD
		canvas.drawText("?", getMeasuredWidth() / 2, cellHeight, paint);
=======
		
		int top = 0;
		int left = 0;
		int right = 0;
		int bottm = 0;
>>>>>>> parent of d978c8f... bug fixed: 1.sidebar上下滑动背景色异常；2.sidebar快速滑动crash。
		
		for (int i = 0; i < mIndexes.size(); i++) {
			JSectionSearcherAdapter.IndexEntity indexEntity = mIndexes.get(i);
			canvas.drawText(indexEntity.index, getMeasuredWidth() / 2, cellHeight * (i + 1), paint);
			
			
		}
		
		
	}
	
	private int state = 1;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			state =2;
//			JSectionSearcherSideBar.this.setBackgroundColor(mColorDown);
			invalidate();
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			state =3;
			invalidate();
//			JSectionSearcherSideBar.this.setBackgroundColor(mColorUp);
		}
		
		int index = (int) (event.getY() / (getMeasuredHeight() / mIndexes.size()));
		
		System.out.println("dfds " + index);
		
		int position = mAdapter.getPositionForSection(index);
		
		if (position >= 0) {
			mListView.setSelection(position);
		}
		
		return super.onTouchEvent(event);
	}

}

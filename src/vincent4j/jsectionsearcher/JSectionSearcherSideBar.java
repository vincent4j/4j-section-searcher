package vincent4j.jsectionsearcher;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
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
	
	/**
	 * SideBar是否正处于按下的状态
	 */
	private boolean mIsTouching = false;
	
	/**
	 * SideBar一般状态下的背景色（相对于按下状态）
	 */
	private int mColorUp;
	
	/**
	 * SideBar被按下的背景色
	 */
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
		canvas.drawColor(mIsTouching ? mColorDown : mColorUp);
		
		super.onDraw(canvas);
		
		if (mIndexes == null) {
			return;
		}
		
		int cellHeight = getMeasuredHeight() / (mIndexes.size() + 1);
		
		Paint paint = new Paint();
		paint.setTextSize((int) (cellHeight * 0.75));
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		
		canvas.drawText("?", getMeasuredWidth() / 2, cellHeight, paint);
		
		for (int i = 0; i < mIndexes.size(); i++) {
			JSectionSearcherAdapter.IndexEntity indexEntity = mIndexes.get(i);
			canvas.drawText(indexEntity.index, getMeasuredWidth() / 2, cellHeight * (i + 1), paint);
		}
		
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			mIsTouching = true;
			invalidate();
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			mIsTouching = false;
			invalidate();
		}
		
		int index = (int) (event.getY() / (getMeasuredHeight() / mIndexes.size()));
		
		int position = mAdapter.getPositionForSection(index);
		
		if (position >= 0) {
			mListView.setSelection(position);
		}
		
		return super.onTouchEvent(event);
	}

}

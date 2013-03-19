package vincent4j.jsectionsearcher;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

@SuppressLint("DrawAllocation")
public class JSectionSearcherSideBar extends View {
	
	private ListView mListView;
	private JSectionSearcherAdapter mAdapter;
	private ArrayList<JSectionSearcherAdapter.IndexEntity> mIndexes;
	
	/**
	 * SideBar�Ƿ������ڰ��µ�״̬
	 */
	private boolean mIsTouching = false;
	
	/**
	 * SideBarһ��״̬�µı���ɫ������ڰ���״̬��
	 */
	private int mColorUp;
	
	/**
	 * SideBar�����µı���ɫ
	 */
	private int mColorDown;

	public JSectionSearcherSideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mColorUp = getResources().getColor(android.R.color.transparent);
		mColorDown = getResources().getColor(android.R.color.darker_gray);
	}
	
	public void setListView(ListView listview) {
		if (listview == null) {
			return;
		}
		
		mListView = listview;
		
		try {
			mAdapter = (JSectionSearcherAdapter) mListView.getAdapter();
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
		
		int cellHeight = getMeasuredHeight() / mIndexes.size();
		
		Paint paint = new Paint();
		paint.setTextSize((int) (cellHeight * 0.75));
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		
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

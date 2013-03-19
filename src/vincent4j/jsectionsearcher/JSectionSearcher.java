<<<<<<< HEAD
package vincent4j.jsectionsearcher;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class JSectionSearcher extends LinearLayout {

	private JSectionSearcherAdapter mAdapter;
	private ListView mListView;
	private JSectionSearcherSideBar mSideBar;
	private EditText mKeywordEdt;
	
	public JSectionSearcher(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
	}
	
	private void initViews(Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.j_section_searcher, this);
		mListView = (ListView) layout.findViewById(R.id.j_section_searcher_list);
		
		View header = inflater.inflate(R.layout.j_section_searcher_header, null);
		mListView.addHeaderView(header);
		mKeywordEdt = (EditText) header.findViewById(R.id.j_section_searcher_list_head_content);
		mKeywordEdt.addTextChangedListener(new KeywordTextWatcher());
		
		mSideBar = (JSectionSearcherSideBar) layout.findViewById(R.id.j_section_searcher_side);
	}
	
	public void setAdapter(JSectionSearcherAdapter adapter) {
		mAdapter = adapter;
		mListView.setAdapter(mAdapter);
		mSideBar.setListView(mListView);
<<<<<<< HEAD
	}
	
	public void setOnItemClickListener(OnItemClickListener listener) {
		mListView.setOnItemClickListener(listener);
=======
>>>>>>> parent of d978c8f... bug fixed: 1.sidebar上下滑动背景色异常；2.sidebar快速滑动crash。
	}
	
	private class KeywordTextWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			mAdapter.getFilter().filter(s);
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			
		}
		
	}
	
}
=======
package vincent4j.jsectionsearcher;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

public class JSectionSearcher extends LinearLayout {

	private JSectionSearcherAdapter mAdapter;
	private ListView mListView;
	private JSectionSearcherSideBar mSideBar;
	
	public JSectionSearcher(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
	}
	
	private void initViews(Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = (inflater.inflate(R.layout.j_section_searcher, this));
		mListView = (ListView) layout.findViewById(R.id.j_section_searcher_list);
		mSideBar = (JSectionSearcherSideBar) layout.findViewById(R.id.j_section_searcher_side);
	}
	
	public void setAdapter(JSectionSearcherAdapter adapter) {
		mAdapter = adapter;
		mListView.setAdapter(mAdapter);
		mSideBar.setListView(mListView);
	}
	
}
>>>>>>> parent of d978c8f... bug fixed: 1.sidebar上下滑动背景色异常；2.sidebar快速滑动crash。

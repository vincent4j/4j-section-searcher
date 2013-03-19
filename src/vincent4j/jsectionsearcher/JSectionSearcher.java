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
import android.widget.AdapterView.OnItemClickListener;

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
	}
	
	public void setOnItemClickListener(OnItemClickListener listener) {
		mListView.setOnItemClickListener(listener);
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

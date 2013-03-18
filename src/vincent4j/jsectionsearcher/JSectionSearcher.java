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
	}
	
	public void setAdapter(JSectionSearcherAdapter adapter) {
		mAdapter = adapter;
		mListView.setAdapter(mAdapter);
	}
	
}

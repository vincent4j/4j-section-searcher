package vincent4j.jsectionsearcher;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;

public class JSectionSearcher extends LinearLayout {

	private JSectionSearcherAdapter mAdapter;
	private ListView mListView;
	private JSectionSearcherSideBar mSideBar;
	
	public JSectionSearcher(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setAdapter(JSectionSearcherAdapter adapter) {
		mAdapter = adapter;
	}
	
}

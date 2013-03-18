package vincent4j.jsectionsearcher;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	private JSectionSearcher mSectionSearcher;
	private JSectionSearcherAdapter mAdapter;
	private ArrayList<JSectionSearcherSectionEntity> mSectionEntity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mSectionSearcher = (JSectionSearcher) findViewById(R.id.section_searcher);
		
		initData();
		mAdapter = new JSectionSearcherAdapter(this, mSectionEntity);
		mSectionSearcher.setAdapter(mAdapter);
	}
	
	private void initData() {
		 String [] INDEXES_CONTAINER = {
//		    	"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
//		    	"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
//		    	"U", "V", "W", "X", "Y", "Z",
				 "A", "C", "z",
		    };
		
		 mSectionEntity = new ArrayList<JSectionSearcherSectionEntity>();
		 JSectionSearcherSectionEntity sectionEntity = null;
		 
		 for (int i = 0; i < INDEXES_CONTAINER.length; i++) {
			String index = INDEXES_CONTAINER[i];
			
			ArrayList<JSectionSearcherItemEntity> items = new ArrayList<JSectionSearcherItemEntity>();
			
			for (int j = 0; j < 5; j++) {
				JSectionSearcherItemEntity item = new JSectionSearcherItemEntity(index + "-" + j + "-content", index + "-" + j + "-value");
				items.add(item);
			}
			
			sectionEntity = new JSectionSearcherSectionEntity(index, items);
			mSectionEntity.add(sectionEntity);
		}
		
	}


}

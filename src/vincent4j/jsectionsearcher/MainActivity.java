<<<<<<< HEAD
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
<<<<<<< HEAD
				 "A", "B","C", "D", "E",
=======
				 "A", "L", "O",  "S","X","z",
>>>>>>> parent of d978c8f... bug fixed: 1.sidebar上下滑动背景色异常；2.sidebar快速滑动crash。
		    };
		
		 mSectionEntity = new ArrayList<JSectionSearcherSectionEntity>();
		 JSectionSearcherSectionEntity sectionEntity = null;
		 
		 for (int i = 0; i < INDEXES_CONTAINER.length; i++) {
			String index = INDEXES_CONTAINER[i];
			
			ArrayList<JSectionSearcherItemEntity> items = new ArrayList<JSectionSearcherItemEntity>();
			
<<<<<<< HEAD
			for (int j = 0; j < 4; j++) {
=======
			for (int j = 0; j < 5; j++) {
>>>>>>> parent of d978c8f... bug fixed: 1.sidebar上下滑动背景色异常；2.sidebar快速滑动crash。
				JSectionSearcherItemEntity item = new JSectionSearcherItemEntity(index + "-" + j + "-content", index + "-" + j + "-value");
				items.add(item);
			}
			
			sectionEntity = new JSectionSearcherSectionEntity(index, items);
			mSectionEntity.add(sectionEntity);
		}
		
	}
<<<<<<< HEAD
	
	private class JOnItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			System.out.println("position: " + position);
			System.out.println(mAdapter.getItem(position).toString());
		}
		
	}
=======
>>>>>>> parent of d978c8f... bug fixed: 1.sidebar上下滑动背景色异常；2.sidebar快速滑动crash。


}
=======
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
				 "A", "L", "O",  "S","X","z",
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
>>>>>>> parent of d978c8f... bug fixed: 1.sidebar上下滑动背景色异常；2.sidebar快速滑动crash。

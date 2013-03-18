package vincent4j.jsectionsearcher;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class JSectionSearcherAdapter extends BaseAdapter implements SectionIndexer {
	
	private static final String TAG = "JSectionSearcher";
	
	private Context mContext;
	
	/**
	 * Original data, initialized when construct method called.
	 */
	private ArrayList<JSectionSearcherSectionEntity> mOriginalData;
	private LayoutInflater mInflater;
	
	private ArrayList<ItemEntity> mItems;
	private ArrayList<IndexEntity> mIndexes;
	
	public class IndexEntity {
		public String index;
		public int fromPosition;
		public int toPosition;
		
		@Override
		public String toString() {
			return "IndexEntity [index=" + index + ", fromPosition="
					+ fromPosition + ", toPosition=" + toPosition + "]";
		}
		
		
	}
	
	public class ItemEntity {
		public IndexEntity index;
		public JSectionSearcherItemEntity item;
		@Override
		public String toString() {
			return "ItemEntity [index=" + index + ", item=" + item + "]";
		}
		
		
	}
	
    /**
     * Item�Ĳ������ͣ�ʵ����ʱ����
     * 	0��Ĭ�ϣ�
     */
    private int mLayoutType;
    
    /**
     * Item�Ĳ������ͣ�Ĭ�ϣ���ʾ1��TextView
     */
    public final static int LAYOUT_TYPE_DEFAULT = 0;
    
    /**
     * �ұ߿��ټ�����������
     */
    private final static String [] INDEXES_CONTAINER = {
    	"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
    	"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
    	"U", "V", "W", "X", "Y", "Z"
    };
    
    public JSectionSearcherItemEntity getItemSelected() {
    	return null;
    }
    
    public JSectionSearcherAdapter(Context context, ArrayList<JSectionSearcherSectionEntity> data) {
    	this(context, LAYOUT_TYPE_DEFAULT, data);
    }
	
	public JSectionSearcherAdapter(Context context, int layoutType, ArrayList<JSectionSearcherSectionEntity> data) {
		mContext = context;
		mLayoutType = layoutType;
		mOriginalData = data;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		initData(mOriginalData);
	}
	
	private void initData(ArrayList<JSectionSearcherSectionEntity> data) {
		if (data == null) {
			return;
		}
		
		mItems = new ArrayList<ItemEntity>();
		mIndexes = initIndexes();
		
		int indexIndex = 0;
		int itemPosition = 0;
		
		for (int i = 0; i < data.size(); i++) {
			System.out.println(" data.size() " + i);
			
			JSectionSearcherSectionEntity sectionEntity = data.get(i);
			
			System.out.println("sectionEntity " + sectionEntity);
			
			if (sectionEntity == null) {
				continue;
			}
			
			String index = sectionEntity.getIndex();
			
			System.out.println("sectionEntity.getIndex() " + index);
			
			IndexEntity indexEntity = mIndexes.get(indexIndex);
			
			System.out.println("indexEntity " + indexEntity);
			
			while (!indexEntity.index.equalsIgnoreCase(index)) {
				indexIndex++;
				indexEntity = mIndexes.get(indexIndex);
			}
			
			ArrayList<JSectionSearcherItemEntity> sectionItems = sectionEntity.getItems();
			
			if (sectionItems == null) {
				continue;
			}
			
			int sectionItemsSize = sectionItems.size();
			
			indexEntity.fromPosition = itemPosition;
			indexEntity.toPosition = itemPosition + sectionItemsSize -1;
			itemPosition = indexEntity.toPosition + 1;
			
			for (int j = 0; j < sectionItemsSize; j++) {
				ItemEntity itemEntity = new ItemEntity();
				itemEntity.index = indexEntity;
				itemEntity.item = sectionItems.get(j);
				mItems.add(itemEntity);
			}
			
			indexIndex++;
		}
		
	}
	
	private ArrayList<IndexEntity> initIndexes() {
		ArrayList<IndexEntity> ret = new ArrayList<IndexEntity>();
		
		for (int i = 0; i < INDEXES_CONTAINER.length; i++) {
			String index = INDEXES_CONTAINER[i];
			
			IndexEntity indexEntity = new IndexEntity();
			indexEntity.index = index;
			indexEntity.fromPosition = -1;
			indexEntity.toPosition = -1;
			
			ret.add(indexEntity);
		}
		
		return ret;
	}
	
	
	private int getLayoutResourceId() {
		int resourceId = android.R.layout.simple_list_item_1;
		
		/*
		 * ����Ҫ��������Item��������ʱ���ڴ�������Ӧ��switch��֧��
		switch (mLayoutType) {
		default:
			
			break;
		}*/
		
		return resourceId;
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;

        if (convertView == null) {
            view = mInflater.inflate(getLayoutResourceId(), parent, false);
        } else {
            view = convertView;
        }
        
        switch (mLayoutType) {
		case LAYOUT_TYPE_DEFAULT:
			try {
				((TextView) view).setText(mItems.get(position).item.getContent());
			} catch (ClassCastException e) {
				Log.e(TAG, "You must supply a resource ID for a TextView");
	            throw new IllegalStateException(
	            		TAG + " requires the resource ID to be a TextView", e);
			}
			
			break;

		// ����Item��������ڴ˴���Ӽ���
		default:
			break;
		}

        return view;
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPositionForSection(int section) {
		return 0;
	}

	@Override
	public int getSectionForPosition(int position) {
		return 0;
	}

}

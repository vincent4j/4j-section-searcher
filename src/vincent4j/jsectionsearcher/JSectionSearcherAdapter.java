package vincent4j.jsectionsearcher;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class JSectionSearcherAdapter extends BaseAdapter implements SectionIndexer, Filterable {
	
	private Context mContext;
	
	/**
	 * Original data, initialized when construct method called.
	 */
	private ArrayList<JSectionSearcherSectionEntity> mOriginalData;
	private LayoutInflater mInflater;
	
	private ArrayList<ItemEntity> mItems;
	private ArrayList<IndexEntity> mIndexes;
	
	private ItemsFilter mFilter;
	
    /**
     * Item的布局类型，实例化时传入
     * 	0，默认；
     */
    private int mLayoutType;
    
    /**
     * Item的布局类型：默认；显示1个TextView
     */
    public final static int LAYOUT_TYPE_DEFAULT = 0;
    
    /**
     * 右边快速检索栏的索引
     */
    private final static String [] INDEXES_CONTAINER = {
    	"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
    	"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
    	"U", "V", "W", "X", "Y", "Z"
    };
    
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
	
	public ArrayList<IndexEntity> getIndexes() {
		return mIndexes;
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
			
			System.out.println("indexEntity " + indexEntity);
			
			for (int j = 0; j < sectionItemsSize; j++) {
				ItemEntity itemEntity = new ItemEntity();
				itemEntity.index = indexEntity;
				itemEntity.item = sectionItems.get(j);
				mItems.add(itemEntity);
				
				System.out.println("itemEntity " + itemEntity);
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
		int resourceId = R.layout.j_section_searcher_item_default;
		
		/*
		 * 当需要新增其他Item布局类型时，在此新增对应的switch分支。
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
	public JSectionSearcherItemEntity getItem(int position) {
		return mItems.get(position).item;
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
				bindViewDefault(view, position);
			} catch (ClassCastException e) {
				Log.e("SectionSearcher", "You must supply a resource ID for a TextView");
	            throw new IllegalStateException(
	            		"SectionSearcher" + " requires the resource ID to be a TextView", e);
			}
			
			break;

		// 其他Item布局类别在此处添加即可
		default:
			break;
		}

        return view;
	}
	
	private void bindViewDefault(View view, int position) {
		TextView sectionView = (TextView) view.findViewById(R.id.j_section_searcher_item_default_0);
		
		if (isSectionVisibility(position)) {
			sectionView.setVisibility(View.VISIBLE);
			sectionView.setText(mItems.get(position).index.index);
		} else {
			sectionView.setVisibility(View.GONE);
		}
		
		((TextView) view.findViewById(R.id.j_section_searcher_item_default_1)).setText(mItems.get(position).item.getContent());
	}
	
	private boolean isSectionVisibility(int position) {
		return position == mItems.get(position).index.fromPosition;
	}

	@Override
	public Object[] getSections() {
		return null;
	}

	@Override
	public int getPositionForSection(int section) {
		if (section >= mIndexes.size()) {
			return mIndexes.size() - 1;
		} else if (section < 0) {
			return 0;
		} else {
			return mIndexes.get(section).fromPosition;
		}
	}

	@Override
	public int getSectionForPosition(int position) {
		return 0;
	}
	
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

	@Override
	public Filter getFilter() {
		return ((mFilter == null) ? new ItemsFilter() : mFilter);
	}
	
	/**
	 * 判断某条ListItem数据是否在检索范围之内。
	 * @param item 
	 * @param keyword 检索关键字
	 * @return
	 */
	private boolean isContainKeyword(JSectionSearcherItemEntity item, String keyword) {
		if ((keyword == null) || (keyword.length() < 1)) {
			return true;
		}
		
		if (item == null) {
			return false;
		}
		
		boolean ret = true;
		
		switch (mLayoutType) {
		case LAYOUT_TYPE_DEFAULT:
			String contentDefault = item.getContent();
			
			if (contentDefault == null) {
				ret = false;
				break;
			}
			
			ret = contentDefault.contains(keyword);
			break;

		default:
			break;
		}
		
		return ret;
		
	}
	
	private class ItemsFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults ret = new FilterResults();
			
			if ((constraint == null) 
					|| (constraint.toString().trim().length() < 1)) {
				ret.values = mOriginalData;
				return ret;
			}
			
			String keyword = constraint.toString().trim();
			
			ArrayList<JSectionSearcherSectionEntity> sections = new ArrayList<JSectionSearcherSectionEntity>();
			
			for (int i = 0; i < mOriginalData.size(); i++) {
				ArrayList<JSectionSearcherItemEntity> items = new ArrayList<JSectionSearcherItemEntity>();
				
				for (int j = 0; j < mOriginalData.get(i).getItems().size(); j++) {
					if (isContainKeyword(mOriginalData.get(i).getItems().get(j), keyword)) {
						items.add(mOriginalData.get(i).getItems().get(j));
					}
				}
				
				if (items.size() < 1) {
					continue;
				} else {
					sections.add(new JSectionSearcherSectionEntity(mOriginalData.get(i).getIndex(), items));
				}
			}
			
			ret.values = sections;
			return ret;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			mItems.clear();
			mIndexes.clear();
			
			initData((ArrayList<JSectionSearcherSectionEntity>) results.values);
			JSectionSearcherAdapter.this.notifyDataSetChanged();
		}
		
	}

}

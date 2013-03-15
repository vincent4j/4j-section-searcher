package vincent4j.jsectionsearcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private List<? extends Map<String, ?>> mData;
	private LayoutInflater mInflater;
	
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
    private final static String [] SECTION_INDEX = {
    	"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
    	"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
    	"U", "V", "W", "X", "Y", "Z"
    };
    
    /**
     * ���ټ���ʵ��
     */
    private ArrayList<SearcherIndexEntity> mSearcherIndex;
    
    private class SearcherIndexEntity {
    	String content; // ���ټ�����ʾ����
    	int fromPosition; // Section��Ӧ�Ŀ�ʼPostion
    	int toPosition; // Section��Ӧ�Ľ���Position
    }
    
    /**
     * ʵ���������ʵ��
     */
    private ArrayList<SearcherItemEntity> mSearcherItem;
    
    /**
     * ʵ���������ʵ��
     */
    public class SearcherItemEntity {
    	String contentStr01;
    	String contentStr02;
    }
    
    
    public JSectionSearcherAdapter(Context context, List<Map<String, List<SearcherItemEntity>>> data) {
    	this(context, LAYOUT_TYPE_DEFAULT, data);
    }
	
	public JSectionSearcherAdapter(Context context, int layoutType, List<Map<String, List<SearcherItemEntity>>> data) {
		mContext = context;
		mLayoutType = layoutType;
		mData = data;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		initData(data);
	}
	
	private void initData(List<Map<String, List<SearcherItemEntity>>> data) {
		if (data == null) {
			return;
		}
		
		mSearcherItem = new ArrayList<SearcherItemEntity>();
		mSearcherIndex = new ArrayList<SearcherIndexEntity>();
		SearcherIndexEntity searcherIndex = null;
		int searcherIndexPosition = 0; // ���ټ�����������λ��
		
		for (int i = 0; i < data.size(); i++) {
			
			
			Map<String, List<SearcherItemEntity>> sectionData = data.get(i);
			
			String searcherIndexContent = null;
			
//			for (int j = 0; j < array.length; j++) {
//				
//			}
		}
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
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
				((TextView) view).setText("");
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

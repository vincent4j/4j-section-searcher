package vincent4j.jsectionsearcher;

import java.util.ArrayList;

public class JSectionSearcherSectionEntity {

	private String index;
	private ArrayList<JSectionSearcherItemEntity> items;

	public JSectionSearcherSectionEntity(String index,
			ArrayList<JSectionSearcherItemEntity> items) {
		super();
		this.index = index;
		this.items = items;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public ArrayList<JSectionSearcherItemEntity> getItems() {
		return items;
	}

	public void setItems(ArrayList<JSectionSearcherItemEntity> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "JSectionSearcherSectionEntity [index=" + index + ", items="
				+ items + "]";
	}

}

package vincent4j.jsectionsearcher;

public class JSectionSearcherItemEntity {

	private String content;
	private String value;

	public JSectionSearcherItemEntity(String content, String value) {
		super();
		this.content = content;
		this.value = value;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "JSectionSearcherItemEntity [content=" + content + ", value="
				+ value + "]";
	}

}

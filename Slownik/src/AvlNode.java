
public class AvlNode {
	protected AvlNode left;
	protected AvlNode right;
	protected AvlNode parent;
	protected String key;
	protected int balance;
	protected String[] translate;

	AvlNode(String k) {
		left = right = parent = null;
		balance = 0;
		key = k;
	}
	public String toString() {
		return "" + key;
	}
}

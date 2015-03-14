import java.util.ArrayList;

public class Node {

	public ArrayList<Node> children = new ArrayList<Node>();
	private Node parent;
	public GameState value;


	public Node(GameState value) {
		this.value = value;
	}

	public void addChildren(Node node)
	{
		node.parent = this;
		children.add(node);
	}

	public Node getParent() {
		return parent;
	}
}

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class VisualNode {
    Circle circle;
    Text text;

    VisualNode(RBTree.Node rbNode, double x, double y) {
        this.circle = new Circle(x, y, 20);
        this.circle.setFill(rbNode.color == RBTree.Color.RED ? Color.RED : Color.BLACK);

        this.text = new Text(x - 7, y + 5, String.valueOf(rbNode.value));
        this.text.setFill(Color.WHITE);
    }

    public static void drawTree(Pane root, RBTree.Node currentNode, double x, double y, double spacing) {
        if (currentNode == null) return;

        VisualNode visualNode = new VisualNode(currentNode, x, y);
        root.getChildren().addAll(visualNode.circle, visualNode.text);

        if (currentNode.left != null) {
            Line leftLine = new Line(x, y + 20, x - spacing, y + 100);
            root.getChildren().add(leftLine);
            drawTree(root, currentNode.left, x - spacing, y + 100, spacing / 2);
        }

        if (currentNode.right != null) {
            Line rightLine = new Line(x, y + 20, x + spacing, y + 100);
            root.getChildren().add(rightLine);
            drawTree(root, currentNode.right, x + spacing, y + 100, spacing / 2);
        }
    }
}

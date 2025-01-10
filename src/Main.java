import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private final RBTree rbTree = new RBTree();
    private final Pane rootPane = new Pane();

    @Override
    public void start(Stage primaryStage) {
        TextField valueTextField = new TextField();
        valueTextField.setPromptText("Enter a value");

        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e -> {
            try {
                int value = Integer.parseInt(valueTextField.getText());
                if (rbTree.insert(value)) {
                    rootPane.getChildren().clear();
                    drawTree();
                    System.out.println("Inserted value: " + value);
                } else {
                    System.out.println("Value already exists");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input");
            }
        });

        VBox layout = new VBox(10, valueTextField, insertButton, rootPane);

        Scene scene = new Scene(layout, 1000, 1000);
        primaryStage.setTitle("Red-Black Tree Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawTree() {
        if (rbTree.getRoot() != null) {
            VisualNode.drawTree(rootPane, rbTree.getRoot(), 400, 50, 100);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

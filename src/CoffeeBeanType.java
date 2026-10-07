import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public enum CoffeeBeanType {
    ARABICA("arabica", false, "images/arabica.png"),
    ROBUSTA("robusta", false, "images/robusta.png"),
    DECAF("decafeine", true, "images/decaf.png");

    private final String name;
    private final boolean isDecaf;
    private final String path;

    CoffeeBeanType(String name, boolean isDecaf, String path) {
        this.name = name;
        this.isDecaf = isDecaf;
        this.path = path;
    }

    public boolean isDecaf() {
        return this.isDecaf;
    }

    public String getName() {
        return this.name;
    }

    public ImageView getView() {
        return new ImageView(new Image(getClass().getResourceAsStream(this.path)));
    }

    public void openCoffeeBar(Pane pane) {
        VBox popup = new VBox(10);
        popup.setPadding(new Insets(10, 15, 15, 15));
        popup.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px;");
        popup.setAlignment(Pos.TOP_CENTER);



        popup.setMinWidth(250);
        popup.setMinHeight(200);
        popup.setLayoutX(200);
        popup.setLayoutY(150);
        BorderPane topBar = new BorderPane();
        Label x = new Label("X");
        x.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-cursor: hand; -fx-padding: 2px 6px;");

        x.setOnMouseClicked(e -> pane.getChildren().remove(popup));


        topBar.setRight(x);

        Label gramsLabel = new Label("grams");
        popup.getChildren().add(gramsLabel);
        TextField gramsInput = new TextField();
        popup.getChildren().add(gramsInput);



        Button button = new Button("OK");
        popup.getChildren().add(button);

        button.setOnMouseClicked(event -> {
            String text = gramsInput.getText();
            try {
                int cislo = Integer.parseInt(text);
                System.out.println("Uložené číslo: " + cislo);
                CoffeeBowl bowl = new CoffeeBowl(cislo, this, pane);
                pane.getChildren().remove(popup);
                pane.getChildren().add(bowl.getView());
            } catch (NumberFormatException e) {
                System.out.println("Chyba: Zadaný text nie je platné číslo!");
            }
        });



        popup.getChildren().add(topBar);




        pane.getChildren().add(popup);
    }
}

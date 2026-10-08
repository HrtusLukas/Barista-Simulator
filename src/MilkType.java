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

public enum MilkType {
    COW_MILK("cowMilk", false, "images/cowMilk.png", 620, 120, 150),
    OAT_MILK("oatMilk", true, "images/oatMilk.png", 650, 200, 150),
    SOY_MILK("soyMilk", true, "images/soyMilk.png", 770, 100, 150);

    private final String name;
    private final boolean isVegan;
    private final String path;

    private final double defaultX;
    private static final double DEFAULT_Y = 140.0;
    private final double defaultWidth;
    private final double defaultHeight;

    MilkType(String name, boolean isVegan, String path, double defaultX, double defaultWidth, double defaultHeight) {
        this.name = name;
        this.isVegan = isVegan;
        this.path = path;
        this.defaultX = defaultX;
        this.defaultWidth = defaultWidth;
        this.defaultHeight = defaultHeight;
    }

    public String getName() {
        return this.name;
    }

    public boolean isVegan() {
        return this.isVegan;
    }

    public double getDefaultX() {
        return this.defaultX;
    }

    public double getDefaultY() {
        return DEFAULT_Y;
    }

    public double getDefaultWidth() {
        return this.defaultWidth;
    }

    public double getDefaultHeight() {
        return this.defaultHeight;
    }

    public ImageView getView() {
        return new ImageView(new Image(getClass().getResourceAsStream(this.path)));
    }

    public void openMilkMenu(Pane pane) {
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

        Label gramsLabel = new Label("ml");
        TextField gramsInput = new TextField();
        Button button = new Button("OK");

        button.setOnMouseClicked(event -> {
            String text = gramsInput.getText();
            try {
                int cislo = Integer.parseInt(text);
                System.out.println("Uložené číslo: " + cislo);
                MilkSteamer.getInstance().AddMilk(this ,cislo );
                pane.getChildren().remove(popup);
            } catch (NumberFormatException e) {
                System.out.println("Chyba: Zadaný text nie je platné číslo!");
            }
        });

        popup.getChildren().addAll(topBar, gramsLabel, gramsInput, button);

        pane.getChildren().add(popup);
    }
}
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class EspressoMachine extends GUIObject {
    private Portafilter insertedFilter;
    private CupState cup;
    private static EspressoMachine instance;

    public EspressoMachine() {
        this.setView("images/machine.png");
        this.setX(700);
        this.setY(430);
        this.setHeight(325);
        this.setWidth(325);
        instance = this;

    }

    public void insertFilter(Portafilter insertedFilter) {
        this.insertedFilter = insertedFilter;
    }

    public void placeCup (CupState cup) {
        this.cup = cup;
    }

    public CupState getCup() {
        return cup;
    }

    public void takeCup() {
        this.cup = null;
    }

    public void brew(int ml) {
        if (this.cup != null && this.insertedFilter != null && this.insertedFilter.getLoadedBean() != null) {
            PauseTransition pause = new PauseTransition(Duration.seconds(5));

            pause.setOnFinished(e -> {
                System.out.println("Ubehlo 5 sekúnd!");
                int grams = this.insertedFilter.getCoffeeGrams();
                this.cup.addCoffee(this.insertedFilter.getLoadedBean(), grams, ml);
                this.insertedFilter.setDirty(true);
                this.cup.setX(300);
                this.cup.setY(500);
            });

            pause.play();

        }
    }

    public Portafilter getInsertedFilter() {
        return insertedFilter;
    }

    public Portafilter removeFilter() {
        Portafilter filterToReturn = this.insertedFilter;
        this.insertedFilter = null;
        return filterToReturn;
    }

    public static EspressoMachine getInstance() {
        return instance;
    }

    public void openEspressoMachineMenu() {
        Pane pane = (Pane) this.view.getParent();
        if (pane == null) {
            System.out.println("Chyba: EspressoMachine nemá parent Pane!");
            return;
        }

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


        popup.getChildren().add(topBar);


        Label mlLabel = new Label("Zadaj objem (ml):");
        popup.getChildren().add(mlLabel);

        TextField mlInput = new TextField();
        popup.getChildren().add(mlInput);

        Button button = new Button("OK");
        popup.getChildren().add(button);

        button.setOnMouseClicked(event -> {
            String text = mlInput.getText();
            try {
                int cislo = Integer.parseInt(text);
                System.out.println("Uložené číslo: " + cislo);
                this.brew(cislo);
                pane.getChildren().remove(popup);
            } catch (NumberFormatException e) {
                System.out.println("Chyba: Zadaný text nie je platné číslo!");
            }
        });


        pane.getChildren().add(popup);
        popup.toFront();
    }


}

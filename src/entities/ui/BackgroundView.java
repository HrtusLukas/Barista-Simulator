package entities.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BackgroundView {
    private final ImageView desk;
    private final ImageView shelf;

    public BackgroundView() {
        this.desk = new ImageView(new Image(getClass().getResourceAsStream("/images/table.png")));
        this.desk.setLayoutX(0);
        this.desk.setLayoutY(510);
        this.desk.setFitWidth(1000);
        this.desk.setFitHeight(400);

        this.shelf = new ImageView(new Image(getClass().getResourceAsStream("/images/shelf.png")));
        this.shelf.setLayoutX(-100);
        this.shelf.setLayoutY(110);
        this.shelf.setFitWidth(1200);
        this.shelf.setFitHeight(400);
    }

    public void attachTo(Pane parentPane) {
        parentPane.getChildren().addAll(this.desk, this.shelf);
    }

    public ImageView getDesk() {
        return this.desk;
    }
}
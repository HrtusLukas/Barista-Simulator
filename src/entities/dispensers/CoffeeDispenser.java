package entities.dispensers;

import entities.enums.CoffeeBeanType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CoffeeDispenser {
    private Pane objectLayer;

    public CoffeeDispenser(Pane objectLayer) {
        this.objectLayer = objectLayer;
    }

    public void createCoffeeDispenser(CoffeeBeanType type) {
        ImageView view = type.getView();

        if (type == CoffeeBeanType.ARABICA) {
            view.setLayoutX(120);
            view.setFitWidth(120);
            view.setFitHeight(150);
        } else if (type == CoffeeBeanType.ROBUSTA) {
            view.setLayoutX(220);
            view.setFitWidth(200);
            view.setFitHeight(150);
        } else {
            view.setLayoutX(420);
            view.setFitWidth(100);
            view.setFitHeight(150);
        }

        view.setLayoutY(140);

        this.objectLayer.getChildren().add(view);

        view.setOnMouseClicked(e -> {
            type.openCoffeeBar(this.objectLayer);
        });
    }
}

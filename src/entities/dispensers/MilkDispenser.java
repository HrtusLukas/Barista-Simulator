package entities.dispensers;

import entities.CupState;
import entities.enums.MilkType;
import entities.stations.MilkSteamer;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MilkDispenser {
    private final Pane objectLayer;
    private MilkType milkType;

    public MilkDispenser(Pane objectLayer) {
        this.objectLayer = objectLayer;
    }

    public void createMilkDispenser(MilkType type) {
        this.milkType = type;
        ImageView view = type.getView();
        MilkSteamer milkSteamer = MilkSteamer.getInstance();

        double startX = type.getDefaultX();
        double startY = type.getDefaultY();

        view.setLayoutX(startX);
        view.setLayoutY(startY);
        view.setFitWidth(type.getDefaultWidth());
        view.setFitHeight(type.getDefaultHeight());

        this.objectLayer.getChildren().add(view);

        view.setOnMouseDragged(e -> {
            view.setLayoutX(e.getSceneX() - (type.getDefaultWidth() / 2));
            view.setLayoutY(e.getSceneY() - (type.getDefaultHeight() / 2));
        });

        view.setOnMouseReleased(e -> {

            if (milkSteamer.getView().getBoundsInParent().intersects(view.getBoundsInParent())) {
                type.openMilkMenu(objectLayer, true, null);
            }

            for (Node node : objectLayer.getChildren()) {
                if (node.getUserData() instanceof CupState cup) {
                    if (milkSteamer.getView().getBoundsInParent().intersects(cup.getView().getBoundsInParent())) {
                        type.openMilkMenu(objectLayer, false, cup);
                    }
                }
            }

            view.setLayoutX(startX);
            view.setLayoutY(startY);
        });
    }
}

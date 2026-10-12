package entities.dispensers;

import controllers.OrderInspection;
import entities.CupState;
import entities.enums.CupType;
import entities.enums.Recipe;
import entities.stations.EspressoMachine;
import entities.stations.MilkSteamer;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CupDispenser {
    private final Pane objectLayer;
    private final Button acceptButtonView;
    private final ImageView deskView; // 1. Pridaný odkaz na stôl

    public CupDispenser(Pane objectLayer, Button acceptButtonView, ImageView deskView) {
        this.objectLayer = objectLayer;
        this.acceptButtonView = acceptButtonView;
        this.deskView = deskView;
    }

    public void createCupDispenser(CupType type) {
        CupState cup = new CupState(type);

        cup.getView().setOnMousePressed(mouseEvent -> {
            spawnDraggableCup(type);
        });

        this.objectLayer.getChildren().add(cup.getView());
    }

    private void spawnDraggableCup(CupType type) {
        CupState newCupState = new CupState(type);
        ImageView cupView = newCupState.getView();
        EspressoMachine espressoMachine = EspressoMachine.getInstance();
        MilkSteamer milkSteamer = MilkSteamer.getInstance();

        this.objectLayer.getChildren().add(cupView);

        cupView.setOnMouseDragged(e -> {
            // Opravené odčítanie
            cupView.setLayoutX(e.getSceneX() - 30);
            cupView.setLayoutY(e.getSceneY() - 30);
            cupView.toFront();
        });

        cupView.setOnMouseReleased(e -> {
            if (espressoMachine != null && espressoMachine.getView().getBoundsInParent().intersects(cupView.getBoundsInParent())) {

                if (newCupState.getCupType() == CupType.PAPER) {
                    cupView.setLayoutX(espressoMachine.getX() + 130);
                } else {
                    cupView.setLayoutX(espressoMachine.getX() + 115);
                }
                cupView.setLayoutY(espressoMachine.getY() + 150);

                espressoMachine.placeCup(newCupState);
                System.out.println("✅ Pohár typ " + type + " bol vložený do kávovaru!");

            } else if (milkSteamer != null && milkSteamer.getView().getBoundsInParent().intersects(cupView.getBoundsInParent())) {
                if (newCupState.getCupType() == CupType.PAPER) {
                    cupView.setLayoutX(milkSteamer.getX() + 130);
                } else {
                    cupView.setLayoutX(milkSteamer.getX() + 115);
                }
                cupView.setLayoutY(milkSteamer.getY() + 150);

                milkSteamer.placeCup(newCupState);
                System.out.println("✅ Pohár typ " + type + " bol vložený pod napenovač!");

            } else if (this.acceptButtonView != null && this.acceptButtonView.getBoundsInParent().intersects(cupView.getBoundsInParent())) {
                OrderInspection.inspectOrder(Recipe.FLAT_WHITE, newCupState);
                this.objectLayer.getChildren().remove(cupView);
                System.out.println("📋 Objednávka bola odovzdaná!");

            } else if (this.deskView != null && this.deskView.getBoundsInParent().intersects(cupView.getBoundsInParent())) {
                // 2. Pohár je na stole! Neodstraňujeme ho, ostáva položený tam, kde si ho pustil.
                System.out.println("☕ Pohár bol položený na stôl.");

            } else {
                // 3. Pohár je mimo staníc aj mimo stola -> až teraz sa vyhodí!
                this.objectLayer.getChildren().remove(cupView);
                System.out.println("🗑️ Pohár bol zahodený (mimo stola).");
            }
        });
    }
}
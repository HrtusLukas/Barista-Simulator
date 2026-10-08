import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;

public class SimulatorLoop extends Application {
    private Pane pane;
    private Pane objectLayer;
    private Scene scene;
    private EspressoMachine espressoMachine;
    private GrinderStation grinderStation;
    private Portafilter portafilter;
    private MilkSteamer milkSteamer;
    private Button acceptButtonView;



    @Override
    public void start(Stage stage) {

        this.pane = new Pane();
        this.pane.setPrefSize(1000, 800);
        this.scene = new Scene(this.pane);

        Canvas canvas = new Canvas(1000, 800);
        canvas.setMouseTransparent(true);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.pane.getChildren().add(canvas);

        ImageView desk = new ImageView(new Image(getClass().getResourceAsStream("/images/table.png")));
        desk.setLayoutX(0);
        desk.setLayoutY(510);
        desk.setFitWidth(1000);
        desk.setFitHeight(400);

        ImageView shelf = new ImageView(new Image(getClass().getResourceAsStream("/images/shelf.png")));
        shelf.setLayoutX(-100);
        shelf.setLayoutY(110);
        shelf.setFitWidth(1200);
        shelf.setFitHeight(400);

        this.pane.getChildren().add(desk);
        this.pane.getChildren().add(shelf);

        this.objectLayer = new Pane();
        this.pane.getChildren().add(this.objectLayer);



        this.acceptButtonView = new Button("ACCEPT / ODOVZDAŤ");
        this.acceptButtonView.setLayoutX(450);
        this.acceptButtonView.setLayoutY(20);
        this.acceptButtonView.setPrefWidth(120);
        this.acceptButtonView.setPrefHeight(50);
        this.acceptButtonView.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: #4CAF50; -fx-text-fill: white;");


        this.objectLayer.getChildren().add(this.acceptButtonView);
        this.acceptButtonView.toFront();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, 1000, 800);
            }
        };
        timer.start();

        Customer jozo = new Customer("jozo", Recipe.ESPRESSO);
        CupState cup = new CupState(CupType.CERAMIC);

        OrderInspection.inspectOrder(jozo.getCoffee(), cup);

        TicketUI ticketUI = new TicketUI(this.pane, jozo.getCoffee());
        this.objectLayer.getChildren().add(ticketUI.getView());

        ticketUI.getView().setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                ticketUI.openTicket();
                System.out.println("cliked");
            }
        });

        this.espressoMachine = new EspressoMachine();
        this.objectLayer.getChildren().add(this.espressoMachine.getView());

        this.grinderStation = new GrinderStation();
        grinderStation.getView().setUserData(grinderStation);
        this.objectLayer.getChildren().add(grinderStation.getView());

        espressoMachine.getView().setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                this.portafilter = new Portafilter(pane);
                pane.getChildren().add(this.portafilter.getView());
                System.out.println("x");
            }

            if (event.getButton() == MouseButton.PRIMARY) {
                if (espressoMachine.getInsertedFilter() != null && espressoMachine.getCup() != null) {
                    espressoMachine.openEspressoMachineMenu();
                }
            }
        });

        this.milkSteamer = new MilkSteamer();
        this.pane.getChildren().add(milkSteamer.getView());

        this.milkSteamer.getView().setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                this.milkSteamer.steam();
            }
        });

        createCupDispenser(CupType.CERAMIC);
        createCupDispenser(CupType.PAPER);
        createCoffeeDispenser(CoffeeBeanType.ARABICA);
        createCoffeeDispenser(CoffeeBeanType.ROBUSTA);
        createCoffeeDispenser(CoffeeBeanType.DECAF);
        createMilkDispenser(MilkType.COW_MILK);
        createMilkDispenser(MilkType.OAT_MILK);
        createMilkDispenser(MilkType.SOY_MILK);

        stage.setTitle("Moja hra");
        stage.setScene(this.scene);
        stage.show();
    }

    public void createCupDispenser(CupType type) {
        CupState cup = new CupState(type);

        cup.getView().setOnMousePressed(mouseEvent -> {
            spawnDraggableCup(type);
        });

        this.objectLayer.getChildren().add(cup.getView());
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

    public void createMilkDispenser(MilkType type) {
        ImageView view = type.getView();

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
                type.openMilkMenu(pane);
            }

            view.setLayoutX(startX);
            view.setLayoutY(startY);
        });
    }

    private void spawnDraggableCup(CupType type) {
        CupState newCupState = new CupState(type);
        ImageView cupView = newCupState.getView();

        this.objectLayer.getChildren().add(cupView);

        cupView.setOnMouseDragged(e -> {
            cupView.setLayoutX(e.getSceneX() - 30);
            cupView.setLayoutY(e.getSceneY() - 30);

            // Pohár dáme na vrch, aby sa plynule posúval nad tlačidlami
            cupView.toFront();
        });

        cupView.setOnMouseReleased(e -> {
            if (espressoMachine.getView().getBoundsInParent().intersects(cupView.getBoundsInParent())) {

                if (newCupState.getCupType() == CupType.PAPER) {
                    cupView.setLayoutX(espressoMachine.getX() + 130);
                } else {
                    cupView.setLayoutX(espressoMachine.getX() + 115);
                }
                cupView.setLayoutY(espressoMachine.getY() + 150);

                espressoMachine.placeCup(newCupState);
                System.out.println("✅ Pohár typ " + type + " bol vložený do kávovaru!");

            } else if (milkSteamer.getView().getBoundsInParent().intersects(cupView.getBoundsInParent())) {
                if (newCupState.getCupType() == CupType.PAPER) {
                    cupView.setLayoutX(milkSteamer.getX() + 130);
                } else {
                    cupView.setLayoutX(milkSteamer.getX() + 115);
                }
                cupView.setLayoutY(milkSteamer.getY() + 150);

                milkSteamer.placeCup(newCupState);
                System.out.println("✅ Pohár typ " + type + " bol vložený pod napenovač!");

            } else if (this.acceptButtonView.getBoundsInParent().intersects(cupView.getBoundsInParent())) {
                // Vyhodnotenie po presunutí a pustení na acceptButton
                OrderInspection.inspectOrder(Recipe.FLAT_WHITE, newCupState);
                this.objectLayer.getChildren().remove(cupView);
                System.out.println("📋 Objednávka bola odovzdaná!");

            } else {
                this.objectLayer.getChildren().remove(cupView);
                System.out.println("🗑️ Pohár bol zahodený mimo stanice.");
            }
        });
    }
}
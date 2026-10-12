import controllers.OrderInspection;
import controllers.OrderManager;
import entities.CupState;
import entities.Customer;
import entities.dispensers.CoffeeDispenser;
import entities.dispensers.CupDispenser;
import entities.dispensers.MilkDispenser;
import entities.enums.CoffeeBeanType;
import entities.enums.CupType;
import entities.enums.MilkType;
import entities.enums.Recipe;
import entities.stations.EspressoMachine;
import entities.stations.GrinderStation;
import entities.stations.MilkSteamer;
import entities.ui.BackgroundView;
import entities.ui.TicketUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SimulatorLoop extends Application {
    private Pane pane;
    private Pane objectLayer;
    private Scene scene;
    private Button acceptButtonView;
    private BackgroundView background;

    private EspressoMachine espressoMachine;
    private GrinderStation grinderStation;
    private MilkSteamer milkSteamer;

    @Override
    public void start(Stage stage) {
        setupUI();
        initStations();
        initCustomerAndOrder();
        initDispensers();

        stage.setTitle("Moja hra");
        stage.setScene(this.scene);
        stage.show();
    }

    private void setupUI() {
        this.pane = new Pane();
        this.pane.setPrefSize(1000, 800);
        this.scene = new Scene(this.pane);

        this.background = new BackgroundView();
        background.attachTo(this.pane);

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
    }

    private void initStations() {
        this.espressoMachine = new EspressoMachine(this.pane);
        this.grinderStation = new GrinderStation();
        this.grinderStation.getView().setUserData(this.grinderStation);
        this.milkSteamer = new MilkSteamer();

        this.objectLayer.getChildren().addAll(
                this.espressoMachine.getView(),
                this.grinderStation.getView(),
                this.milkSteamer.getView()
        );
    }

    private void initCustomerAndOrder() {
        Customer jozo = new Customer("jozo", Recipe.ESPRESSO);
        CupState cup = new CupState(CupType.CERAMIC);
        TicketUI ticketUI = new TicketUI(this.pane, jozo.getCoffee());
        OrderManager.getInstance().newOrder();


        this.objectLayer.getChildren().add(ticketUI.getView());
    }

    private void initDispensers() {
        CupDispenser cupDispenser = new CupDispenser(this.objectLayer, this.acceptButtonView, this.background.getDesk());
        CoffeeDispenser coffeeDispenser = new CoffeeDispenser(this.objectLayer);
        MilkDispenser milkDispenser = new MilkDispenser(this.objectLayer);

        for (CupType type : CupType.values()) {
            cupDispenser.createCupDispenser(type);
        }

        for (CoffeeBeanType type : CoffeeBeanType.values()) {
            coffeeDispenser.createCoffeeDispenser(type);
        }

        for (MilkType type : MilkType.values()) {
            milkDispenser.createMilkDispenser(type);
        }
    }
}
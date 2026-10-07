import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TicketUI extends GUIObject {
    private final Pane pane;
    private Recipe order;

    public TicketUI(Pane pane, Recipe order) {
        this.pane = pane;
        this.setView("images/ticket.png");
        this.order = order;
        this.setX(20);
        this.setY(655);
        this.setHeight(120);
        this.setWidth(80);
    }

    public void openTicket() {
        VBox popup = new VBox(10);
        popup.setPadding(new Insets(10, 15, 15, 15));
        popup.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px;");
        popup.setAlignment(Pos.TOP_CENTER);


        BorderPane topBar = new BorderPane();

        Label x = new Label("X");
        x.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-cursor: hand; -fx-padding: 2px 6px;");
        
        x.setOnMouseClicked(e -> this.pane.getChildren().remove(popup));


        topBar.setRight(x);
        popup.getChildren().add(topBar);


        Label header = new Label("Order: " + order.getDisplayName());
        header.setStyle("-fx-text-fill: #000000; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 0 0 8px 0;");
        popup.getChildren().add(header);

        if (order.getRequiredMilk() != null) {
            Label milk = new Label("- " + order.getRequiredMilk().toString());
            popup.getChildren().add(milk);
        }

        Label beand = new Label("- " + order.getRequiredBean().getName());
        Label cup = new Label("- " + order.getRequiredCup().toString());

        popup.getChildren().addAll(beand, cup);

        popup.setMinWidth(300);
        popup.setMinHeight(400);
        popup.setLayoutX(200);
        popup.setLayoutY(150);

        this.pane.getChildren().add(popup);
    }
}

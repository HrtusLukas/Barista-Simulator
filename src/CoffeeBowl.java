import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CoffeeBowl extends GUIObject{
    private int grams;
    private CoffeeBeanType type;
    private GrinderStation grinderStation;

    public CoffeeBowl(int grams, CoffeeBeanType type, Pane pane) {
        this.grams = grams;
        this.type = type;
        this.setView("images/coffeeBowl.png");
        this.setX(430);
        this.setY(450);
        this.setHeight(125);
        this.setWidth(125);

        this.view.setOnMouseDragged(e -> {
            this.view.setLayoutX(e.getSceneX() - 30);
            this.view.setLayoutY(e.getSceneY() - 30);
        });



        this.view.setOnMouseReleased(e -> {
            GrinderStation foundGrinder = GrinderStation.getInstance();

            if (foundGrinder != null && foundGrinder.getView().getBoundsInParent().intersects(this.view.getBoundsInParent())) {
                System.out.println("grind");
                foundGrinder.selectBean(type, grams);
                pane.getChildren().remove(this.view);
            }
        });
    }

    public int getGrams() {
        return grams;
    }

    public CoffeeBeanType getType() {
        return type;
    }
}

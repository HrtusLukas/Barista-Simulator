import javafx.application.Platform;
import javafx.scene.layout.Pane;

public class Portafilter extends GUIObject {
    private CoffeeBeanType loadedBean;
    private boolean isTamped;
    private int coffeeGrams;
    private boolean isDirty;
    private Pane pane;

    private final int START_X = 360;
    private final int START_Y = 530;

    private double mouseAnchorX;
    private double mouseAnchorY;

    private static Portafilter instance;

    public Portafilter(Pane pane) {
        this.loadedBean = null;
        this.coffeeGrams = 0;
        this.isDirty = false;
        this.isTamped = false;
        this.pane = pane;
        instance = this;

        this.setView("images/portafilter.png");
        this.setHeight(125);
        this.setWidth(125);

        this.setX(START_X);
        this.setY(START_Y);

        this.view.setOnMousePressed(e -> {
            mouseAnchorX = e.getX();
            mouseAnchorY = e.getY();
        });

        this.view.setOnMouseDragged(e -> {
            this.setX((int) (e.getSceneX() - mouseAnchorX));
            this.setY((int) (e.getSceneY() - mouseAnchorY));
        });

        this.view.setOnMouseReleased(e -> {
            GrinderStation foundGrinder = GrinderStation.getInstance();

            if (foundGrinder != null && foundGrinder.getView().getBoundsInParent().intersects(this.view.getBoundsInParent())) {
                System.out.println("inserted");

                if (this.view.getParent() != null) {
                    ((Pane) this.view.getParent()).getChildren().remove(this.view);
                }

                foundGrinder.grind(this);
            }

            if (this.isTamped) {
                EspressoMachine espressoMachine = EspressoMachine.getInstance();
                if (espressoMachine.getView().getBoundsInParent().intersects(this.view.getBoundsInParent())) {
                    espressoMachine.insertFilter(this);
                    System.out.println("filter");
                    pane.getChildren().remove(this.view);
                }
            }
        });
    }

    public static Portafilter getInstance() {
        return instance;
    }

    public void loadBeans(CoffeeBeanType beans, int grams) {
        this.loadedBean = beans;
        this.coffeeGrams = grams;
        System.out.println("loaded");
        this.tamp();

        Platform.runLater(() -> {
            this.setView("images/portafilterfilled.png");

            this.setX(400);
            this.setY(530);

            if (this.view.getParent() == null && !pane.getChildren().contains(this.view)) {
                pane.getChildren().add(this.view);
            }

            this.view.setVisible(true);
            this.view.toFront();
        });
    }

    public void tamp() {
        if (this.loadedBean != null) {
            this.isTamped = true;
            System.out.println("tamped!");
        }
    }

    public void clean() {
        this.loadedBean = null;
        this.coffeeGrams = 0;
        this.isDirty = false;
        this.isTamped = false;
        this.setView("images/portafilter.png");
    }

    public CoffeeBeanType getLoadedBean() { return loadedBean; }
    public int getCoffeeGrams() { return coffeeGrams; }
    public boolean isTamped() { return isTamped; }
    public boolean isDirty() { return isDirty; }
    public void setDirty(boolean dirty) { isDirty = dirty; }
}
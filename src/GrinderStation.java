import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class GrinderStation extends GUIObject{
    private CoffeeBeanType beans;
    private int grams;
    private static GrinderStation instance;

    public GrinderStation() {
        this.setView("images/grinder.png");
        this.setX(180);
        this.setY(430);
        this.setHeight(225);
        this.setWidth(225);
        instance = this;
    }

    public void selectBean(CoffeeBeanType beanType, int grams) {
        this.beans = beanType;
        this.grams = grams;
    }

    public void grind(Portafilter portafilter) {
        if (portafilter != null & !portafilter.isDirty() & this.beans != null) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));

            pause.setOnFinished(e -> {
                portafilter.loadBeans(beans, grams);
            });

            pause.play();

        }
    }

    public static GrinderStation getInstance() {
        return instance;
    }
}

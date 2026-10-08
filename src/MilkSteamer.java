import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class MilkSteamer extends GUIObject{
    private MilkType milk;
    private int ml;
    private CupState cup;
    private static MilkSteamer instance;

    public MilkSteamer() {
        this.setView("images/steamer.png");
        this.setX(450);
        this.setY(430);
        this.setHeight(225);
        this.setWidth(225);
        instance = this;
    }

    public static MilkSteamer getInstance() {
        return instance;
    }

    public void AddMilk(MilkType milk, int ml) {
        this.milk = milk;
        this.ml = ml;
    }

    public void placeCup(CupState cup) {
        this.cup = cup;
    }

    public void steam() {
        if (this.cup != null) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));

            pause.setOnFinished(e -> {
                System.out.println("Ubehlo 3 sekúnd!");
                this.cup.addMilk(this.milk, this.ml, true);
                System.out.println("steam");
                this.cup.setX(420);
                this.cup.setY(600);
            });

            pause.play();
        }
    }
}
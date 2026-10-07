import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum MilkType {
    COW_MILK("cowMilk", false, "images/cowMilk.png"),
    OAT_MILK("oatMilk", true, "images/oatMilk.png"),
    SOY_MILK("soyMilk", true, "images/soyMilk.png");

    private final String name;
    private final boolean isVegan;
    private final String path;

    MilkType(String name, boolean isVegan, String path) {
        this.name = name;
        this.isVegan = isVegan;
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public boolean isVegan() {
        return this.isVegan;
    }

    public ImageView getView() {
        return new ImageView(new Image(getClass().getResourceAsStream(this.path)));
    }
}

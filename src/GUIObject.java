import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GUIObject {
    protected ImageView view;
    protected int x;
    protected int y;
    protected int height;
    protected int width;

    public ImageView getView() {
        return view;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setView(String cesta) {
        if (this.view == null) {
            this.view = new ImageView(new Image(getClass().getResourceAsStream(cesta)));
        } else {
            this.view.setImage(new Image(getClass().getResourceAsStream(cesta)));
        }

    }

    public void setX(int x) {
        this.x = x;
        this.view.setLayoutX(this.x);
    }

    public void setY(int y) {
        this.y = y;
        this.view.setLayoutY(this.y);
    }

    public void setHeight(int height) {
        this.height = height;
        this.view.setFitHeight(this.height);
    }

    public void setWidth(int width) {
        this.width = width;
        this.view.setFitWidth(this.width);
    }
}

public class MilkSteamer extends GUIObject{

    public MilkSteamer() {
        this.setView("images/steamer.png");
        this.setX(450);
        this.setY(430);
        this.setHeight(225);
        this.setWidth(225);
    }

    public void AddMilk(MilkType milk, CupState cup, int ml) {
        if (cup == null) {
            return;
        }

        cup.addMilk(milk, ml, true);
    }
}
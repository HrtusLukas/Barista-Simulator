public class MilkSteamer {

    public void AddMilk(MilkType milk, CupState cup, int ml) {
        if (cup == null) {
            return;
        }

        cup.addMilk(milk, ml, true);
    }
}
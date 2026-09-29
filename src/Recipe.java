public class Recipe {
    private final CoffeeBeanType coffeeBeanType;
    private final MilkType milkType;
    private final CupType cupType;
    private final int requiredMilkML;
    private final int requiredWaterML;

    public Recipe(CoffeeBeanType coffeeBeanType, MilkType milkType, CupType cupType, int requiredMilkML, int requiredWaterML) {
        this.coffeeBeanType = coffeeBeanType;
        this.milkType = milkType;
        this.cupType = cupType;
        this.requiredMilkML = requiredMilkML;
        this.requiredWaterML = requiredWaterML;
    }

    public CoffeeBeanType getCoffeeBeanType() {
        return coffeeBeanType;
    }

    public MilkType getMilkType() {
        return milkType;
    }

    public CupType getCupType() {
        return cupType;
    }

    public int getRequiredMilkML() {
        return requiredMilkML;
    }

    public int getRequiredWaterML() {
        return requiredWaterML;
    }
}

public class CupState {
    private final CupType cupType;
    private CoffeeBeanType usedBeanType;
    private MilkType usedMilkType;

    private int addedCoffeeGrams;
    private int addedWaterML;
    private int addedMilkML;
    private boolean isSteamed;

    public CupState(CupType cupType) {
        this.cupType = cupType;
        this.addedCoffeeGrams = 0;
        this.addedWaterML = 0;
        this.addedMilkML = 0;
        this.isSteamed = false;
    }

    public void addCoffee(CoffeeBeanType usedBeanType, int coffeeGrams, int waterML) {
        this.usedBeanType = usedBeanType;
        this.addedCoffeeGrams += coffeeGrams;
        this.addedWaterML += waterML;
    }

    public void addMilk(MilkType usedMilkType, int milkML, boolean isSteamed) {
        this.usedMilkType = usedMilkType;
        this.addedMilkML += milkML;
        this.isSteamed = isSteamed;
    }

    public CupType getCupType() {
        return cupType;
    }

    public CoffeeBeanType getUsedBeanType() {
        return usedBeanType;
    }

    public MilkType getUsedMilkType() {
        return usedMilkType;
    }

    public int getAddedCoffeeGrams() {
        return addedCoffeeGrams;
    }

    public int getAddedWaterML() {
        return addedWaterML;
    }

    public int getAddedMilkML() {
        return addedMilkML;
    }

    public boolean isSteamed() {
        return isSteamed;
    }

    public int getTotalVolumeML() {
        return addedWaterML + addedMilkML;
    }
}
public class CupState extends GUIObject{
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

        if (cupType == CupType.PAPER){
            this.setView("images/paperCup.png");
            this.setX(60);
            this.setY(500);
            this.setHeight(90);
            this.setWidth(60);
        } else {
            this.setView("images/ceramicCup.png");
            this.setX(140);
            this.setY(490);
            this.setHeight(120);
            this.setWidth(100);
        }



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
public enum Recipe {

    // Názov, Bean, Milk, Cup, MilkML, WaterML, CoffeeGrams, NeedsSteamedMilk, NeedsTamping
    ESPRESSO("Espresso", CoffeeBeanType.ARABICA, null, CupType.CERAMIC, 0, 30, 9, false, true),
    DOUBLE_ESPRESSO("Double Espresso", CoffeeBeanType.ARABICA, null, CupType.CERAMIC, 0, 60, 18, false, true),
    CAPPUCCINO("Cappuccino", CoffeeBeanType.ARABICA, MilkType.COW_MILK, CupType.CERAMIC, 100, 30, 18, true, true),
    FLAT_WHITE("Flat White", CoffeeBeanType.ARABICA, MilkType.COW_MILK, CupType.CERAMIC, 120, 60, 18, true, true);

    private final String displayName;
    private final CoffeeBeanType requiredBean;
    private final MilkType requiredMilk;
    private final CupType requiredCup;
    private final int requiredMilkML;
    private final int requiredWaterML;
    private final int requiredCoffeeGrams;
    private final boolean requiresSteamedMilk;
    private final boolean requiresTamping;

    Recipe(String displayName, CoffeeBeanType requiredBean, MilkType requiredMilk, CupType requiredCup,
           int requiredMilkML, int requiredWaterML, int requiredCoffeeGrams,
           boolean requiresSteamedMilk, boolean requiresTamping) {
        this.displayName = displayName;
        this.requiredBean = requiredBean;
        this.requiredMilk = requiredMilk;
        this.requiredCup = requiredCup;
        this.requiredMilkML = requiredMilkML;
        this.requiredWaterML = requiredWaterML;
        this.requiredCoffeeGrams = requiredCoffeeGrams;
        this.requiresSteamedMilk = requiresSteamedMilk;
        this.requiresTamping = requiresTamping;
    }

    public String getDisplayName() { return displayName; }
    public CoffeeBeanType getRequiredBean() { return requiredBean; }
    public MilkType getRequiredMilk() { return requiredMilk; }
    public CupType getRequiredCup() { return requiredCup; }
    public int getRequiredMilkML() { return requiredMilkML; }
    public int getRequiredWaterML() { return requiredWaterML; }
    public int getRequiredCoffeeGrams() { return requiredCoffeeGrams; }
    public boolean isRequiresSteamedMilk() { return requiresSteamedMilk; }
    public boolean isRequiresTamping() { return requiresTamping; }
}
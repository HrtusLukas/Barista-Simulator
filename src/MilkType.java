public enum MilkType {
    COW_MILK("cowMilk", false),
    OAT_MILK("oatMilk", true),
    SOY_MILK("soyMilk", true);

    private final String name;
    private final boolean isVegan;

    MilkType(String name, boolean isVegan) {
        this.name = name;
        this.isVegan = isVegan;
    }

    public String getName() {
        return this.name;
    }

    public boolean isVegan() {
        return this.isVegan;
    }
}

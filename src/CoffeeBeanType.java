public enum CoffeeBeanType {
    ARABICA("arabica", false),
    ROBUSTA("robusta", false),
    DECAF("decafeine", true);

    private final String name;
    private final boolean isDecaf;

    CoffeeBeanType(String name, boolean isDecaf) {
        this.name = name;
        this.isDecaf = isDecaf;
    }

    public boolean isDecaf() {
        return this.isDecaf;
    }

    public String getName() {
        return this.name;
    }
}

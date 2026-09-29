public class Portafilter {
    private CoffeeBeanType loadedBean;
    private boolean isTamped;
    private int coffeeGrams;
    private boolean isDirty;

    public Portafilter() {
        this.loadedBean = null;
        this.coffeeGrams = 0;
        this.isDirty = false;
        this.isTamped = false;
    }

    public void loadBeans(CoffeeBeanType beans, int grams) {
        this.loadedBean = beans;
        this.coffeeGrams = grams;
        System.out.println("loaded");
    }

    public void tamp() {
        if (this.loadedBean != null) {
            this.isTamped = true;
            System.out.println("tamped!");
        }
    }

    public void clean() {
        this.loadedBean = null;
        this.coffeeGrams = 0;
        this.isDirty = false;
        this.isTamped = false;
    }

    public CoffeeBeanType getLoadedBean() { return loadedBean; }
    public int getCoffeeGrams() { return coffeeGrams; }
    public boolean isTamped() { return isTamped; }
    public boolean isDirty() { return isDirty; }
    public void setDirty(boolean dirty) { isDirty = dirty; }
}

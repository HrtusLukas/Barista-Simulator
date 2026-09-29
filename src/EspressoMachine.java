public class EspressoMachine {
    private Portafilter insertedFilter;
    private CupState cup;

    public void insertFilter(Portafilter insertedFilter) {
        this.insertedFilter = insertedFilter;
    }

    public void placeCup (CupState cup) {
        this.cup = cup;
    }

    public void brew(int ml) {
        if (this.cup != null && this.insertedFilter != null && this.insertedFilter.getLoadedBean() != null) {
            int grams = this.insertedFilter.getCoffeeGrams();
            this.cup.addCoffee(this.insertedFilter.getLoadedBean(), grams, ml);
            this.insertedFilter.setDirty(true);
        }
    }

    public Portafilter removeFilter() {
        Portafilter filterToReturn = this.insertedFilter;
        this.insertedFilter = null;
        return filterToReturn;
    }
}

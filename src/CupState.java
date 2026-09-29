public class CupState {
    private final CupType cupType;
    private CoffeeBeanType usedBeanType;
    private MilkType usedMilkType;
    private float cupVolumeML;

    public CupState(CupType cupType) {
        this.cupType = cupType;
        this.cupVolumeML = 0.0f;
    }

    public void AddCoffee(CoffeeBeanType usedBeanType, int waterML) {
       this.usedBeanType = usedBeanType;
       this.cupVolumeML += waterML;
    }

    public void AddMilk(MilkType usedMilkType, int milkML) {
        this.usedMilkType = usedMilkType;
        this.cupVolumeML += milkML;
    }

    public CupType getCupType() {
        return this.cupType;
    }

    public CoffeeBeanType getUsedBeanType() {
        return this.usedBeanType;
    }

    public MilkType getUsedMilkType() {
        return this.usedMilkType;
    }

    public float getCupVolumeML() {
        return this.cupVolumeML;
    }
}

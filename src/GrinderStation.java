public class GrinderStation {
    private CoffeeBeanType beans;

    public void selectBean(CoffeeBeanType beanType) {
        this.beans = beanType;
    }

    public void grind(Portafilter portafilter, int grams) {
        if (portafilter != null & !portafilter.isDirty()) {
            portafilter.loadBeans(beans, grams);
        }
    }
}

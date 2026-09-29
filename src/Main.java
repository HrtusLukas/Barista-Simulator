//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Customer jozo = new Customer("jozo", Recipe.ESPRESSO);

    CupState cup = new CupState(CupType.CERAMIC);
    GrinderStation gs = new GrinderStation();
    Portafilter pf = new Portafilter();
    gs.selectBean(CoffeeBeanType.ARABICA);
    gs.grind(pf, 9);

    EspressoMachine em = new EspressoMachine();
    em.insertFilter(pf);
    em.placeCup(cup);
    em.brew(30);

    OrderInspection.inspectOrder(jozo.getCoffee(), cup);
}

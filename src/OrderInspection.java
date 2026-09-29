public class OrderInspection {

    private static final int WATER_TOLERANCE_ML = 5;
    private static final int GRAMS_TOLERANCE = 1;

    public static boolean inspectOrder(Recipe order, CupState cup) {
        if (cup == null) {
            System.out.println("❌ FAILED: Pohár je null!");
            return false;
        }

        if (order.getRequiredCup() != cup.getCupType()) {
            System.out.println("❌ FAILED: Zlý pohár! Vyžadovaný: " + order.getRequiredCup() + ", Použitý: " + cup.getCupType());
            return false;
        }

        if (order.getRequiredBean() != cup.getUsedBeanType()) {
            System.out.println("❌ FAILED: Zlé zrná! Vyžadované: " + order.getRequiredBean() + ", Použité: " + cup.getUsedBeanType());
            return false;
        }

        int gramsDiff = Math.abs(order.getRequiredCoffeeGrams() - cup.getAddedCoffeeGrams());
        if (gramsDiff > GRAMS_TOLERANCE) {
            System.out.println("❌ FAILED: Zlá gramáž! Vyžadovaná: " + order.getRequiredCoffeeGrams() + "g, Použitá: " + cup.getAddedCoffeeGrams() + "g");
            return false;
        }

        int waterDiff = Math.abs(order.getRequiredWaterML() - cup.getAddedWaterML());
        if (waterDiff > WATER_TOLERANCE_ML) {
            System.out.println("❌ FAILED: Zlá voda! Vyžadovaná: " + order.getRequiredWaterML() + "ml, Použitá: " + cup.getAddedWaterML() + "ml");
            return false;
        }

        if (order.getRequiredMilkML() > 0) {
            if (order.getRequiredMilk() != cup.getUsedMilkType()) {
                System.out.println("❌ FAILED: Zlé mlieko!");
                return false;
            }
            if (order.isRequiresSteamedMilk() != cup.isSteamed()) {
                System.out.println("❌ FAILED: Mlieko malo/nemalo byť spenené!");
                return false;
            }
        } else {
            if (cup.getAddedMilkML() > 0) {
                System.out.println("❌ FAILED: Pridávanie mlieka do kávy bez mlieka!");
                return false;
            }
        }

        System.out.println("✅ PASSED: Káva je v poriadku!");
        return true;
    }
}
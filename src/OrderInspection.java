public class OrderInspection {
    public static boolean inspectOrder(Recipe order, CupState cup) {
        if(!order.getCoffeeBeanType().equals(cup.getUsedBeanType())) {
            return false;
        }

        if (!order.getMilkType().equals(cup.getUsedMilkType())) {
            return false;
        }

        if (!order.getCupType().equals(cup.getCupType())) {
            return false;
        }

        return true;
    }
}

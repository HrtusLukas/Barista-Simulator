public class Customer {
    private String name;
    private Recipe coffee;

    public Customer(String name, Recipe coffee) {
        this.name = name;
        this.coffee = coffee;
    }

    public Recipe getCoffee() {
        return coffee;
    }

    public String getName() {
        return name;
    }
}

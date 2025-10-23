package org.softwaretechnologies;

// TODO: 09.12.2024 Создайте класс MilkCoffee, поддерживающий интерфейс CofeIntrface
//  к стоимости базового напитка добавьте 10.
//  к описанию добавьте " + milk"

public class MilkCoffee implements CoffeeInterface {
    public CoffeeInterface cofe;
    public MilkCoffee(CoffeeInterface c) {
        this.cofe = c;
    }
    @Override
    public int getCost() {
        return cofe.getCost() + 10;
    }
    @Override
    public String description() {
        return cofe.description() + " + milk";
    }
}
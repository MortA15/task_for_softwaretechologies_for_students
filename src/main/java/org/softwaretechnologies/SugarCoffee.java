package org.softwaretechnologies;


// TODO: 09.12.2024 /**
//     Создайте класс SugarCoffee, поддерживающий интерфейс CofeIntrface
//     к стоимости базового напитка добавьте 20.
//     к описанию добавьте " + sugar"
//     */

public class SugarCoffee implements CoffeeInterface {
    public CoffeeInterface cofe;
    public SugarCoffee(CoffeeInterface c) {
        this.cofe = c;
    }
    @Override
    public int getCost() {
        return cofe.getCost() + 20;
    }
    @Override
    public String description() {
        return cofe.description() + " + sugar";
    }
}
package org.softwaretechnologies.animals;

public abstract class Animal implements Comparable<Animal> {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Создайте три класса животных: кошка, корова, собака.
     * Кошка должна издавать звук: "meow"
     * Корова должна издавать звук: "moo"
     * Собака должна издавать звук: "woof"
     * @return Метод должен возвращать строку, соответсвующую издаваемого животным звука.
     */
    public abstract String sound();

    @Override
    public int compareTo(Animal o) {
        return name.compareTo(o.name);
    }
}

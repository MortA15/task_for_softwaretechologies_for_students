package org.softwaretechnologies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

public class Money {
    private final MoneyType type;
    private final BigDecimal amount;

    public Money(MoneyType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Money равны, если одинаковый тип валют и одинаковое число денег до 4 знака после запятой.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @param o объект для сравнения
     * @return true - равно, false - иначе
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //если они равны
        if (o == null || getClass() != o.getClass()) return false; //если о пустой или классы разные

        Money money = (Money) o;

        // Сравнение типов валют (с обработкой null)
        if (!Objects.equals(type, money.type)) return false;

        // Оба amount null - равны
        if (amount == null && money.amount == null) return true;

        // Один из amount null - не равны
        if (amount == null || money.amount == null) return false;

        // Оба amount не null - сравниваем с округлением до 4 знаков
        BigDecimal thisScaled = amount.setScale(4, RoundingMode.HALF_UP);
        BigDecimal otherScaled = money.amount.setScale(4, RoundingMode.HALF_UP);

        return thisScaled.compareTo(otherScaled) == 0;
    }

    /**
     * Формула:
     * (Если amount null 10000, иначе количество денег окрукленные до 4х знаков * 10000) + :
     * если USD , то 1
     * если EURO, то 2
     * если RUB, то 3
     * если KRONA, то 4
     * если null, то 5
     * Если amount округленный до 4х знаков * 10000 >= (Integer.MaxValue - 5), то хеш равен Integer.MaxValue
     * Округление по правилу: если >= 5, то в большую сторону, иначе - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @return хеш код по указанной формуле
     */
    @Override
    public int hashCode() {
        // Если amount равен null, возвращаем фиксированное значение 10000
        if (this.amount == null) {
            return 10000;
        }

        // Округляем сумму до 4 знаков после запятой
        BigDecimal scaledAmount = this.amount.setScale(4, RoundingMode.HALF_UP);
        // Умножаем округленную сумму на 10000 для преобразования в целое число
        BigDecimal multipliedAmount = scaledAmount.multiply(BigDecimal.valueOf(10_000));

        // Проверяем, не превышает ли умноженная сумма максимальное значение int минус 5
        if (multipliedAmount.compareTo(BigDecimal.valueOf(MAX_VALUE - 5)) >= 0) {
            return MAX_VALUE;
        }

        // Преобразуем умноженную сумму в целое число
        int baseHash = multipliedAmount.intValue();
        // Получаем числовой код для типа валюты
        int currencyCode = getCurrencyCode();

        // Суммируем базовый хеш и код валюты для получения итогового хеш-кода
        return baseHash + currencyCode;
    }

    private int getCurrencyCode() {
        // Если тип валюты не указан, возвращаем код 5
        if (type == null) {
            return 5;
        }

        // Возвращаем соответствующий код для каждого типа валюты
        return switch (type) {
            case USD -> 1;
            case EURO -> 2;
            case RUB -> 3;
            case KRONA -> 4;
            default -> 5;
        };
    }

    /**
     * Верните строку в формате
     * Тип_ВАЛЮТЫ: количество.XXXX
     * Тип_валюты: USD, EURO, RUB или KRONA
     * количество.XXXX - округленный amount до 4х знаков.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     * <p>
     * Если тип валюты null, то вернуть:
     * null: количество.XXXX
     * Если количество денег null, то вернуть:
     * Тип_ВАЛЮТЫ: null
     * Если и то и то null, то вернуть:
     * null: null
     *
     * @return приведение к строке по указанному формату.
     */
    @Override
    public String toString() {
        // Формируем строку для типа валюты: если null, то "null", иначе название валюты
        String typeString = (this.type == null ? "null" : this.type.toString());

        // Формируем строку для суммы: если null, то "null", иначе округленная до 4 знаков сумма
        String amountString = (this.amount == null ? "null" :
                this.amount.setScale(4, RoundingMode.HALF_UP).toString());

        // Собираем итоговую строку в формате "ВАЛЮТА: сумма"
        return typeString + ": " + amountString;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public MoneyType getType() {
        return type;
    }

    public static void main(String[] args) {
        Money money = new Money(MoneyType.EURO, BigDecimal.valueOf(10.00012));
        Money money1 = new Money(MoneyType.USD, BigDecimal.valueOf(10.5000));
        System.out.println(money1.toString());
        System.out.println(money1.hashCode());
        System.out.println(money.equals(money1));
    }
}

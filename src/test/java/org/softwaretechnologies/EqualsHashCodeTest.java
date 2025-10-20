package org.softwaretechnologies;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


@SuppressWarnings("ALL")
public class EqualsHashCodeTest {
    static class A {
        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return 33;
        }
    } //сравниваем то что ввели с родителем А. equals правильный так как будет возращать результат сравнения объектов с одинаковм хеш кодом (33)

    static class B {
        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            Random random = new Random();
            return random.nextInt();
        }
    }//не подходит под условие из-за рандомного хеш кода

    static class C {
        int a;

        @Override
        public boolean equals(Object o) {
            return a == ((C) o).a;
        } //не подходит потому что не факт что объект о класса С

        @Override
        public int hashCode() {
            return 77;
        }
    }

    static class D {
        int a;

        @Override
        public boolean equals(Object obj) {
            if(obj == null) return false;// неправильно из-за этого (а если первый объект (a) тоже null (пустой))
            return obj instanceof D; //возвращает является ли obj объектом класса D
        }

        @Override
        public int hashCode() {
            return a;
        }
    }

    static class E {
        int a;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true; //если они равны
            if (o == null || getClass() != o.getClass()) return false; // если о пустой или разные классы

            E f = (E) o;

            return a == f.a; //выводим результат сравниваем значение объектов
        }

        @Override
        public int hashCode() {
            return a;
        }
    }

    @Test
    public void validate() {
        boolean a = EqualsVerifier.simple().forClass(A.class).report().isSuccessful();
        boolean b = EqualsVerifier.simple().forClass(B.class).report().isSuccessful();
        boolean c = EqualsVerifier.simple().forClass(C.class).report().isSuccessful();
        boolean d = EqualsVerifier.simple().forClass(D.class).report().isSuccessful();
        boolean e = EqualsVerifier.simple().forClass(E.class).report().isSuccessful();

        /*
         * Какие классы содержат верную пару equals hashCode.
         * Невалидным может быть только один из методов.
         * Встатьте нужные выражения assertTrue или assertFalse для переменных a b c d e
         * Пример:
         * assertTrue(c);
         * assertFalse(a);
         */

        // TODO: Встатьте нужные выражения assertTrue или assertFalse для переменных a b c d e чтобы тест проходил
        // ↓↓↓↓ КОД ДЛЯ ЗАМЕНЫ ↓↓↓↓
        assertTrue(a);
        assertFalse(b);
        assertFalse(c);
        assertFalse(d);
        assertTrue(e);
        // ↑↑↑↑ КОД ДЛЯ ЗАМЕНЫ ↑↑↑↑

    }
}

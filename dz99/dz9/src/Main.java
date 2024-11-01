// Базовый интерфейс напитка
interface Beverage {
    String getDescription();
    double getCost();
}

// Класс Эспрессо
class Espresso implements Beverage {
    @Override
    public String getDescription() {
        return "Эспрессо";
    }

    @Override
    public double getCost() {
        return 150.0;
    }
}

// Класс Чай
class Tea implements Beverage {
    @Override
    public String getDescription() {
        return "Чай";
    }

    @Override
    public double getCost() {
        return 100.0;
    }
}

// Класс Латте
class Latte implements Beverage {
    @Override
    public String getDescription() {
        return "Латте";
    }

    @Override
    public double getCost() {
        return 200.0;
    }
}

// Абстрактный декоратор напитков
abstract class BeverageDecorator implements Beverage {
    protected Beverage beverage;

    public BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription();
    }

    @Override
    public double getCost() {
        return beverage.getCost();
    }
}

// Декоратор для добавления молока
class Milk extends BeverageDecorator {
    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Молоко";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 30.0;
    }
}

// Декоратор для добавления сахара
class Sugar extends BeverageDecorator {
    public Sugar(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Сахар";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 10.0;
    }
}

// Декоратор для добавления взбитых сливок
class WhippedCream extends BeverageDecorator {
    public WhippedCream(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Взбитые сливки";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 50.0;
    }
}

// Декоратор для добавления сиропа
class Syrup extends BeverageDecorator {
    public Syrup(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Сироп";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 40.0;
    }
}

// Клиентский код
public class Main {
    public static void main(String[] args) {
        // Создание Эспрессо с добавлением молока и сахара
        Beverage beverage1 = new Espresso();
        beverage1 = new Milk(beverage1);
        beverage1 = new Sugar(beverage1);
        System.out.println(beverage1.getDescription() + " | Итоговая стоимость: " + beverage1.getCost() + " руб.");

        // Создание Латте с добавлением взбитых сливок и сиропа
        Beverage beverage2 = new Latte();
        beverage2 = new WhippedCream(beverage2);
        beverage2 = new Syrup(beverage2);
        System.out.println(beverage2.getDescription() + " | Итоговая стоимость: " + beverage2.getCost() + " руб.");

        // Создание Чая с добавлением сахара и молока
        Beverage beverage3 = new Tea();
        beverage3 = new Sugar(beverage3);
        beverage3 = new Milk(beverage3);
        System.out.println(beverage3.getDescription() + " | Итоговая стоимость: " + beverage3.getCost() + " руб.");
    }
}

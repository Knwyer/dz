import java.util.Scanner;

// Абстрактный класс Beverage
abstract class Beverage {
    // Шаблонный метод
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    // Общие шаги
    private void boilWater() {
        System.out.println("Кипятим воду.");
    }

    private void pourInCup() {
        System.out.println("Наливаем напиток в чашку.");
    }

    // Абстрактные методы, которые будут переопределены в подклассах
    protected abstract void brew();
    protected abstract void addCondiments();

    // Перехватываемый метод
    protected boolean customerWantsCondiments() {
        return true; // по умолчанию добавляем добавки
    }
}

// Класс Tea
class Tea extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Завариваем чайные листья.");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Добавляем лимон.");
    }
}

// Класс Coffee
class Coffee extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Завариваем кофейные зерна.");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Добавляем сахар и молоко.");
    }

    @Override
    protected boolean customerWantsCondiments() {
        System.out.print("Хотите добавить сахар и молоко? (да/нет): ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        return answer.equalsIgnoreCase("да");
    }
}

// Клиентский код
public class Main {
    public static void main(String[] args) {
        Beverage tea = new Tea();
        tea.prepareRecipe();

        System.out.println(); // Разделитель

        Beverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}

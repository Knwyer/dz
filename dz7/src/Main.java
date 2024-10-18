import java.util.Scanner;

// 1. Интерфейс стратегии оплаты
interface IPaymentStrategy {
    void pay(double amount);
}

// 2. Реализации различных стратегий оплаты

// Оплата банковской картой
class CreditCardPaymentStrategy implements IPaymentStrategy {
    public void pay(double amount) {
        System.out.println("Оплата " + amount + " через банковскую карту.");
    }
}

// Оплата через PayPal
class PayPalPaymentStrategy implements IPaymentStrategy {
    public void pay(double amount) {
        System.out.println("Оплата " + amount + " через PayPal.");
    }
}

// Оплата криптовалютой
class CryptoPaymentStrategy implements IPaymentStrategy {
    public void pay(double amount) {
        System.out.println("Оплата " + amount + " через криптовалюту.");
    }
}

// 3. Контекст, который работает со стратегиями оплаты
class PaymentContext {
    private IPaymentStrategy paymentStrategy;

    // Установка стратегии оплаты
    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Выполнение оплаты
    public void processPayment(double amount) {
        if (paymentStrategy != null) {
            paymentStrategy.pay(amount);
        } else {
            System.out.println("Стратегия оплаты не выбрана.");
        }
    }
}

// 4. Главный класс и клиентский код
public class Main {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext(); // Создаем объект контекста
        Scanner scanner = new Scanner(System.in); // Инициализация Scanner

        // Запрашиваем выбор способа оплаты
        System.out.println("Выберите способ оплаты: 1 - Карта, 2 - PayPal, 3 - Криптовалюта");
        String choice = scanner.nextLine();

        // Выбор стратегии оплаты на основе ввода пользователя
        switch (choice) {
            case "1":
                context.setPaymentStrategy(new CreditCardPaymentStrategy());
                break;
            case "2":
                context.setPaymentStrategy(new PayPalPaymentStrategy());
                break;
            case "3":
                context.setPaymentStrategy(new CryptoPaymentStrategy());
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        // Запрос суммы для оплаты
        System.out.println("Введите сумму для оплаты:");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            context.processPayment(amount); // Выполнение оплаты через выбранную стратегию
        } else {
            System.out.println("Некорректный ввод суммы.");
        }

        // Закрываем сканер
        scanner.close();
    }
}

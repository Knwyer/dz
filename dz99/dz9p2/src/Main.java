// Интерфейс для процессора платежей, используемый в интернет-магазине
interface IPaymentProcessor {
    void processPayment(double amount);
}

// Класс процессора платежей через PayPal
class PayPalPaymentProcessor implements IPaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Оплата через PayPal на сумму: " + amount + " руб.");
    }
}

// Сторонний класс платежного сервиса Stripe
class StripePaymentService {
    public void makeTransaction(double totalAmount) {
        System.out.println("Оплата через Stripe на сумму: " + totalAmount + " руб.");
    }
}

// Адаптер для интеграции Stripe в систему интернет-магазина
class StripePaymentAdapter implements IPaymentProcessor {
    private final StripePaymentService stripePaymentService;

    public StripePaymentAdapter(StripePaymentService stripePaymentService) {
        this.stripePaymentService = stripePaymentService;
    }

    @Override
    public void processPayment(double amount) {
        stripePaymentService.makeTransaction(amount);
    }
}

// Дополнительный сторонний класс платежного сервиса Square
class SquarePaymentService {
    public void executePayment(double amount) {
        System.out.println("Оплата через Square на сумму: " + amount + " руб.");
    }
}

// Адаптер для интеграции Square в систему интернет-магазина
class SquarePaymentAdapter implements IPaymentProcessor {
    private final SquarePaymentService squarePaymentService;

    public SquarePaymentAdapter(SquarePaymentService squarePaymentService) {
        this.squarePaymentService = squarePaymentService;
    }

    @Override
    public void processPayment(double amount) {
        squarePaymentService.executePayment(amount);
    }
}

// Клиентский код
public class Main {
    public static void main(String[] args) {
        // Используем PayPal для оплаты
        IPaymentProcessor paypalProcessor = new PayPalPaymentProcessor();
        paypalProcessor.processPayment(1000.0);

        // Используем Stripe для оплаты через адаптер
        StripePaymentService stripeService = new StripePaymentService();
        IPaymentProcessor stripeAdapter = new StripePaymentAdapter(stripeService);
        stripeAdapter.processPayment(2000.0);

        // Используем Square для оплаты через адаптер
        SquarePaymentService squareService = new SquarePaymentService();
        IPaymentProcessor squareAdapter = new SquarePaymentAdapter(squareService);
        squareAdapter.processPayment(1500.0);
    }
}

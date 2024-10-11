import java.io.*;
import java.util.*;

// Паттерн "Одиночка" (Singleton)
class ConfigManager {
    private static ConfigManager instance;
    private static final Object lock = new Object();
    private Map<String, String> settings;

    // Приватный конструктор
    private ConfigManager() {
        settings = new HashMap<>();
    }

    // Метод для получения единственного экземпляра
    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    // Метод для загрузки настроек из файла
    public void loadFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    settings.put(parts[0], parts[1]);
                }
            }
        }
    }

    // Метод для получения настройки по ключу
    public String getSetting(String key) {
        return settings.getOrDefault(key, "Настройка не найдена");
    }

    // Метод для изменения настройки
    public void setSetting(String key, String value) {
        settings.put(key, value);
    }

    // Метод для сохранения настроек в файл
    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : settings.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        }
    }
}

// Паттерн "Строитель" (Builder)
interface ReportBuilder {
    ReportBuilder setHeader(String header);
    ReportBuilder setContent(String content);
    ReportBuilder setFooter(String footer);
    Report build();
}

class Report {
    private String header;
    private String content;
    private String footer;

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    @Override
    public String toString() {
        return header + "\n" + content + "\n" + footer;
    }
}

class PlainTextReportBuilder implements ReportBuilder {
    private Report report;

    public PlainTextReportBuilder() {
        report = new Report();
    }

    public ReportBuilder setHeader(String header) {
        report.setHeader("=== " + header + " ===");
        return this;
    }

    public ReportBuilder setContent(String content) {
        report.setContent(content);
        return this;
    }

    public ReportBuilder setFooter(String footer) {
        report.setFooter("--- " + footer + " ---");
        return this;
    }

    public Report build() {
        return report;
    }
}

class HTMLReportBuilder implements ReportBuilder {
    private Report report;

    public HTMLReportBuilder() {
        report = new Report();
    }

    public ReportBuilder setHeader(String header) {
        report.setHeader("<h1>" + header + "</h1>");
        return this;
    }

    public ReportBuilder setContent(String content) {
        report.setContent("<p>" + content + "</p>");
        return this;
    }

    public ReportBuilder setFooter(String footer) {
        report.setFooter("<footer>" + footer + "</footer>");
        return this;
    }

    public Report build() {
        return report;
    }
}

class ReportDirector {
    public void constructReport(ReportBuilder builder) {
        builder.setHeader("Отчет")
                .setContent("Это содержимое отчета")
                .setFooter("Конец отчета");
    }
}

// Паттерн "Прототип" (Prototype)
interface Prototype<T> {
    T clone();
}

class Product implements Prototype<Product> {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product clone() {
        return new Product(this.name, this.price);
    }

    @Override
    public String toString() {
        return name + ": " + price;
    }
}

class Order implements Prototype<Order> {
    private List<Product> products;
    private double deliveryCost;
    private double discount;
    private String paymentMethod;

    public Order() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void setDeliveryCost(double cost) {
        this.deliveryCost = cost;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setPaymentMethod(String method) {
        this.paymentMethod = method;
    }

    public Order clone() {
        Order clonedOrder = new Order();
        for (Product product : products) {
            clonedOrder.addProduct(product.clone());
        }
        clonedOrder.setDeliveryCost(this.deliveryCost);
        clonedOrder.setDiscount(this.discount);
        clonedOrder.setPaymentMethod(this.paymentMethod);
        return clonedOrder;
    }

    @Override
    public String toString() {
        return "Заказ с товарами: " + products + "\nСтоимость доставки: " + deliveryCost + "\nСкидка: " + discount + "\nМетод оплаты: " + paymentMethod;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        // Тестирование Singleton
        ConfigManager config = ConfigManager.getInstance();
        config.setSetting("URL", "http://example.com");
        config.saveToFile("config.txt");

        // Загружаем настройки из файла
        config.loadFromFile("config.txt");
        System.out.println("Настройка URL: " + config.getSetting("URL"));

        // Тестирование Builder
        ReportDirector director = new ReportDirector();
        ReportBuilder textBuilder = new PlainTextReportBuilder();
        ReportBuilder htmlBuilder = new HTMLReportBuilder();

        director.constructReport(textBuilder);
        System.out.println("\nТекстовый отчет:");
        System.out.println(textBuilder.build());

        director.constructReport(htmlBuilder);
        System.out.println("\nHTML отчет:");
        System.out.println(htmlBuilder.build());

        // Тестирование Prototype
        Order order1 = new Order();
        order1.addProduct(new Product("Ноутбук", 1500));
        order1.addProduct(new Product("Мышь", 50));
        order1.setDeliveryCost(15);
        order1.setDiscount(10);
        order1.setPaymentMethod("Кредитная карта");

        Order clonedOrder = order1.clone();
        System.out.println("\nОригинальный заказ: " + order1);
        System.out.println("\nКлонированный заказ: " + clonedOrder);
    }
}

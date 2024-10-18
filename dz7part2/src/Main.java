import java.util.ArrayList;
import java.util.List;

// 1. Интерфейс IObserver
interface IObserver {
    void update(String currency, double rate);
}

// 2. Интерфейс ISubject
interface ISubject {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}

// 3. Класс субъекта CurrencyExchange
class CurrencyExchange implements ISubject {
    private List<IObserver> observers = new ArrayList<>();
    private String currency;
    private double rate;

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(currency, rate);
        }
    }

    // Метод для обновления курса валют
    public void setCurrencyRate(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
        notifyObservers();  // Уведомляем всех наблюдателей
    }
}

// 4. Реализация различных наблюдателей

// Наблюдатель, который выводит курс валюты в консоль
class ConsoleObserver implements IObserver {
    @Override
    public void update(String currency, double rate) {
        System.out.println("Консольный наблюдатель: Курс " + currency + " обновлен: " + rate);
    }
}

// Наблюдатель, который сохраняет курс в список
class ListObserver implements IObserver {
    private List<String> ratesList = new ArrayList<>();

    @Override
    public void update(String currency, double rate) {
        ratesList.add(currency + ": " + rate);
        System.out.println("Список наблюдателя обновлен: " + currency + " - " + rate);
    }

    public List<String> getRatesList() {
        return ratesList;
    }
}

// Наблюдатель, который отправляет уведомление по электронной почте
class EmailObserver implements IObserver {
    @Override
    public void update(String currency, double rate) {
        System.out.println("Email наблюдатель: Уведомление отправлено: " + currency + " - " + rate);
    }
}

// 5. Клиентский код
public class Main {
    public static void main(String[] args) {
        CurrencyExchange currencyExchange = new CurrencyExchange(); // Создаем субъект

        // Создаем наблюдателей
        ConsoleObserver consoleObserver = new ConsoleObserver();
        ListObserver listObserver = new ListObserver();
        EmailObserver emailObserver = new EmailObserver();

        // Подписываем наблюдателей на изменения
        currencyExchange.addObserver(consoleObserver);
        currencyExchange.addObserver(listObserver);
        currencyExchange.addObserver(emailObserver);

        // Обновляем курс валют
        currencyExchange.setCurrencyRate("USD", 74.50);
        currencyExchange.setCurrencyRate("EUR", 89.30);

        // Удаляем Email наблюдателя
        currencyExchange.removeObserver(emailObserver);

        // Обновляем курс снова
        currencyExchange.setCurrencyRate("GBP", 102.15);
    }
}

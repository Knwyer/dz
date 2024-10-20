import java.util.ArrayList;
import java.util.List;

// Интерфейс ICommand - общий интерфейс для всех команд
interface ICommand {
    void execute();
    void undo();
}

// Класс Receiver - устройство, выполняющее команды
class Light {
    public void turnOn() {
        System.out.println("Свет включен.");
    }

    public void turnOff() {
        System.out.println("Свет выключен.");
    }
}

class Door {
    public void open() {
        System.out.println("Дверь открыта.");
    }

    public void close() {
        System.out.println("Дверь закрыта.");
    }
}

class Thermostat {
    private int temperature;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Температура установлена на " + temperature + "°C.");
    }

    public int getTemperature() {
        return temperature;
    }
}

// Конкретные команды для управления устройствами
class LightOnCommand implements ICommand {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

class LightOffCommand implements ICommand {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}

class DoorOpenCommand implements ICommand {
    private Door door;

    public DoorOpenCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }

    @Override
    public void undo() {
        door.close();
    }
}

class DoorCloseCommand implements ICommand {
    private Door door;

    public DoorCloseCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }

    @Override
    public void undo() {
        door.open();
    }
}

class ThermostatSetCommand implements ICommand {
    private Thermostat thermostat;
    private int temperature;

    public ThermostatSetCommand(Thermostat thermostat, int temperature) {
        this.thermostat = thermostat;
        this.temperature = temperature;
    }

    @Override
    public void execute() {
        thermostat.setTemperature(temperature);
    }

    @Override
    public void undo() {
        // Для простоты реализации мы просто уменьшаем температуру на 1
        thermostat.setTemperature(thermostat.getTemperature() - 1);
    }
}

// Класс Invoker - объект, который вызывает команды и управляет их историей
class RemoteControl {
    private List<ICommand> commandHistory = new ArrayList<>();

    public void executeCommand(ICommand command) {
        command.execute();
        commandHistory.add(command);
    }

    public void undoLastCommand() {
        if (commandHistory.isEmpty()) {
            System.out.println("Нет команд для отмены.");
            return;
        }
        ICommand command = commandHistory.remove(commandHistory.size() - 1);
        command.undo();
    }
}

// Клиентский код - демонстрация работы паттерна Команда
public class Main {
    public static void main(String[] args) {
        // Создание устройств
        Light livingRoomLight = new Light();
        Door frontDoor = new Door();
        Thermostat thermostat = new Thermostat();

        // Создание команд
        ICommand lightOn = new LightOnCommand(livingRoomLight);
        ICommand lightOff = new LightOffCommand(livingRoomLight);
        ICommand doorOpen = new DoorOpenCommand(frontDoor);
        ICommand doorClose = new DoorCloseCommand(frontDoor);
        ICommand setTemperature = new ThermostatSetCommand(thermostat, 22);

        // Создание пульта управления
        RemoteControl remote = new RemoteControl();

        // Выполнение команд
        remote.executeCommand(lightOn);
        remote.executeCommand(doorOpen);
        remote.executeCommand(setTemperature);

        // Отмена последней команды
        remote.undoLastCommand();

        // Выполнение оставшихся команд
        remote.executeCommand(lightOff);
        remote.executeCommand(doorClose);

        // Отмена последней команды
        remote.undoLastCommand();
    }
}

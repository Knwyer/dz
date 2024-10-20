public class Main {

    // Интерфейс команды
    interface Command {
        void execute();
        void undo();
    }

    // Класс устройства "Свет"
    static class Light {
        public void on() {
            System.out.println("Свет включен.");
        }

        public void off() {
            System.out.println("Свет выключен.");
        }
    }

    // Класс устройства "Кондиционер"
    static class AirConditioner {
        public void on() {
            System.out.println("Кондиционер включен.");
        }

        public void off() {
            System.out.println("Кондиционер выключен.");
        }
    }

    // Класс устройства "Телевизор"
    static class TV {
        public void on() {
            System.out.println("Телевизор включен.");
        }

        public void off() {
            System.out.println("Телевизор выключен.");
        }
    }

    // Команда для включения света
    static class LightOnCommand implements Command {
        private Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        public void execute() {
            light.on();
        }

        public void undo() {
            light.off();
        }
    }

    // Команда для выключения света
    static class LightOffCommand implements Command {
        private Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        public void execute() {
            light.off();
        }

        public void undo() {
            light.on();
        }
    }

    // Команда для включения кондиционера
    static class AirConditionerOnCommand implements Command {
        private AirConditioner airConditioner;

        public AirConditionerOnCommand(AirConditioner airConditioner) {
            this.airConditioner = airConditioner;
        }

        public void execute() {
            airConditioner.on();
        }

        public void undo() {
            airConditioner.off();
        }
    }

    // Команда для выключения кондиционера
    static class AirConditionerOffCommand implements Command {
        private AirConditioner airConditioner;

        public AirConditionerOffCommand(AirConditioner airConditioner) {
            this.airConditioner = airConditioner;
        }

        public void execute() {
            airConditioner.off();
        }

        public void undo() {
            airConditioner.on();
        }
    }

    // Команда для включения телевизора
    static class TVOnCommand implements Command {
        private TV tv;

        public TVOnCommand(TV tv) {
            this.tv = tv;
        }

        public void execute() {
            tv.on();
        }

        public void undo() {
            tv.off();
        }
    }

    // Команда для выключения телевизора
    static class TVOffCommand implements Command {
        private TV tv;

        public TVOffCommand(TV tv) {
            this.tv = tv;
        }

        public void execute() {
            tv.off();
        }

        public void undo() {
            tv.on();
        }
    }

    // Класс для управления пультом
    static class RemoteControl {
        private Command[] onCommands;
        private Command[] offCommands;
        private Command undoCommand;

        public RemoteControl() {
            onCommands = new Command[7];
            offCommands = new Command[7];
            undoCommand = null;
        }

        public void setCommand(int slot, Command onCommand, Command offCommand) {
            onCommands[slot] = onCommand;
            offCommands[slot] = offCommand;
        }

        public void onButtonWasPressed(int slot) {
            if (onCommands[slot] != null) {
                onCommands[slot].execute();
                undoCommand = onCommands[slot];
            } else {
                System.out.println("Команда не назначена на слот " + slot);
            }
        }

        public void offButtonWasPressed(int slot) {
            if (offCommands[slot] != null) {
                offCommands[slot].execute();
                undoCommand = offCommands[slot];
            } else {
                System.out.println("Команда не назначена на слот " + slot);
            }
        }

        public void undoButtonWasPressed() {
            if (undoCommand != null) {
                undoCommand.undo();
            } else {
                System.out.println("Нет команды для отмены.");
            }
        }
    }

    // Макрокоманда для выполнения нескольких команд
    static class MacroCommand implements Command {
        private Command[] commands;

        public MacroCommand(Command[] commands) {
            this.commands = commands;
        }

        public void execute() {
            for (Command command : commands) {
                command.execute();
            }
        }

        public void undo() {
            for (Command command : commands) {
                command.undo();
            }
        }
    }

    // Главный метод для тестирования системы
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();

        Light livingRoomLight = new Light();
        AirConditioner airConditioner = new AirConditioner();
        TV tv = new TV();

        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        AirConditionerOnCommand acOn = new AirConditionerOnCommand(airConditioner);
        AirConditionerOffCommand acOff = new AirConditionerOffCommand(airConditioner);

        TVOnCommand tvOn = new TVOnCommand(tv);
        TVOffCommand tvOff = new TVOffCommand(tv);

        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, acOn, acOff);
        remote.setCommand(2, tvOn, tvOff);

        // Тестирование пульта
        remote.onButtonWasPressed(0);
        remote.offButtonWasPressed(0);
        remote.undoButtonWasPressed();

        // Макрокоманда
        Command[] partyMode = {livingRoomLightOn, acOn, tvOn};
        MacroCommand partyMacro = new MacroCommand(partyMode);

        System.out.println("\nЗапуск макрокоманды...");
        partyMacro.execute();
        partyMacro.undo();
    }
}

import java.util.ArrayList;
import java.util.List;

// Интерфейс IMediator
interface IMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
    void removeUser(User user);
}

// Класс ChatRoom - конкретный посредник
class ChatRoom implements IMediator {
    private final List<User> users = new ArrayList<>();

    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            // Не отправляем сообщение отправителю
            if (u != user) {
                u.receiveMessage(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        System.out.println(user.getName() + " присоединился к чату.");
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
        System.out.println(user.getName() + " покинул чат.");
    }
}

// Класс User - участник чата
class User {
    private final String name;
    private final IMediator mediator;

    public User(String name, IMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        System.out.println(name + " отправил сообщение: " + message);
        mediator.sendMessage(message, this);
    }

    public void receiveMessage(String message) {
        System.out.println(name + " получил сообщение: " + message);
    }
}

// Клиентский код
public class Main {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User user1 = new User("Алиса", chatRoom);
        User user2 = new User("Боб", chatRoom);
        User user3 = new User("Чарли", chatRoom);

        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        user1.sendMessage("Привет всем!");
        user2.sendMessage("Привет, Алиса!");
        user3.sendMessage("Здравствуйте, ребята!");

        chatRoom.removeUser(user2);
        user1.sendMessage("Где Боб?");
    }
}

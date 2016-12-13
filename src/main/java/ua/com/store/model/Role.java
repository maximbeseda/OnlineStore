package ua.com.store.model;

import ua.com.store.enums.RoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает сущность "Роль пользвателей", наследует класс {@link Model}.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "roles") указывает на таблицу "roles", в которой будут храниться объекты.
 */
@Entity
@Table(name = "Roles")
public class Role extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Название роли, может принимать одно из значений перечисления {@link RoleEnum}.
     * Значение поля сохраняется в колонке "title". Не может быть null.
     */
    @Column(name = "title", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum title;

    /**
     * Описание роли. Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Список пользвателей, которые относятся к данной роли.
     * К текущей роли можно добраться через поле "role"
     * в объекте класса {@link User}.
     * Выборка объектов users при первом доступе к ним.
     * Сущности users автоматически удаляется при удалении текущей.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.REMOVE)
    private List<User> users = new ArrayList<>();

    public Role() {
        super();
        description = "";
    }

    public Role(RoleEnum title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Title: " + title.name() + "\nDescription: " + description;
    }

    /**
     * Генерирует строку для конечного сравнения роли в методе equals() родительского класса.
     * Переопределенный метод родительского класса {@link Model}.
     */
    public String toEquals() {
        return title.name();
    }

    /**
     * Добавляет пользователя в список текущей роли.
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Добавляет список пользователей в список пользователей users.
     */
    public void addUsers(List<User> users) {
        this.users.addAll(users);
    }

    /**
     * Удаляет пользователя из списка текущей роли.
     */
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     * Метод удаляет список пользователей из списка users.
     */
    public void removeUsers(List<User> users) {
        this.users.removeAll(users);
    }

    /**
     * Очищает список пользователей текущей роли.
     */
    public void clearUsers() {
        users.clear();
    }

    public List<User> getUsers() {
        return getUnmodifiableList(users);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public RoleEnum getTitle() {
        return title;
    }

    public void setTitle(RoleEnum title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }
}

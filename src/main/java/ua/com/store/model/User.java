package ua.com.store.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Класс описывает сущность "Пользователь", наследует класс {@link Model}
 * и реализует методы интерфейса {@link UserDetails}.
 * В классе обязательно поле role, то есть можно создавать пользователей с разными правами (администраторы
 * клиенты и т.д).
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "users") указывает на таблицу "users", в которой будут храниться объекты.
 */
@Entity
@Table(name = "Users")
public class User extends Model implements UserDetails {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Имя пользователя. Значение поля сохраняется в колонке "name". Не может быть null.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Имя пользователя для входа в учетную запись на сайте (логин).
     * Значение поля сохраняется в колонке "username".
     */
    @Column(name = "username")
    private String username;

    /**
     * Пароль пользователя для входа в учетную запись на сайте.
     * Значение поля сохраняется в колонке "password".
     */
    @Column(name = "password")
    private String password;

    /**
     * Электронная почта пользователя.
     * Значение поля сохраняется в колонке "email". Не может быть null.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Номер телефона пользователя.
     * Значение поля сохраняется в колонке "phone". Не может быть null.
     */
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * Ссылка на страничку в соц. сети "ВКонтакте" пользователя.
     * Значение поля сохраняется в колонке "vkontakte".
     */
    @Column(name = "vkontakte")
    private String vkontakte;

    /**
     * Ссылка на страничку в соц. сети "Facebook" пользователя.
     * Значение поля сохраняется в колонке "facebook".
     */
    @Column(name = "facebook")
    private String facebook;

    /**
     * Логин пользователя в месенджере "Skype".
     * Значение поля сохраняется в колонке "skype".
     */
    @Column(name = "skype")
    private String skype;

    /**
     * Описание заказа. Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description")
    private String description;

    /**
     * Роль пользователя.
     * Значение поля (id объекта role) сохраняется в колонке "role_id". Не может быть null.
     * Между объектами классов {@link User} и
     * {@link Role} связь многие-к-одному, а именно
     * много разных пользователей могут иметь одинаковую роль (права) на сайте.
     * Выборка объекта status до первого доступа нему, при первом доступе к текущему объекту.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    /**
     * Список заказов, которые сделал текущий клиент.
     * К текущему пользователю можно добраться через поле "client"
     * в объекте класса {@link Order}.
     * Выборка продаж при первом доступе к текущему объекту.
     * Сущности clientOrders автоматически удаляются при удалении текущей сущности.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Order> clientOrders = new ArrayList<>();

    /**
     * Список заказов, которые обработал текущий менеджер.
     * К текущему пользователю можно добраться через поле "manager"
     * в объекте класса {@link Order}.
     * Выборка продаж при первом доступе к текущему объекту.
     * Сущности managerOrders автоматически удаляются при удалении текущей сущности.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manager", cascade = CascadeType.REMOVE)
    private List<Order> managerOrders = new ArrayList<>();

    public User() {
        super();
        name = "";
        username = "";
        password = "";
        email = "";
        phone = "";
        vkontakte = "";
        facebook = "";
        skype = "";
        description = "";
    }

    public User(String name, String email, String phone, Role role) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;

        username = "";
        password = "";
        vkontakte = "";
        facebook = "";
        skype = "";
        description = "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name)
                .append("\nRole: ").append(role.getDescription())
                .append("\nEmail: ").append(email)
                .append("\nPhone: ").append(phone);
        return sb.toString();
    }

    /**
     * Генерирует строку для конечного сравнения пользователей в методе equals() родительского класса.
     * Переопределенный метод родительского класса {@link Model}.
     */
    @Override
    public String toEquals() {
        return getName() + getEmail() + getPhone();
    }

    /**
     * Возвращает значение типа boolean в зависемости от срока действия аккаунта.
     * Реализованый метод интерфейса {@link UserDetails}.
     *
     * @return true - если текущий аккаунт работоспособный.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Возвращает значение типа boolean от того, заблокирован текущий аккаунт
     * (пользователь) или нет. Реализованый метод интерфейса {@link UserDetails}.
     *
     * @return true - если текущий аккаунт не заблокирован.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Возвращает значение типа boolean от того, активны ли права (полномичия)
     * данного аккаунта или нет. Реализованый метод интерфейса {@link UserDetails}.
     *
     * @return true - если срок прав текущего аккаунта не истек.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Возвращает значение типа boolean от того, активный ли
     * текущий аккаунт или нет. Реализованый метод интерфейса {@link UserDetails}.
     *
     * @return true - если текущий аккаунт активный.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Возвращает список всех ролей пользователя через объект-обертку
     * класса SimpleGrantedAuthority. Реализованый метод интерфейса {@link UserDetails}.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + role.getTitle().name()));
        return roles;
    }

    /**
     * Инициализация полей пользователя.
     */
    public void initialize(String name, String username, String password, String email,
                           String phone, String vkontakte, String facebook, String skype,
                           String description, Role role) {
        setName(name);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setVkontakte(vkontakte);
        setFacebook(facebook);
        setSkype(skype);
        setDescription(description);
        setRole(role);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? "" : username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? "" : password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? "" : phone;
    }

    public String getVkontakte() {
        return vkontakte;
    }

    public void setVkontakte(String vkontakte) {
        this.vkontakte = vkontakte == null ? "" : vkontakte;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook == null ? "" : facebook;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype == null ? "" : skype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getClientOrders() {
        return getUnmodifiableList(clientOrders);
    }

    public void setClientOrders(List<Order> clientOrders) {
        this.clientOrders = clientOrders;
    }

    public List<Order> getManagerOrders() {
        return getUnmodifiableList(managerOrders);
    }

    public void setManagerOrders(List<Order> managerOrders) {
        this.managerOrders = managerOrders;
    }
}

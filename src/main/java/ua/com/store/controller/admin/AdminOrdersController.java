package ua.com.store.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.store.exception.WrongInformationException;
import ua.com.store.model.Order;
import ua.com.store.model.Status;
import ua.com.store.model.User;
import ua.com.store.service.OrderService;
import ua.com.store.service.RoleService;
import ua.com.store.service.StatusService;
import ua.com.store.service.UserService;

import java.util.Date;

/**
 * Класс-контроллер страниц управления заказами клиентов. К даному контроллеру и соответствующим
 * страницам могут обращатсья пользователи, имеющие роль-админстратор.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 * Аннотацией @RequestMapping(value = "/admin") сообщаем, что данный контроллер
 * будет обрабатывать запрос, URI которого "/admin".
 * Методы класса работают с объектом, возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminOrdersController {
    /**
     * Объект сервиса для работы с заказами клиентов.
     */
    private OrderService orderService;

    /**
     * Объект сервиса для работы с статусами выполнения заказов.
     */
    private StatusService statusService;

    /**
     * Объект сервиса для работы с категориями товаров.
     */
    private UserService userService;

    /**
     * Объект сервиса для работы с категориями товаров.
     */
    private RoleService roleService;

    /**
     * Конструктор для инициализации основных переменных контроллера заказов.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать объекты.
     */
    @Autowired
    public AdminOrdersController(OrderService orderService, StatusService statusService,
                                 UserService userService, RoleService roleService) {
        super();
        this.orderService = orderService;
        this.statusService = statusService;
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Возвращает все заказы, сделаные клиентами, на страницу "admin/order/all".
     * URL запроса {"/admin", "/admin/orders"}, метод GET.
     */
    @RequestMapping(value = {"", "/orders"}, method = RequestMethod.GET)
    public ModelAndView viewAllOrders(ModelAndView modelAndView) {
        modelAndView.addObject("orders", orderService.getAll());
        modelAndView.addObject("status_new", statusService.getDefault());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/order/all");
        return modelAndView;
    }

    /**
     * Возвращает заказ с уникальным кодом id на страницу "admin/order/one".
     * URL запроса "/admin/view_order_{id}", метод GET.
     */
    @RequestMapping(value = "/view_order_{id}", method = RequestMethod.GET)
    public ModelAndView viewOrder(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Order order = orderService.get(id);
        modelAndView.addObject("order", order);
        modelAndView.addObject("sale_positions", order.getSalePositions());
        modelAndView.addObject("order_price", order.getPrice());
        modelAndView.addObject("status_new", statusService.getDefault());
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/order/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/order/edit" для редактирование заказа с уникальным кодом,
     * который совпадает с параметром id. URL запроса "/admin/edit_order_{id}", метод GET.
     */
    @RequestMapping(value = "/edit_order_{id}", method = RequestMethod.GET)
    public ModelAndView getEditOrderPage(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Order order = orderService.get(id);
        modelAndView.addObject("order", order);
        modelAndView.addObject("sale_positions", order.getSalePositions());
        modelAndView.addObject("order_price", order.getPrice());
        modelAndView.addObject("statuses", statusService.getAll());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/order/edit");
        return modelAndView;
    }

    /**
     * Обновляет заказ по входящим параметрам и перенаправляет по запросу "/admin/view_order_{id}".
     * URL запроса "/admin/update_category", метод POST.
     */
    @RequestMapping(value = "/update_order", method = RequestMethod.POST)
    public ModelAndView updateOrder(@RequestParam long id,
                                    @RequestParam(value = "auth_user") long managerId,
                                    @RequestParam String number,
                                    @RequestParam(value = "status") long statusId,
                                    @RequestParam(value = "user_name") String name,
                                    @RequestParam(value = "user_email") String email,
                                    @RequestParam(value = "user_phone") String phone,
                                    @RequestParam(value = "shipping-address") String shippingAddress,
                                    @RequestParam(value = "shipping-details") String shippingDetails,
                                    @RequestParam String description,
                                    ModelAndView modelAndView) {
        Order order = orderService.get(id);

        User client = order.getClient();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);

        Status status = statusService.get(statusId);
        User manager = userService.get(managerId);

        order.initialize(number, new Date(), shippingAddress, shippingDetails, description, status, client, manager);
        orderService.update(order);

        modelAndView.setViewName("redirect:/admin/view_order_" + id);
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException, если обратится по запросу "/update_order" методом GET.
     */
    @RequestMapping(value = "/update_order", method = RequestMethod.GET)
    public void updateOrder() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/update_order\" is not supported!");
    }

    /**
     * Удаляет заказ с уникальным кодом, который совпадает с входящим параметром id,
     * и перенаправляет по запросу "/admin/orders".
     * URL запроса "/delete_orders_{id}", метод GET.
     */
    @RequestMapping(value = "/delete_order_{id}", method = RequestMethod.GET)
    public ModelAndView deleteOrder(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        orderService.remove(id);
        modelAndView.setViewName("redirect:/admin/orders");
        return modelAndView;
    }

    /**
     * Удаляет все заказы и перенаправляет по запросу "/admin/orders".
     * URL запроса "/delete_all_orders", метод GET.
     */
    @RequestMapping(value = "/delete_all_orders", method = RequestMethod.GET)
    public ModelAndView deleteAllOrders(ModelAndView modelAndView) {
        orderService.removeAll();
        modelAndView.setViewName("redirect:/admin/orders");
        return modelAndView;
    }
}

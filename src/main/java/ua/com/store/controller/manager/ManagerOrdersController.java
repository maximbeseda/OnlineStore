package ua.com.store.controller.manager;

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
 * Класс-контроллер страниц, предназначеных для управления заказами менеджерами.
 * К даному контроллеру и соответствующим страницам могут обращатсья пользователи,
 * имеющие роль-админстратор и -менеджер.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 * Аннотацией @RequestMapping(value = "/managers") сообщаем, что данный контроллер
 * будет обрабатывать запрос, URI которого "/managers".
 * Методы класса работают с объектом, возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 */
@Controller
@RequestMapping(value = "/managers")
public class ManagerOrdersController {
    /**
     * Объект сервиса для работы с пользователями.
     */
    private UserService userService;

    /**
     * Объект сервиса для работы с заказами клиентов.
     */
    private OrderService orderService;

    /**
     * Объект сервиса для работы с статусами виполнения заказов.
     */
    private StatusService statusService;

    /**
     * Объект сервиса для работы с ролями пользователей.
     */
    private RoleService roleService;

    /**
     * Конструктор для инициализации основных переменных контроллера страниц для менеджеров.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать объекты.
     */
    @Autowired
    public ManagerOrdersController(UserService userService, OrderService orderService,
                                   StatusService statusService, RoleService roleService) {
        super();
        this.userService = userService;
        this.orderService = orderService;
        this.statusService = statusService;
        this.roleService = roleService;
    }

    /**
     * Возвращает все заказы, сделаные клиентами, на страницу "managers/order/all".
     * URL запроса {"/managers" , "/managers/orders"}, метод GET.
     */
    @RequestMapping(value = {"", "/orders"}, method = RequestMethod.GET)
    public ModelAndView viewAllOrders(ModelAndView modelAndView) {
        modelAndView.addObject("orders", orderService.getAll());
        modelAndView.addObject("status_new", statusService.getDefault());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("managers/order/all");
        return modelAndView;
    }

    /**
     * Возвращает заказ с уникальным кодом id на страницу "managers/order/one".
     * URL запроса "/managers/view_order_{id}", метод GET.
     */
    @RequestMapping(value = "/view_order_{id}", method = RequestMethod.GET)
    public ModelAndView viewOrder(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Order order = orderService.get(id);
        modelAndView.addObject("order", order);
        modelAndView.addObject("sales", order.getSalePositions());
        modelAndView.addObject("order_price", order.getPrice());
        modelAndView.addObject("status_new", statusService.getDefault());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.setViewName("managers/order/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "managers/order/edit" для редактирование заказа с уникальным кодом,
     * который совпадает с параметром id, или перенаправляет по запросу "/managers/orders", если
     * этот заказ уже обработал другой менеджер. URL запроса "/managers/edit_order_{id}", метод GET.
     */
    @RequestMapping(value = "/edit_order_{id}", method = RequestMethod.GET)
    public ModelAndView getEditOrderPage(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Order order = orderService.get(id);
        if (order.getManager() == null || order.getManager().equals(userService.getAuthenticatedUser())) {
            modelAndView.addObject("order", order);
            modelAndView.addObject("sales", order.getSalePositions());
            modelAndView.addObject("order_price", order.getPrice());
            modelAndView.addObject("statuses", statusService.getAll());
            modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
            modelAndView.setViewName("managers/order/edit");
        } else {
            modelAndView.setViewName("redirect:/managers/orders");
        }
        return modelAndView;
    }

    /**
     * Обновляет заказ по входящим параметрам и перенаправляет по запросу "/managers/view_order_{id}".
     * URL запроса "/managers/update_category", метод POST.
     */
    @RequestMapping(value = "/update_order", method = RequestMethod.POST)
    public ModelAndView updateOrder(@RequestParam long id,
                                    @RequestParam(value = "auth_user") long managerId,
                                    @RequestParam String number,
                                    @RequestParam(value = "status") long statusId,
                                    @RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String phone,
                                    @RequestParam(value = "shipping-address") String shippingAddress,
                                    @RequestParam(value = "shipping-details") String shippingDetails,
                                    @RequestParam String description,
                                    ModelAndView modelAndView) {
        Order order = orderService.get(id);

        if (order.getManager() == null || order.getManager().equals(userService.getAuthenticatedUser())) {

            User client = order.getClient();
            client.setName(name);
            client.setEmail(email);
            client.setPhone(phone);

            Status status = statusService.get(statusId);
            User manager = null;
            if (!status.equals(statusService.getDefault())) {
                manager = userService.get(managerId);
            }

            order.initialize(number, new Date(), shippingAddress, shippingDetails, description, status, client, manager);
            orderService.update(order);
        }

        modelAndView.setViewName("redirect:/managers/view_order_" + id);
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException, если обратится по запросу "/update_order" методом GET.
     */
    @RequestMapping(value = "/update_order", method = RequestMethod.GET)
    public void updateOrder() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/update_order\" is not supported!");
    }
}

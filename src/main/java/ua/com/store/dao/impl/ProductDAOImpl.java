package ua.com.store.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.store.dao.ProductDAO;
import ua.com.store.model.Product;
import ua.com.store.repository.ProductRepository;

import java.util.List;

/**
 * Класс реализует методы доступа объектов класса {@link Product}
 * в базе данных интерфейса {@link ProductDAO}, наследует родительский
 * абстрактный класс {@link DataDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link ProductRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 */
@Repository
public class ProductDAOImpl extends DataDAOImpl<Product> implements ProductDAO {
    /**
     * Реализация репозитория {@link ProductRepository} для работы с товаров базой данных.
     */
    private ProductRepository repository;

    /**
     * Конструктор для инициализации основных переменных.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    public ProductDAOImpl(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает товар из базы данных, у которого совпадает параметр url.
     */
    @Override
    public Product getByUrl(String url) {
        return repository.findByUrl(url);
    }

    /**
     * Возвращает товар из базы даных, у которого совпадает уникальный
     * артикль с значением входящего параметра.
     */
    @Override
    public Product getByArticle(int article) {
        return repository.findByArticle(article);
    }

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр url.
     */
    @Override
    public void removeByUrl(String url) {
        repository.deleteByUrl(url);
    }

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр article.
     */
    @Override
    public void removeByArticle(int article) {
        repository.deleteByArticle(article);
    }

    /**
     * Удаляет товары из базы даных, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     */
    @Override
    public void removeByCategoryId(long id) {
        List<Product> productList = repository.findByCategoryId(id);
        repository.delete(productList);
    }

    /**
     * Возвращает список товаров, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     */
    @Override
    public List<Product> getListByCategoryId(long id) {
        return repository.findByCategoryId(id);
    }
}

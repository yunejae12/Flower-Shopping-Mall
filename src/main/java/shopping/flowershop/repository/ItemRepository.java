package shopping.flowershop.repository;

import org.springframework.stereotype.Repository;
import shopping.flowershop.domain.Category;
import shopping.flowershop.domain.Item;
import shopping.flowershop.domain.Seller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface ItemRepository {
    void save(Item item);
    Item findOne(Long id);
    List<Item> findByName(String name);
    List<Item> findByCategory(Category category);

    List<Item> findAllById(Long... ids);

    List<Item> findAllByPriceAscend();
    List<Item> findAllByPriceDescend();
    List<Item> findBySeller(Seller seller);
    List<Item> findBySellerStockZero(Seller seller);
}

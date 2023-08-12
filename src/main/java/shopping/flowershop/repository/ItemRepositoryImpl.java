package shopping.flowershop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shopping.flowershop.domain.Category;
import shopping.flowershop.domain.Item;
import shopping.flowershop.domain.Seller;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {
    @Autowired
    private final EntityManager em;

    @Override
    public void save(Item item) {
        if(item.getId() == null){
            em.persist(item);
        }else{
            em.merge(item);
        }
    }

    @Override
    public Item findOne(Long id) {
        return em.find(Item.class,id);
    }

    @Override
    public Item findAll(ItemSearch itemSearch){
        return null;
    }



    @Override
    public List<Item> findByName(String name) {
        return em.createQuery("select i from Item i where i.name =:name",Item.class)
                .setParameter("name",name)
                .getResultList();
    }

    @Override
    public List<Item> findByCategory(Category category) {
        return em.createQuery("select i from Item i where i.category =:category",Item.class)
                .setParameter("category",category)
                .getResultList();
    }
    @Override
    public List<Item> findAllById(Long... ids){
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }

    @Override
    public List<Item> findAllByPriceAscend() {
        return em.createQuery("select i from Item i orderby i.stockQuantity asc",Item.class)
                .getResultList();
    }

    @Override
    public List<Item> findAllByPriceDescend() {
        return em.createQuery("select i from Item i orderby i.stockQuantity desc",Item.class)
                .getResultList();
    }

    @Override
    public List<Item> findBySeller(Seller seller) {
        return em.createQuery("select i from Item i where i.seller =:seller",Item.class)
                .setParameter("seller",seller)
                .getResultList();
    }

    @Override
    public List<Item> findBySellerStockZero(Seller seller) {
        return em.createQuery("select i from Item where i.seller =:seller and i.stockQuantity = 0",Item.class)
                .setParameter("seller",seller)
                .getResultList();
    }
}

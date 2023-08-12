package shopping.flowershop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shopping.flowershop.domain.ItemImage;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemImageRepositoryImpl implements ItemImageRepository {
    @Autowired
    EntityManager em;

    @Override
    public Optional<ItemImage> findOne(Long imageId) {
        return  em.createQuery("select i from ItemImage i where i.id =:imgId",ItemImage.class)
                .setParameter("imgId",imageId).getResultList().stream().findFirst();
    }

    @Override
    public List<ItemImage> findAllFromItem(Long itemId) {
        return null;
    }

    @Override
    public void save(ItemImage image) {
        em.persist(image);
    }
}

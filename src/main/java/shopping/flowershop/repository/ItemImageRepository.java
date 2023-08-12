package shopping.flowershop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import shopping.flowershop.domain.ItemImage;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public interface ItemImageRepository {
    Optional<ItemImage> findOne(Long imageId);
    List<ItemImage> findAllFromItem(Long itemId);
    void save(ItemImage image);

}

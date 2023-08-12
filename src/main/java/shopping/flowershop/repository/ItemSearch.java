package shopping.flowershop.repository;


import lombok.Getter;
import org.springframework.stereotype.Repository;
import shopping.flowershop.domain.Category;

import java.util.List;

@Getter
@Repository
public class ItemSearch {
    private String itemName;
    private String SellerName;
    private List<Category> categories;




}

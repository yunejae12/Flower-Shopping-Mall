package shopping.flowershop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shopping.flowershop.domain.CategoryItem;
import shopping.flowershop.domain.ItemFavorites;
import shopping.flowershop.domain.Seller;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    private String imageUrl;
    private Seller seller;
    List<CategoryItem> categories;
    List<ItemFavorites> likedMembers;
    public String content;
}

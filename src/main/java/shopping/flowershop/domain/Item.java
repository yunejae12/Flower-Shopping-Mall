package shopping.flowershop.domain;

import lombok.*;
import shopping.flowershop.exception.NotEnoughStackException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item extends TimeBase {
    @Id @GeneratedValue
    @Column(name="ITEM_ID")
    private Long id;

    private String name;
    private int price;
    @Setter
    private int stockQuantity;
    private String imageUrl;

    private Long click = 0L;
    private Long likes = 0L;
    private Long itemSold = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_ID")
    private Seller seller;


    @OneToMany(mappedBy = "item")
    List<CategoryItem> categories = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    List<ItemFavorites> likedMembers = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    List<ItemImage> images = new ArrayList<>();

    private String briefContent;

    private String content;

    public void addStock(int stockQuantity){
        this.stockQuantity += stockQuantity;
    }

    public void removeStock(int stockQuantity){
        if(this.stockQuantity - stockQuantity <0){
            throw new NotEnoughStackException("재고수량보다 많은 양을 요청했습니다.");
        }else{
            this.stockQuantity -= stockQuantity;
        }
    }



    @Builder
    public Item(String name,int price,int stockQuantity){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void setItemSeller(Seller s){
        this.seller = s;
    }

    public void addItemImage(ItemImage img){
        this.images.add(img);
        img.setItem(this);
    }



}

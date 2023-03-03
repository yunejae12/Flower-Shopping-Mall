package shopping.flowershop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shopping.flowershop.exception.ItemNotFoundException;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Seller extends TimeBase {
    @Id @GeneratedValue
    @Column(name = "SELLER_ID")
    private Long id;
    private String loginId;
    private String password;
    private String enterpriseNumber;
    private String name;
    @OneToMany(mappedBy = "seller")
    private List<Item> items;

    @Embedded
    private Address address;
    private String phoneNumber;
    private String email;
    private Double reputation;
    @Enumerated(EnumType.STRING)
    private SellerStatus status;

    public void addItem(Item item,int amount){
        item.setStockQuantity(amount);
        items.add(item);
        item.setItemSeller(this);
    }

    public void removeItem(Item item){
        if(items.contains(item)){
            items.remove(item);
        }else{
            throw new ItemNotFoundException("삭제할 아이템이 판매자의 목록에 없습니다.");
        }
    }
}

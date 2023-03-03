package shopping.flowershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class ItemFavorites {
    @Id @GeneratedValue
    @Column(name = "ITEM_FAVORITES_ID")
    private Long id;


    @ManyToOne @Setter
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    private Item item;

    private int preferPoint = 0;





}

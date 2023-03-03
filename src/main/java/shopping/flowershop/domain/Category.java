package shopping.flowershop.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends TimeBase {
    @Id @GeneratedValue
    @Column(name="CATEGORY_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    List<CategoryItem> items = new ArrayList<>();


}

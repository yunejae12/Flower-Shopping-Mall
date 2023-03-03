package shopping.flowershop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shopping.flowershop.domain.CategoryItem;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    List<CategoryItem> items;
}

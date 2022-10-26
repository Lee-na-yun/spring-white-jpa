package site.metacoding.white.util;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@NoArgsConstructor // 스프링에서 DB->rs->Entity 전략 : 디폴트 생성자를 호출한 뒤 setter
@AllArgsConstructor
@Setter
@Getter
class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
    private String mcp; // 제조사
}

@AllArgsConstructor
@Setter
@Getter
class ProductDto {
    private String name;
    private Integer price;
    private Integer qty;
}

public class MapperTest {

    @Test
    public void 고급매핑하기() {
        // toEntity, toDto 만들어서 매핑하면 편하지 않을까?
    }

    @Test
    public void 매핑하기1() {
        // 1. product 객체 생성
        Product product = new Product(1, "사과", 1000, 2, "과일가게");

        // 2. 값 넣기

        // 3. productDto 객체 생성
        ProductDto productDto = new ProductDto(product.getName(), product.getPrice(), product.getQty());

        // 4. Product -> ProductDto로 옮기기

        // 5. ProductDto -> Product로 변경
        Product p2 = new Product(null, productDto.getName(), productDto.getPrice(), productDto.getQty(), null);
    }
}

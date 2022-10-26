package site.metacoding.white.util;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // 스프링에서 DB->rs->Entity 전략 : 디폴트 생성자를 호출한 뒤 setter
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
        Product product = new Product();

        // 2. 값 넣기
        product.setId(1);
        product.setName("사과");
        product.setPrice(1000);
        product.setQty(2);
        product.setMcp("과일가게");

        // 3. productDto 객체 생성
        ProductDto productDto = new ProductDto();
        // 4. Product -> ProductDto로 옮기기
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQty(product.getQty());

        // 5. ProductDto -> Product로 변경
        Product p2 = new Product();
        p2.setName(productDto.getName());
        p2.setPrice(productDto.getPrice());
        p2.setQty(productDto.getQty());

    }
}

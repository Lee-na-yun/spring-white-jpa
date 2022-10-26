package site.metacoding.white.util;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@NoArgsConstructor // 스프링에서 DB->rs->Entity 전략 : 디폴트 생성자를 호출한 뒤 setter
//@AllArgsConstructor
@Setter
@Getter
class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
    private String mcp; // 제조사

    @Builder
    public Product(Integer id, String name, Integer price, Integer qty, String mcp) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.mcp = mcp;
    }

}

// @AllArgsConstructor
@Setter
@Getter
class ProductDto {
    private String name;
    private Integer price;
    private Integer qty;

    // Product를 productDto로 변경하기
    public ProductDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.qty = product.getQty();
    }

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .qty(qty)
                .build();
    }

}

public class MapperTest {

    @Test
    public void 고급매핑하기() {
        // toEntity, toDto 만들어서 매핑하면 편하지 않을까?
    }

    @Test
    public void 매핑하기1() {
        // 1. product 객체 생성
        Product product = Product.builder()
                .id(1)
                .name("사과")
                .price(1000)
                .qty(2)
                .mcp("과일가게")
                .build();

        // 2. 값 넣기

        // 3. productDto 객체 생성
        ProductDto productDto = new ProductDto(product);

        // 4. Product -> ProductDto로 옮기기

        // 5. ProductDto -> Product로 변경
        Product p2 = productDto.toEntity();
    }
}

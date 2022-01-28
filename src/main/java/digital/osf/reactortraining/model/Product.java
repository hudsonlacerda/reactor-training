package digital.osf.reactortraining.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("products")
public class Product {
    @Id
    @Column("product_id")
    private Integer id;

    @Length(max = 255)
    @Column("product_name")
    private String productName;

    @Column("brand_id")
    private Integer brand;

    @NotNull
    @Column("category_id")
    private Integer category;

    @NotNull
    @Column("model_year")
    private Integer modelYear;

    @NotNull
    @Column("list_price")
    private Double listPrice;
}
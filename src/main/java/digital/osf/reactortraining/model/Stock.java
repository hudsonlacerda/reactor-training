package digital.osf.reactortraining.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("stocks")
public class Stock {

    @Id
    private Integer id;

    @Transient
    // @ManyToOne
    // @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private Store store;

    @Transient
    // @ManyToOne
    // @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column("quantity")
    private Integer quantity;

}

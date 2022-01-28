package digital.osf.reactortraining.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
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
@Table("order_items")
public class OrderItem {

    @Id
    @Column("item_id")
    private Integer id;

    @NotNull
    @Column("quantity")
    private Integer quantity;

    @NotNull
    @Column("list_price")
    private Double listPrice;

    @NotNull
    @Column("discount")
    private Double discount;

    @NotNull
    // @ManyToOne
    // @JoinColumn("product_id", referencedColumnName = "product_id")
    private Product product;

    @NotNull
    // @ManyToOne
    // @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

}
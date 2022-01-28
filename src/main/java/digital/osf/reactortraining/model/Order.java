package digital.osf.reactortraining.model;

import java.time.LocalDate;
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
@Table("orders")
public class Order {

    @Id
    @Column("order_id")
    private Integer id;

    @NotNull
    @Column("order_status")
    private Integer orderStatus;

    @NotNull
    @Column("order_date")
    private LocalDate orderDate;

    @NotNull
    @Column("required_date")
    private LocalDate required_date;

    @Column("shipped_date")
    private LocalDate shipped_date;

    private Customer customer;

    @NotNull
    //@JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private Store store;

    @NotNull
    //@ManyToOne
    //@JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    private Staff staff;
}

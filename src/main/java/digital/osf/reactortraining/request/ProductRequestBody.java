package digital.osf.reactortraining.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import digital.osf.reactortraining.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestBody {
    @NotEmpty
    private String productName;
    @NotNull
    private Integer brand;
    @NotNull
    private Integer category;
    @NotNull
    private Integer modelYear;
    @NotNull
    private Double listPrice;

    public Product convert() {
        return Product.builder()
                .productName(productName)
                .listPrice(listPrice)
                .modelYear(modelYear)
                .build();
    }
}

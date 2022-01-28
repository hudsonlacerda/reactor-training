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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("brands")
public class Brand {

    @Id
    @Column("brand_id")
    private Integer id;

    @Length(max = 255)
    @NotNull
    @Column("brand_name")
    private String brandName;

}

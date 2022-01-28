package digital.osf.reactortraining.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
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
@Table("stores")
public class Store {

    @Id
    @Column("store_id")
    private Integer id;

    @Length(max = 255)
    @UniqueElements
    @Column("store_name")
    private String storeName;

    @Length(max = 25)
    @Column("phone")
    private String phone;

    @Length(max = 255)
    @Column("email")
    private String email;

    @Length(max = 255)
    @Column("street")
    private String street;

    @Length(max = 255)
    @Column("city")
    private String city;

    @Length(max = 10)
    @Column("state")
    private String state;

    @Length(max = 5)
    @Column("zip_code")
    private String zipCode;

}

package digital.osf.reactortraining.model;

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
@Table("customers") // , schema = "osf")
public class Customer {

    @Id
    @Column("customer_id")
    private Integer id;

    @Length(max = 255)
    @Column("first_name")
    private String firstName;

    @Length(max = 255)
    @Column("last_name")
    private String lastName;

    @Length(max = 25)
    @Column("phone")
    private String phone;

    @Length(max = 255)
    @Column("email")
    private String email;

    @Length(max = 255)
    @Column("street")
    private String street;

    @Length(max = 50)
    @Column("city")
    private String city;

    @Length(max = 25)
    @Column("state")
    private String state;

    @Length(max = 5)
    @Column("zip_code")
    private String zipCode;

}

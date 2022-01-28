package digital.osf.reactortraining.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
@Table("staffs")
public class Staff implements Serializable {

    @Id
    @Column("staff_id")
    private Integer id;

    @NotNull
    @Length(max = 50)
    @Column("first_name")
    private String firstName;

    @NotNull
    @Length(max = 50)
    @Column("last_name")
    private String lastName;

    @NotNull
    @UniqueElements
    @Length(max = 255)
    @Column("email")
    private String email;

    @Length(max = 25)
    @Column("phone")
    private String phone;

    @NotNull
    @Column("active")
    private Boolean active;

    @NotNull
    @Column("store_id")
    private Integer store;

    @Column("manager_id")
    private Integer manager;

    @Transient
    private List<Staff> staffs;
}

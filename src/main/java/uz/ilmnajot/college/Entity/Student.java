package uz.ilmnajot.college.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "studen")
public class Student extends BaseUUIDEntity {

    private String name;

    private String email;

    @OneToOne
    private College college;

}

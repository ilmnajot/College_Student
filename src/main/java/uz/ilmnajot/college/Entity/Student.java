package uz.ilmnajot.college.Entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.college.Entity.component.BaseLongEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class Student extends BaseLongEntity {

    private String name;

    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<College> colleges;

}

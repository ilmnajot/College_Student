package uz.ilmnajot.college.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.*;
import uz.ilmnajot.college.Entity.component.BaseLongEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class College extends BaseLongEntity {

    private String name;

    private int numberOfFaculty;

    private String region;

    private String address;
    @ManyToMany
    private List<Student> studentList;

}

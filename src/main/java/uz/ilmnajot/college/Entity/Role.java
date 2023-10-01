package uz.ilmnajot.college.Entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.college.Entity.component.BaseLongEntity;
import uz.ilmnajot.college.enums.Permissions;
import uz.ilmnajot.college.enums.RoleName;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@Builder
public class Role extends BaseLongEntity {

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    RoleName roleName;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Permissions> permissions = new ArrayList<>();

    private boolean active;
}

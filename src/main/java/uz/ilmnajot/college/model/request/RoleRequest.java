package uz.ilmnajot.college.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import uz.ilmnajot.college.Entity.Role;
import uz.ilmnajot.college.enums.Permissions;
import uz.ilmnajot.college.enums.RoleName;

import java.util.ArrayList;
import java.util.List;
@Data
public class RoleRequest {

    private String name;

    private Role role;

    private List<Permissions> permissions;

    private boolean active;


}

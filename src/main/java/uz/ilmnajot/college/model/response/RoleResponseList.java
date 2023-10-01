package uz.ilmnajot.college.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ilmnajot.college.Entity.Role;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseList {

    private List<Role> content;
    private long allSize;
    private int allPage;
    private int currentPage;
}

package uz.ilmnajot.college.model.request;

import lombok.Data;
import uz.ilmnajot.college.Entity.Role;

@Data
public class    UserRequest {

    private String name;

    private String email;

    private String phoneNumber;

    private String region;

    private String password;

    private Role role;

}

package uz.ilmnajot.college.model.response;

import lombok.Data;
import uz.ilmnajot.college.enums.RoleName;

import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    private String region;

    private RoleName roleName;
}


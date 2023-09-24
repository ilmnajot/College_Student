package uz.ilmnajot.college.model.request;

import lombok.Data;

@Data
public class    UserRequest {

    private String name;

    private String email;

    private String phoneNumber;

    private String region;

    public String password;
}

package uz.ilmnajot.college.model.response;

import lombok.Data;

@Data
public class StudentResponse {

    private Long id;

    private String name;

    private String email;

    private Long collegeId;
}

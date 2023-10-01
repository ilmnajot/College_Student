package uz.ilmnajot.college.model.response;

import lombok.Data;
import uz.ilmnajot.college.Entity.Student;

@Data
public class CollegeResponse {
    private Long id;
    private String name;
    private int numberOfFaculty;
    private String region;
    private String address;
    private StudentResponse student;
}

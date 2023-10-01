package uz.ilmnajot.college.model.request;

import lombok.Data;

@Data
public class CollegeRequest {

    private String name;
    private int numberOfFaculty;
    private String region;
    private String address;
}

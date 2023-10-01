package uz.ilmnajot.college.model.request;

import jakarta.persistence.OneToOne;
import lombok.Data;
import uz.ilmnajot.college.Entity.College;
import uz.ilmnajot.college.model.response.CollegeResponse;

@Data
public class StudentRequest {

    private String name;

    private String email;

    private CollegeRequest college;
}

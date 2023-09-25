package uz.ilmnajot.college.model.request;

import jakarta.persistence.OneToOne;
import lombok.Data;
import uz.ilmnajot.college.Entity.College;

@Data
public class StudentRequest {

    private String name;

    private String email;

    private College college;
}

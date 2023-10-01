package uz.ilmnajot.college.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponsePage {

    private List<StudentResponse> userResponseList;
    private long allSize;
    private int allPage;
    private int currentPage;
}

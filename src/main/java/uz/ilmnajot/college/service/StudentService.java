package uz.ilmnajot.college.service;
import org.springframework.stereotype.Service;
import uz.ilmnajot.college.model.common.ApiResponse;
import uz.ilmnajot.college.model.request.StudentRequest;
import uz.ilmnajot.college.repository.StudentRepository;

@Service
public class StudentService implements BaseService<StudentRequest, Long>{4
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public ApiResponse create(StudentRequest studentRequest) {

        return null;
    }

    @Override
    public ApiResponse getById(Long aLong) {
        return null;
    }

    @Override
    public ApiResponse getAll() {
        return null;
    }

    @Override
    public ApiResponse deleteById(Long aLong) {
        return null;
    }

    @Override
    public ApiResponse update(StudentRequest studentRequest, Long aLong) {
        return null;
    }
}

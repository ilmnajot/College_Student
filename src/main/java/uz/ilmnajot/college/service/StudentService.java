package uz.ilmnajot.college.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.ilmnajot.college.Entity.Student;
import uz.ilmnajot.college.Entity.User;
import uz.ilmnajot.college.model.common.ApiResponse;
import uz.ilmnajot.college.model.request.StudentRequest;
import uz.ilmnajot.college.model.response.StudentResponse;
import uz.ilmnajot.college.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements BaseService<StudentRequest, Long> {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse create(StudentRequest studentRequest) {
        Optional<Student> optionalStudent = studentRepository.findByEmail(studentRequest.getEmail());
        if (optionalStudent.isPresent()) {
            return new ApiResponse("student is already registered", false);
        }
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setCollege(studentRequest.getCollege());
        student.setDeleted(false);
        student.setBlocked(false);
        Student saved = studentRepository.save(student);
        StudentResponse studentResponse = modelMapper.map(saved, StudentResponse.class);
        return new ApiResponse("successfully registered", true, studentResponse);
    }

    @Override
    public ApiResponse getById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            StudentResponse studentResponse = modelMapper.map(student, StudentResponse.class);
            return new ApiResponse("success", true, studentResponse);
        }
        return new ApiResponse("there is no such student with id: " + id, false);
    }

    @Override
    public ApiResponse getAll() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentResponse> studentResponseList = new ArrayList<>();
        for (Student student : studentList) {
            StudentResponse studentResponse = modelMapper.map(student, StudentResponse.class);
            studentResponseList.add(studentResponse);
        }
        return new ApiResponse("success", true, studentResponseList);
    }

    @Override
    public ApiResponse deleteById(Long aLong) {
        Optional<Student> optionalStudent = studentRepository.findById(aLong);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setDeleted(true);
            student.setBlocked(true);
            Student savedStudent = studentRepository.save(student);
            StudentResponse studentResponse = modelMapper.map(savedStudent, StudentResponse.class);
            return new ApiResponse("success", true, studentResponse);
        }
        return new ApiResponse("there is no student with id : " + aLong, false);

    }

    @Override
    public ApiResponse update(StudentRequest studentRequest, Long aLong) {
        Student student = updateUserById(studentRequest, aLong);
        StudentResponse studentResponse = modelMapper.map(student, StudentResponse.class);
        return new ApiResponse("successfuly updated", true, studentResponse);
    }

    public Student updateUserById(StudentRequest request, Long id) {
        Optional<Student> optionalStudent = studentRepository.findByEmail(request.getEmail());
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setId(id);
            student.setName(request.getName());
            student.setEmail(request.getEmail());
            student.setCollege(request.getCollege());
            return studentRepository.save(student);
        }
        throw new UsernameNotFoundException("student not found");
    }
}

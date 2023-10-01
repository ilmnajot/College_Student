package uz.ilmnajot.college.database;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.ilmnajot.college.Entity.College;
import uz.ilmnajot.college.Entity.Role;
import uz.ilmnajot.college.Entity.Student;
import uz.ilmnajot.college.Entity.User;
import uz.ilmnajot.college.enums.Permissions;
import uz.ilmnajot.college.enums.RoleName;
import uz.ilmnajot.college.model.response.StudentResponse;
import uz.ilmnajot.college.model.response.UserResponse;
import uz.ilmnajot.college.repository.CollegeRepository;
import uz.ilmnajot.college.repository.RoleRepository;
import uz.ilmnajot.college.repository.StudentRepository;
import uz.ilmnajot.college.repository.UserRepository;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final CollegeRepository collegeRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Value("${spring.sql.init.mode}")
    private String mode;

    public DataLoader(
            CollegeRepository collegeRepository,
            StudentRepository studentRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            ModelMapper modelMapper, RoleRepository roleRepository) {
        this.collegeRepository = collegeRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {

            String password = passwordEncoder.encode("password");

            College college = collegeRepository.save(College
                    .builder()
                    .name("college name")
                    .numberOfFaculty(10)
                    .region("US")
                    .address("us")
                    .build());

            Student student = studentRepository.save(
                    Student
                            .builder()
                            .name("Student")
                            .email("student@example.com")
                            .colleges(List.of(college))
                            .build());
            modelMapper.map(student, StudentResponse.class);

            Permissions[] permissions = Permissions.values();
            Role userRole = roleRepository.save(
                    Role
                            .builder()
                            .name("USER")
                            .roleName(RoleName.USER)
                            .active(true)
                            .permissions(List.of(permissions))
                            .build());
            Role adminRole = roleRepository.save(
                    Role
                            .builder()
                            .name("ADMIN")
                            .roleName(RoleName.ADMIN)
                            .active(true)
                            .permissions(List.of(permissions))
                            .build());


            User user = userRepository.save(User
                    .builder()
                    .name("user")
                    .email("user@example.com")
                    .phoneNumber("123-456-789")
                    .region("US")
                    .password(password)
                    .role(userRole)
                    .build());
            modelMapper.map(user, UserResponse.class);

            User admin = userRepository.save(User
                    .builder()
                    .name("admin")
                    .email("admin@example.com")
                    .phoneNumber("123-456-789")
                    .region("US")
                    .password(password)
                    .role(adminRole)
                    .build());
            modelMapper.map(admin, UserResponse.class);

        }
    }
}

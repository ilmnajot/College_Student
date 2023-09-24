package uz.ilmnajot.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.college.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}

package uz.ilmnajot.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.college.Entity.College;

import java.util.Optional;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

    Optional<College> findCollegeByName(String name);
    Optional<College> findCollegeByNameAndId(String name, Long id);
}

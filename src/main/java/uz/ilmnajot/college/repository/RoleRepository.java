package uz.ilmnajot.college.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.college.Entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByName(String roleName);
    Optional<Role> findByNameAndActiveTrue(String roleName);
    Page<Role> findAllByActiveTrue(Pageable pageable);
}

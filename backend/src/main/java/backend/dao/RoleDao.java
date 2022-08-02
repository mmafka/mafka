package backend.dao;

import backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long id);

    Role findByRoleName(String roleName);
}

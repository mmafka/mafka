package backend.dao;

import backend.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDao extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByUserId(Long userId);
}

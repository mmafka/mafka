package backend.dao;

import backend.entity.RoleUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRoleDao extends JpaRepository<RoleUrl, Long> {
    List<RoleUrl> findAllByRoleId(Long roleId);
}

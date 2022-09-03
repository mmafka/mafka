package backend.dao;

import backend.entity.ClusterNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClusterNodeDao extends JpaRepository<ClusterNode, Long> {
}

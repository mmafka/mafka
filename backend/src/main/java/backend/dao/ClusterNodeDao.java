package backend.dao;

import backend.entity.ClusterNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClusterNodeDao extends JpaRepository<ClusterNode, Long> {
    ClusterNode findByNodeName(String clusterName);

    Optional<ClusterNode> findById(Long id);

    List<ClusterNode> findAllByStatus(Integer status);
}

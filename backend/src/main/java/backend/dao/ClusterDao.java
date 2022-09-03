package backend.dao;

import backend.entity.Cluster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClusterDao extends JpaRepository<Cluster, Long> {
    Cluster findByClusterName(String clusterName);

    Optional<Cluster> findById(Long id);

    List<Cluster> findAllByStatus(Integer status);
}

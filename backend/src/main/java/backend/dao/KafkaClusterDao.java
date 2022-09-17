package backend.dao;

import backend.entity.KafkaCluster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KafkaClusterDao extends JpaRepository<KafkaCluster, Long> {

    Optional<KafkaCluster> findById(Long id);

    List<KafkaCluster> findAllByStatus(Integer status);
}

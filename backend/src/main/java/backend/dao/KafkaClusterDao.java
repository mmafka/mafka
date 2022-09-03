package backend.dao;

import backend.entity.KafkaCluster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaClusterDao extends JpaRepository<KafkaCluster, Long> {
}

package backend.dao;

import backend.entity.KafkaClusterNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaClusterNodeDao extends JpaRepository<KafkaClusterNode, Long> {
}

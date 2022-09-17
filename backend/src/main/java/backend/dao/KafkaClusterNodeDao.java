package backend.dao;

import backend.entity.KafkaClusterNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KafkaClusterNodeDao extends JpaRepository<KafkaClusterNode, Long> {
    List<KafkaClusterNode> findAllByStatus(Integer status);
}

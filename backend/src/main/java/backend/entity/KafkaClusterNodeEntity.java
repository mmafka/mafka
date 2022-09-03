package backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class KafkaClusterNodeEntity extends BaseEntity {
    private long kafkaClusterId;
    private long clusterNodeId;
    private boolean zookeeper;
    private boolean kafkaBroker;
}

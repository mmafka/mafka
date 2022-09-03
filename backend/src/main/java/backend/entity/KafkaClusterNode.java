package backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class KafkaClusterNode extends BaseEntity {
    private long kafkaClusterId;
    private long clusterNodeId;
    private Boolean zookeeper;
    private Boolean kafkaBroker;
}

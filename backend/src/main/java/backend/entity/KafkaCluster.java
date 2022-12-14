package backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class KafkaCluster extends BaseEntity {
    private String kafkaClusterName;
    private Long clusterId;
}

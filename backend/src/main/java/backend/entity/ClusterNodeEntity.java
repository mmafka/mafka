package backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ClusterNodeEntity extends BaseEntity {
    private long clusterId;
    private String nodeName;
    private String nodeIp;
    private String userName;
    private boolean dockerStatus;
    private long ram;
    private long cpu;
    private long disk;
}

package backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ClusterNode extends BaseEntity {
    private long clusterId;
    private String nodeName;
    private String nodeIp;
    private String userName;
    private Boolean dockerStatus;
    private long ram;
    private long cpu;
    private long disk;
}

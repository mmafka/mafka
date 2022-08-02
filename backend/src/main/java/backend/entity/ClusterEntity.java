package backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ClusterEntity extends BaseEntity {
    private String clusterName;
}

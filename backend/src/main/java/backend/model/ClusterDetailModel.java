package backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClusterDetailModel extends BaseModel {
    private String clusterName;
    private int nodeCount;
    private long totalRam;
    private long totalDisk;
    private long totalCPU;
}

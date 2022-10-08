package backend.restcontroller;

import backend.dao.ClusterDao;
import backend.dao.ClusterNodeDao;
import backend.entity.Cluster;
import backend.entity.ClusterNode;
import backend.model.ClusterDetailModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cluster")
public class ClusterRestController {
    private ClusterDao clusterDao;
    private ClusterNodeDao clusterNodeDao;

    public ClusterRestController(ClusterDao clusterDao,ClusterNodeDao clusterNodeDao) {
        this.clusterDao = clusterDao;
        this.clusterNodeDao = clusterNodeDao;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody Cluster cluster) {
        Cluster currentCluster = null;
        if(cluster.getId()==null)
            currentCluster = new Cluster();
        else if (clusterDao.findById(cluster.getId()).isEmpty())
            currentCluster = new Cluster();
        else
            currentCluster = clusterDao.findById(cluster.getId()).get();
        currentCluster.setClusterName(cluster.getClusterName());
        clusterDao.save(currentCluster);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable("id") Long id) {
        if(clusterDao.findById(id).isPresent())
            clusterDao.delete(clusterDao.findById(id).get());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Cluster>> getAll() {
        return new ResponseEntity<>(clusterDao.findAllByStatus(1), HttpStatus.OK);
    }


    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(clusterDao.countAllByStatus(1), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllDetails/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<ClusterDetailModel>> getAllDetails(@PathVariable("page") int page) {
        List<ClusterDetailModel> clusterDetailModels = new ArrayList<>();
        int nodeCount = 0;
        long totalRam = 0;
        long totalDisk = 0;
        long totalCPU = 0;
        Pageable sortedByName;
        sortedByName = PageRequest.of(page, 10, Sort.by("lastModifiedDate"));
        List<Cluster> clusters = clusterDao.findAllByStatusOrderByCreationDateDesc(1,sortedByName);
        for(Cluster cluster : clusters)
        {
            ClusterDetailModel clusterDetailModel = new ClusterDetailModel();
            clusterDetailModel.setId(cluster.getId());
            clusterDetailModel.setClusterName(cluster.getClusterName());
            nodeCount = 0;
            totalRam = 0;
            totalDisk = 0;
            totalCPU = 0;
            for(ClusterNode clusterNode:clusterNodeDao.findByClusterId(cluster.getId()))
            {
                nodeCount += 1;
                totalRam += clusterNode.getRam();
                totalDisk += clusterNode.getDisk();
                totalCPU += clusterNode.getCpu();
            }
            clusterDetailModel.setTotalCPU(totalCPU);
            clusterDetailModel.setTotalDisk(totalDisk);
            clusterDetailModel.setTotalRam(totalRam);
            clusterDetailModel.setNodeCount(nodeCount);
            clusterDetailModels.add(clusterDetailModel);
        }

        return new ResponseEntity<>(clusterDetailModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cluster> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clusterDao.findById(id).isPresent() ? clusterDao.findById(id).get():null, HttpStatus.OK);
    }
}

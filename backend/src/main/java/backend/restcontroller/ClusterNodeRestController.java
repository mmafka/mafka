package backend.restcontroller;

import backend.dao.ClusterNodeDao;
import backend.entity.ClusterNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clusterNode")
public class ClusterNodeRestController {
    private ClusterNodeDao clusterNodeDao;

    public ClusterNodeRestController(ClusterNodeDao clusterNodeDao) {
        this.clusterNodeDao = clusterNodeDao;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody ClusterNode clusterNode) {
        ClusterNode currentClusterNodeDao = null;
        if (clusterNode.getId() == null)
            currentClusterNodeDao = new ClusterNode();
        else if (clusterNodeDao.findById(clusterNode.getId()).isEmpty())
            currentClusterNodeDao = new ClusterNode();
        else
            currentClusterNodeDao = clusterNodeDao.findById(clusterNode.getId()).get();
        currentClusterNodeDao.setClusterId(clusterNode.getClusterId());
        currentClusterNodeDao.setNodeIp(clusterNode.getNodeIp());
        currentClusterNodeDao.setNodeName(clusterNode.getNodeName());
        currentClusterNodeDao.setCpu(clusterNode.getCpu());
        currentClusterNodeDao.setDisk(clusterNode.getDisk());
        currentClusterNodeDao.setRam(clusterNode.getRam());
        currentClusterNodeDao.setDockerStatus(clusterNode.getDockerStatus());
        clusterNodeDao.save(currentClusterNodeDao);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable("id") Long id) {
        if (clusterNodeDao.findById(id).isPresent())
            clusterNodeDao.delete(clusterNodeDao.findById(id).get());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<ClusterNode>> getAll() {
        return new ResponseEntity<>(clusterNodeDao.findAllByStatus(1), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClusterNode> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clusterNodeDao.findById(id).isPresent() ? clusterNodeDao.findById(id).get() : null, HttpStatus.OK);
    }
}

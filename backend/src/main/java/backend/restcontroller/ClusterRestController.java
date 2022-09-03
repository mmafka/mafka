package backend.restcontroller;

import backend.dao.ClusterDao;
import backend.entity.Cluster;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cluster")
public class ClusterRestController {
    private ClusterDao clusterDao;

    public ClusterRestController(ClusterDao clusterDao) {
        this.clusterDao = clusterDao;
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

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cluster> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clusterDao.findById(id).isPresent() ? clusterDao.findById(id).get():null, HttpStatus.OK);
    }
}

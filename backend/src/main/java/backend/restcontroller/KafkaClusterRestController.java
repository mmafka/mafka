package backend.restcontroller;

import backend.dao.ClusterDao;
import backend.dao.KafkaClusterDao;
import backend.entity.Cluster;
import backend.entity.KafkaCluster;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kafkaCluster")
public class KafkaClusterRestController {
    private KafkaClusterDao kafkaClusterDao;
    private ClusterDao clusterDao;

    public KafkaClusterRestController(KafkaClusterDao kafkaClusterDao, ClusterDao clusterDao) {
        this.kafkaClusterDao = kafkaClusterDao;
        this.clusterDao = clusterDao;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody KafkaCluster kafkaCluster) {
        KafkaCluster currentKafkaCluster = null;
        if (kafkaCluster.getId() == null)
            currentKafkaCluster = new KafkaCluster();
        else if (kafkaClusterDao.findById(kafkaCluster.getId()).isEmpty())
            currentKafkaCluster = new KafkaCluster();
        else
            currentKafkaCluster = kafkaClusterDao.findById(kafkaCluster.getId()).get();
        currentKafkaCluster.setKafkaClusterName(kafkaCluster.getKafkaClusterName());
        Cluster cluster = clusterDao.findById(kafkaCluster.getClusterId()).isPresent() ? clusterDao.findById(kafkaCluster.getClusterId()).get() : null;
        if (cluster != null) {
            currentKafkaCluster.setClusterId(kafkaCluster.getClusterId());
            kafkaClusterDao.save(currentKafkaCluster);
        }

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable("id") Long id) {
        if (kafkaClusterDao.findById(id).isPresent())
            kafkaClusterDao.delete(kafkaClusterDao.findById(id).get());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<KafkaCluster>> getAll() {
        return new ResponseEntity<>(kafkaClusterDao.findAllByStatus(1), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<KafkaCluster> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(kafkaClusterDao.findById(id).isPresent() ? kafkaClusterDao.findById(id).get() : null, HttpStatus.OK);
    }
}

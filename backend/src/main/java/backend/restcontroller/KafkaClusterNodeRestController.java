package backend.restcontroller;

import backend.dao.ClusterDao;
import backend.dao.ClusterNodeDao;
import backend.dao.KafkaClusterDao;
import backend.dao.KafkaClusterNodeDao;
import backend.entity.Cluster;
import backend.entity.ClusterNode;
import backend.entity.KafkaCluster;
import backend.entity.KafkaClusterNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kafkaClusterNode")
public class KafkaClusterNodeRestController {
    private KafkaClusterDao kafkaClusterDao;
    private KafkaClusterNodeDao kafkaClusterNodeDao;
    private ClusterNodeDao clusterNodeDao;

    public KafkaClusterNodeRestController(KafkaClusterDao kafkaClusterDao, ClusterNodeDao clusterNodeDao, KafkaClusterNodeDao kafkaClusterNodeDao) {
        this.kafkaClusterDao = kafkaClusterDao;
        this.clusterNodeDao = clusterNodeDao;
        this.kafkaClusterNodeDao = kafkaClusterNodeDao;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody KafkaClusterNode kafkaClusterNode) {
        KafkaClusterNode currentKafkaClusterNode = null;
        if (kafkaClusterNode.getId() == null)
            currentKafkaClusterNode = new KafkaClusterNode();
        else if (kafkaClusterNodeDao.findById(kafkaClusterNode.getId()).isEmpty())
            currentKafkaClusterNode = new KafkaClusterNode();
        else
            currentKafkaClusterNode = kafkaClusterNodeDao.findById(kafkaClusterNode.getId()).get();
        currentKafkaClusterNode.setKafkaBroker(kafkaClusterNode.getKafkaBroker());
        currentKafkaClusterNode.setZookeeper(kafkaClusterNode.getZookeeper());
        KafkaCluster kafkaCluster = kafkaClusterDao.findById(kafkaClusterNode.getKafkaClusterId()).isPresent() ? kafkaClusterDao.findById(kafkaClusterNode.getKafkaClusterId()).get() : null;
        if (kafkaCluster != null) {
            currentKafkaClusterNode.setKafkaClusterId(kafkaClusterNode.getKafkaClusterId());
            ClusterNode clusterNode = clusterNodeDao.findById(kafkaClusterNode.getClusterNodeId()).isPresent() ? clusterNodeDao.findById(kafkaClusterNode.getClusterNodeId()).get() : null;
            if (clusterNode != null) {
                currentKafkaClusterNode.setClusterNodeId(kafkaClusterNode.getKafkaClusterId());
                kafkaClusterNodeDao.save(currentKafkaClusterNode);
            }
        }

            
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable("id") Long id) {
        if (kafkaClusterNodeDao.findById(id).isPresent())
            kafkaClusterNodeDao.delete(kafkaClusterNodeDao.findById(id).get());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<KafkaClusterNode>> getAll() {
        return new ResponseEntity<>(kafkaClusterNodeDao.findAllByStatus(1), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<KafkaClusterNode> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(kafkaClusterNodeDao.findById(id).isPresent() ? kafkaClusterNodeDao.findById(id).get() : null, HttpStatus.OK);
    }
}

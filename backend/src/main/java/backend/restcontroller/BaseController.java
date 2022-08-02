package backend.restcontroller;

import backend.dao.BaseDAO;
import backend.entity.BaseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseController<T extends BaseEntity, D extends BaseDAO<T>> {
    private D d;

    public BaseController(D d) {
        this.d = d;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody T t) {
        d.save(t);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<T>> getAll() {
        List<T> datas = d.findAll();
        List<T> result = new ArrayList<>();
        for (T t : datas)
            if (t.getStatus().equals(1))
                result.add(t);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<T> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(d.findById(id).get(), HttpStatus.OK);
    }
}

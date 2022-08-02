package backend.restcontroller;

import backend.dao.UserDAO;
import backend.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private UserDAO userDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserRestController(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody User user) {
        User currentUser = null;
        if (userDAO.findById(user.getId()).isEmpty())
            currentUser = new User();
        else
            currentUser = userDAO.findById(user.getId()).get();
        currentUser.setUserName(user.getUserName());
        currentUser.setRole("1");
        if (user.getPassword() != null) {
            currentUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userDAO.save(currentUser);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable("id") Long id) {
        userDAO.delete(userDAO.findById(id).get());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userDAO.findAllByStatus(1), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userDAO.findById(id).get(), HttpStatus.OK);
    }
}

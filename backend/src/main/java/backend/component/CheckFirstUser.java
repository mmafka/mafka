package backend.component;

import backend.dao.UserDAO;
import backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CheckFirstUser {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        User mafkaUser = userDAO.findByUserName("mafka");
        if(mafkaUser == null) {
            mafkaUser = new User();
            mafkaUser.setUserName("mafka");
            mafkaUser.setRole("1");
            mafkaUser.setPassword(bCryptPasswordEncoder.encode("mafka"));
            mafkaUser.setStatus(1);
            userDAO.save(mafkaUser);
        }
    }
}

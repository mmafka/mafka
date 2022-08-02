package backend.dao;

import backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    Optional<User> findById(Long id);

    List<User> findAllByStatus(Integer status);
}

package backend.dao;

import backend.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface BaseDAO<T extends BaseEntity> extends JpaRepository<T, Serializable> {
}

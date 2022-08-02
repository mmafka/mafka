package backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class UserRole extends BaseEntity {
    private Long userId;
    private Long roleId;
}

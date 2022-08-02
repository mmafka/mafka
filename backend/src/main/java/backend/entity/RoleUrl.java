package backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class RoleUrl extends BaseEntity {
    private String url;
    private Long roleId;
}

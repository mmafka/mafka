package backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="Users")
public class User extends BaseEntity {
    private String userName;
    private String password;
    private String role;
}

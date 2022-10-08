package backend.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BaseModel implements Serializable {
    private Long id;

    private Integer status = 1;

    private String createdBy;

    protected Date creationDate;

    private String modifiedBy;

    protected Date lastModifiedDate;
}

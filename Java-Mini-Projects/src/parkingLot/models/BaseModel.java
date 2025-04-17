package parkingLot.models;

import java.util.Date;

public class BaseModel {
    private Long id;
    private Date createdAt;
    private Date lastModifiedAt;

    public BaseModel() {
        this.createdAt = new Date();
        this.lastModifiedAt = new Date();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }
    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}

package github.spritecn.leanotJava.model;

public class BaseModel {
    private Long id;
    private Long createdTm;
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedTm() {
        return createdTm;
    }

    public void setCreatedTm(Long createdTm) {
        this.createdTm = createdTm;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}

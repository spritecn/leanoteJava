package github.spritecn.leanotJava.model;

public class TestModel extends BaseModel{
    private Long createdTm;
    private Boolean deleted;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

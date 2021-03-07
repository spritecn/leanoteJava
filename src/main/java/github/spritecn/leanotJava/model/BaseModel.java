package github.spritecn.leanotJava.model;

import lombok.Data;

@Data
public class BaseModel {
    private Long id;
    private Long createdTime;

    private Long updatedTime;

    private Boolean isDeleted;
}

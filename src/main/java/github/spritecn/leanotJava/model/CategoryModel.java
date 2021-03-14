package github.spritecn.leanotJava.model;

import github.spritecn.leanotJava.constant.CategoryTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryModel extends BaseModel {
    //分类
    private CategoryTypeEnum type;
    //名称
    private String title;
    //父id,考虑仅支持两级
    private String parentId;
    private Integer usn;
}

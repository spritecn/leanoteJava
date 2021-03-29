package github.spritecn.leanotJava.bean;


import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class SyncStateResponse extends BaseResponse {
    private Long LastSyncTime;
    private Integer LastSyncUsn;
}

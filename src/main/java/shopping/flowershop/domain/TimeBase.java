package shopping.flowershop.domain;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class TimeBase {
    protected String creator;
    protected LocalDateTime createdTime;
    protected String modifier;
    protected LocalDateTime modifiedTime;
    protected TimeBase(){
        this.createdTime = LocalDateTime.now();
        this.modifiedTime = LocalDateTime.now();
    }

}

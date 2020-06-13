package sso.mac.alcoholic.comm.entity.base;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -3138916241322988793L;
    private static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    @Column(length = 20, updatable = false, nullable = false)
    private String created;

    @Column(length = 50, updatable = false, nullable = false)
    private String createdBy;

    @Column(length = 20, nullable = false)
    private String updated;

    @Column(length = 50, nullable = false)
    private String updatedBy;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    @PrePersist
    protected void onCreate(){
        created = LocalDateTime.now().format(DateTimeFormatter.ofPattern(yyyyMMddHHmmss));
        used = Used.Y;

        // 인증 정보 사용자 ID
        updated = created;
        createdBy = "creater";
        updatedBy = "creater";
    }

    @PreUpdate
    protected void onUpdate(){
        updated = LocalDateTime.now().format(DateTimeFormatter.ofPattern(yyyyMMddHHmmss));

        // 인증 정보 사용자 ID
        updatedBy = "updater";
    }
}

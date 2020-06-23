package sso.mac.alcoholic.comm.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import sso.mac.alcoholic.comm.entity.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert @DynamicUpdate
@EqualsAndHashCode(callSuper=false)
public class Account extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4924173125796108591L;

    @Id @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 50)
    private String nickname;

    @Column(length = 30)
    private String email;

    @Column(length = 10)
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<AccountRole> roles;
}

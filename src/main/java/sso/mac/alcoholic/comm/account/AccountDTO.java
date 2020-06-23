package sso.mac.alcoholic.comm.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sso.mac.alcoholic.comm.entity.AccountRole;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO implements Serializable {
    private static final long serialVersionUID = -7385452766874106083L;

    private String password;
    private String username;
    private String nickname;
    private String email;
    private Set<AccountRole> roles;
}

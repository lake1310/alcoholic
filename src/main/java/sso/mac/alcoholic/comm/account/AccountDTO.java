package sso.mac.alcoholic.comm.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AccountDTO implements Serializable {
    private static final long serialVersionUID = -7385452766874106083L;

    private String password;
    private String username;
    private String nickname;
    private String phoneNumber;
    private String role;
}

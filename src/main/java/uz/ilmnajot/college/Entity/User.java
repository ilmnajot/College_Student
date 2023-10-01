package uz.ilmnajot.college.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.ilmnajot.college.Entity.component.BaseUUIDEntity;
import uz.ilmnajot.college.enums.Permissions;
import uz.ilmnajot.college.enums.RoleName;

import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseUUIDEntity implements UserDetails {

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String region;

    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

    private boolean enabled = false;

    private String emailCode;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permissions> permissions = this.role.getPermissions();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (Permissions permission : permissions) {
                grantedAuthorityList.add(new SimpleGrantedAuthority(permission.name()));
        }
        return grantedAuthorityList;
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}



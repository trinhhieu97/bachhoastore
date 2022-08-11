package vn.com.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserChangePassword {
    String oldPassword;
    String newPassword;
}

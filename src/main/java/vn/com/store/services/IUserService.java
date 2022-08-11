package vn.com.store.services;

import vn.com.store.repository.documents.User;

public interface IUserService {
    User register(User user);

    User login(User user);

    User update(User user);

    User changePassword(String email, String oldPassword, String newPassword);

    User updateAvatar(String userId, String avatarUrl);

    User forgetPassword(String email); // gửi password mới qua email. ( phần gửi qua email chưa học nên sẽ config gửi sau)
}

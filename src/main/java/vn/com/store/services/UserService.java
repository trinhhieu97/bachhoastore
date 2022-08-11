package vn.com.store.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.com.store.repository.UserRepo;
import vn.com.store.repository.documents.User;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService{
    private UserRepo userRepo;

    @Override
    public User register(User user) {
        if (userRepo.findByEmail(user.getEmail())==null){
            return userRepo.save(user);
        }
        // TODO: throw ra exception
        return null;
    }

    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        // những thông tin gì theo em update là hợp lý name, birthday, mobile, describle
        User user1;
        if (!(userRepo.findByEmail(user.getEmail())==null)) {
            user1 = userRepo.findByEmail(user.getEmail());
            if (user.getName() != null) {
                user1.setName(user.getName());
            }
            if (user.getBirthday() != null) {
                user1.setBirthday(user.getBirthday());
            }
            if (user.getMobile() != null) {
                user1.setMobile(user.getMobile());
            }
            if (user.getDescription() != null) {
                user1.setDescription(user.getDescription());
            }
            userRepo.save(user1);
            return user1;
        }
        return null;
    }

    @Override
    public User changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepo.findByEmail(email);
        if (user!=null) {
            if (oldPassword.equals(user.getPassword())){
                user.setPassword(newPassword);
                userRepo.save(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public User updateAvatar(String userId, String avatarUrl) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) { // kiểm tra xem nó có bị null không .
            User user = userOptional.get();
                user.setAvatarUrl(avatarUrl);
                userRepo.save(user);
                return user;
            }
        return null;
    }

    @Override
    public User forgetPassword(String email) {
        User user = userRepo.findByEmail(email);
        if (user!=null) {
                user.setPassword("abcd");
            // TODO: thực hiện gửi mã code qua email, user phải xác thực
                userRepo.save(user);
                return user;
        }
        return null;
    }
}

package org.example.jobseekingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.jobseekingsystem.Model.User;
import org.example.jobseekingsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Boolean updateUser(Integer user_id,User user){
        User oldUser=null;
        for (User u:getAllUsers()){
            if (u.getId().equals(user_id)){
                oldUser=u;
            }
        }

        if (oldUser==null)
            return false;

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setAge(user.getAge());
        oldUser.setRole(user.getRole());

        userRepository.save(oldUser);

        return true;
    }

    public Boolean deleteUser(Integer user_id){
        User user=null;
        for (User u:getAllUsers()){
            if (u.getId().equals(user_id)){
                user=u;
                break;
            }
        }

        if (user==null)
            return false;

        userRepository.delete(user);
        return true;
    }


}

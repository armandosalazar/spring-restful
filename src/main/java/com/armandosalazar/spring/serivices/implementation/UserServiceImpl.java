package com.armandosalazar.spring.serivices.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.armandosalazar.spring.dto.UserDTO;
import com.armandosalazar.spring.dto.UserRequest;
import com.armandosalazar.spring.entities.User;
import com.armandosalazar.spring.repository.UserRepository;
import com.armandosalazar.spring.serivices.interfaces.UserService;
import com.armandosalazar.spring.utils.hash.BCrypt;
import com.armandosalazar.spring.utils.helpers.MHelpers;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> usersDtos = new ArrayList<>();
        Iterable<User> users = this.userRepository.findAll();
        for (User user : users) {
            usersDtos.add(MHelpers.modelMapper.map(user, UserDTO.class));
        }
        return usersDtos;
    }

    @Override
    public UserDTO findByUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return null;
        }
        return MHelpers.modelMapper.map(user.get(), UserDTO.class);
    }

    @Override
    public UserDTO findById(Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent()) {
            return null;
        }
        return MHelpers.modelMapper.map(user.get(), UserDTO.class);
    }

    @Override
    public void save(UserRequest user) {
        // Use BCrypt to encrypt the password
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        this.userRepository.save(MHelpers.modelMapper.map(user, User.class));
    }

    @Override
    public void update(UserRequest user, Integer id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return;
        }
        User userEntity = userOptional.get();
        userEntity.setName(user.getName());
        userEntity.setLastName(user.getLastName());
        userEntity.setUsername(user.getUsername());
        if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
            userEntity.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        }
        this.userRepository.save(userEntity);
    }

    @Override
    public void saveAll(List<UserRequest> users) {
        List<User> usersToSave = new ArrayList<>();
        for (UserRequest user : users) {
            User userToSave = MHelpers.modelMapper.map(user, User.class);
            usersToSave.add(userToSave);
        }
        this.userRepository.saveAll(usersToSave);
    }

    @Override
    public void deleteById(Integer id) {
        this.userRepository.deleteById(id);
    }

    // private UserDTO convertToUserDTO(final User user) {
    // return MHelpers.modelMapper.map(user, UserDTO.class);
    // }

}

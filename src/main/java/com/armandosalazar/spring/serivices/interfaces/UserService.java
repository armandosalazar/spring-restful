package com.armandosalazar.spring.serivices.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armandosalazar.spring.dto.UserDTO;
import com.armandosalazar.spring.dto.UserRequest;

@Service
public interface UserService {
    List<UserDTO> findAll();

    UserDTO findByUsername(String username);

    UserDTO findById(Integer id);

    void save(UserRequest user);

    void update(UserRequest user, Integer id);

    void saveAll(List<UserRequest> users);

    void deleteById(Integer id);
}

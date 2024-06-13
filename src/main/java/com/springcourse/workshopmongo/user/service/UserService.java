package com.springcourse.workshopmongo.user.service;

import com.springcourse.workshopmongo.exceptions.NotFoundException;
import com.springcourse.workshopmongo.user.dto.UserDTO;
import com.springcourse.workshopmongo.user.model.User;
import com.springcourse.workshopmongo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }

        throw new NotFoundException("Objeto não encontrado");
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById((id));
    }

    public User update(User updatedUser) {
        User user = findById(updatedUser.getId());

        user.setEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());
        return userRepository.save(user);
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}

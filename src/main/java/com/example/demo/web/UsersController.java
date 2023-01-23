package com.example.demo.web;

import com.example.demo.model.User;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    public String getAllUsers(Model model){
        if(!this.usersRepository.findAll().isEmpty()){
            model.addAttribute("listUsers", this.usersRepository.findAll());
            return "showUsers";
        }
        else {
            model.addAttribute("message", "List of users is empty!");
            return "error";
        }
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable(value = "id") Long userId, Model model){
        if(this.usersRepository.findById(userId).isPresent()){
            User user = this.usersRepository.findById(userId).orElseThrow();
            model.addAttribute("user", user);
            model.addAttribute("header", "Found user:");
            return "showUser";
        }
        else{
            model.addAttribute("message", "User with id: " + userId +" not found!");
            return "error";
        }
    }

    @GetMapping("/users/add")
    public String createUser(Model model){
        model.addAttribute("user", new User());
        return "createUser";
    }
    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user, Model model){
        usersRepository.save(user);
        model.addAttribute("user", user);
        model.addAttribute("header", "Created user:");
        return "showUser";
    }

    @GetMapping("/users/update/{id}")
    public String recreateUser(@PathVariable (value="id") Long userId, Model model){
        if(this.usersRepository.findById(userId).isPresent()){
            User user = this.usersRepository.findById(userId).orElseThrow();
            model.addAttribute("existingUser", user);
            model.addAttribute("updatedUser", new User());

            return "updateUser";
        }
        else{
            model.addAttribute("message", "User with id: " + userId +" not found!");
            return "error";
        }
    }
    @PostMapping("/users/update/{id}")
    public String updateUser(@ModelAttribute User user, @PathVariable (value="id") Long userId, Model model){
        if(this.usersRepository.findById(userId).isPresent()){
            User existingUser = this.usersRepository.findById(userId).orElseThrow();

            existingUser.setName(user.getName());
            existingUser.setSurname(user.getSurname());
            this.usersRepository.save(existingUser);

            model.addAttribute("user", existingUser);
            model.addAttribute("header", "Updated user");
            return "showUser";
        }
        else{
            model.addAttribute("message", "User with id: " + userId +" not found!");
            return "error";
        }

    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable ("id") Long userId, Model model){
        if(this.usersRepository.findById(userId).isPresent()){
            User existingUser = this.usersRepository.findById(userId).orElseThrow();
            this.usersRepository.delete(existingUser);
            model.addAttribute("message", "User with id: " + userId +" has been deleted.");
        }
        else{
            model.addAttribute("message", "User with id: " + userId +" not found!");
        }

        return "error";
    }
}

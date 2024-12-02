package org.example.jobseekingsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jobseekingsystem.ApiResponse.ApiResponse;
import org.example.jobseekingsystem.Model.User;
import org.example.jobseekingsystem.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/job-seeking-system/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers(){
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getAllUsers()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        userService.addUser(user);
        return ResponseEntity.status(201).body(new ApiResponse<>("User created successfully"));
    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity<ApiResponse<String>> updateUser(@PathVariable Integer user_id, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        boolean isUpdated= userService.updateUser(user_id,user);

        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse<>("User updated successfully"));

        return ResponseEntity.status(404).body(new ApiResponse<>("User not found"));
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Integer user_id){
        boolean isDeleted = userService.deleteUser(user_id);

        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse<>("User deleted successfully"));

        return ResponseEntity.status(404).body(new ApiResponse<>("User not found"));
    }
}

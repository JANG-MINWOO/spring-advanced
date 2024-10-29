package org.example.expert.domain.user.dto.response;

import lombok.Getter;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.User;

@Getter
public class UserResponse {

    private final Long id;
    private final String email;

    public UserResponse(Todo todo) {
        this.id = todo.getUser().getId();
        this.email = todo.getUser().getEmail();
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }
}

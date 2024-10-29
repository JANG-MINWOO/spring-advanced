package org.example.expert.domain.common.exist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.manager.entity.Manager;
import org.example.expert.domain.manager.repository.ManagerRepository;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.repository.UserRepository;

@Getter
@RequiredArgsConstructor
public class ExistCheck {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;

    public Todo isExistTodo(long todoId){
        return todoRepository.findById(todoId).orElseThrow(() -> new InvalidRequestException("Todo not found"));
    }

    public User isExistUser(long userId){
        return userRepository.findById(userId).orElseThrow(() -> new InvalidRequestException("User not found"));
    }

    public Manager isExistManager(long userId){
        return managerRepository.findById(userId).orElseThrow(() -> new InvalidRequestException("Manager not found"));
    }

}

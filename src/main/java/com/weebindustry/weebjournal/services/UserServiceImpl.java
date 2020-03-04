package com.weebindustry.weebjournal.services;

import java.util.*;

import com.weebindustry.weebjournal.exceptions.ResourceNotFoundException;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.repositories.UserRepository;
import com.weebindustry.weebjournal.util.HelperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements HelperService<User> {

    private final UserRepository repo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository repo) {
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
    }

    @Override
    public List<User> list() {
        return repo.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public User findById(Long id) {

        Optional<User> result = repo.findById(id);

        if (!result.isPresent()) {
            
            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("User not found :: " + id);
        }
        return result.get();
    }

    @Override
    public User create(User type) {
        type.setPassword(passwordEncoder.encode(type.getPassword()));
        return repo.save(type);
    }

    @Override
    public User update(Long id, User type) {

        if (!repo.findById(id).isPresent()) {
            
            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("User not found :: " + id);
            
        }

        return repo.save(type);
    }

	@Override
	public void delete(Long id) {
		if (!repo.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("User not found :: " + id);
        }

        repo.deleteById(id);
	}

    
}
package nz.co.vector.users.api;

import nz.co.vector.users.model.User;
import nz.co.vector.users.model.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class UsersApiDelegateImpl implements UsersApiDelegate {

    @Autowired
    UsersRepository repository;

    @Override
    public ResponseEntity<Void> create(User user) {
        try {
            // PK duplication
            if (repository.existsById(user.getEmail())) {
                return ResponseEntity.badRequest().build();
            }
            User newUser = repository.saveAndFlush(user);
            URI location = URI.create(String.format("/users/%s", newUser.getEmail()));
            return ResponseEntity.created(location).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Void> delete(String email) {
        try{
            if (repository.existsById(email))
                repository.deleteById(email);
            return ResponseEntity.noContent().build();
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<User> retrieve(String email) {
        try {
            Optional<User> user = repository.findById(email);
            return ResponseEntity.of(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Void> update(String email, User user) {
        try {
            if (repository.existsById(email)) {
                // primary key has changed
                if (!email.equals(user.getEmail()))
                    repository.deleteById(email);
                repository.save(user);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

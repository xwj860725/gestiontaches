package fr.xwj860725.gestiontaches.services;

import fr.xwj860725.gestiontaches.models.User;
import fr.xwj860725.gestiontaches.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User registerUser(User user) {
        // Check if the same mailbox or user name already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent() ||
            userRepository.findByName(user.getName()).isPresent()) {
            throw new RuntimeException("Ce mail ou le nom d'utilisateur est déjà utilisé, veuillez changer votre email ou votre nom d'utilisateur.");
        }
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            // unregistered user
            throw new RuntimeException("Cet utilisateur n'existe pas, veuillez vous inscrirez");
        }
        User user = optionalUser.get();
        if (!user.getPassword().equals(password)) {
            // password is wrong
            throw new RuntimeException("Connexion échoué, veuillez vérifier le nom d'utilisateur et le mot de passe");
        }
        return user;
    }
}

package com.cursospring.bibliopelis.services;

import com.cursospring.bibliopelis.model.Usuario;
import com.cursospring.bibliopelis.repository.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    private final IUsuarioRepository userRepository;

    public UserServices(IUsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        return userRepository.save(usuario);
    }
    public List<Usuario> getAllUsuarios() {
        return userRepository.findAll();
    }

    public Optional<Usuario> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Usuario getUsuarioById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}

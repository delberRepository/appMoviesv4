
package com.cursospring.bibliopelis.controller;
import com.cursospring.bibliopelis.model.Usuario;
import com.cursospring.bibliopelis.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {


    private final UserServices us;

    public UsuarioController(UserServices us) {
        this.us = us;
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return us.crearUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return us.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable int id) {
        return us.getUsuarioById(id);
    }
}
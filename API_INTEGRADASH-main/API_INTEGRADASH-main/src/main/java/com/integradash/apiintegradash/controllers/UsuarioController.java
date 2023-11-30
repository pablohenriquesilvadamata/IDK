package com.integradash.apiintegradash.controllers;

import com.integradash.apiintegradash.dtos.UsuarioDto;
import com.integradash.apiintegradash.models.UsuarioModel;
import com.integradash.apiintegradash.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

    @RestController
    @RequestMapping(value = "/erro", produces = {"application/json"})
    public class UsuarioController {
        @Autowired
        UsuarioRepository usuarioRepository;

        @GetMapping
        public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
        }

        @GetMapping("/{idErro}")
        public ResponseEntity<Object> exibirUsuario(@PathVariable(value = "idErro") UUID id) {
            Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

            if (usuarioBuscado.isEmpty()) {
                // Retornar usuário não encontrado
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro nao foi achado");
            }

            return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
        }

        @PostMapping
        public ResponseEntity<Object> cadastrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
            if ( usuarioRepository.findByEmail(usuarioDto.id_erro()) != null ) {
                // Não pode cadastrar
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Esse email já está cadastrado");
            }

            UsuarioModel usuario = new UsuarioModel();
            BeanUtils.copyProperties(usuarioDto, usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
        }

        @PutMapping("/{idErro}")
        public ResponseEntity<Object> editarUsuario(@PathVariable(value = "idErro") UUID id, @RequestBody @Valid UsuarioDto usuarioDto) {
            Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

            if ( usuarioBuscado.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }

            UsuarioModel usuario = usuarioBuscado.get();
            BeanUtils.copyProperties(usuarioDto, usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
        }

        @DeleteMapping("/{idErro}")
        public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "idErro") UUID id) {
            Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

            if ( usuarioBuscado.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro nao achado");
            }

            usuarioRepository.delete(usuarioBuscado.get());

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro resolvido!");
        }
}

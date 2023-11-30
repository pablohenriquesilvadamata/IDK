package com.integradash.apiintegradash.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "erro")
public class ErroModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_erro", nullable = false)
    private String titulo;
    private String descricao;
    private String proposta;
    private String status_servico;
    private UUID id_cliente;
    private UUID id_dev;
    private UUID id_erro;


    // private String url_img;

    // Provisóriamente String, será alterado para TipoModel enum


}

package br.com.pedido.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STR_NOME", length = 10)
    private String nome;

    @Column(name = "STR_DESCRICAO", length = 25)
    private String descricao;

    @Column(name = "B_STATUS")
    private Boolean status;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> usuarios = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<User> usuarios) {
        this.usuarios = usuarios;
    }
}

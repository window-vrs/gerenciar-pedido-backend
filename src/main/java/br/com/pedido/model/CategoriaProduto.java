package br.com.pedido.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "TB_CATEGORIA_PRODUTO")
@Data
@EntityListeners(AuditingEntityListener.class)
public class CategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="STR_NOME", nullable = false, length = 100)
    private String nome;

    @Column(name="STR_DESCRICAO", length = 255)
    private String descricao;

    @Column(name="B_STATUS", nullable = false)
    private Boolean ativo = true;

    @OneToMany(
        mappedBy = "categoria",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @ToString.Exclude
    private List<Produto> produtos = new ArrayList<>();

}

package br.com.pedido.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TB_PRODUTO")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "ID_CATEGORIA",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_produtos_categoria")
    )
    private CategoriaProduto categoria;

    @Column(name = "STR_nome",nullable = false, length = 150)
    private String nome;

    @Column(name = "STR_descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "D_preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "IT_estoque", nullable = false)
    private Integer estoque = 0;

    @Column(name = "STR_codigo_barras", length = 50, unique = true, nullable = false)
    private String codigoBarras;

    @Column(name = "B_STATUS",nullable = false)
    private Boolean status = true;

    @CreatedDate
    @Column(name = "DT_CADASTRO", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "DT_ATUALIZADO")
    private LocalDateTime atualizacao;

    @PreUpdate
    protected void onUpdate() {
        atualizacao = LocalDateTime.now();
    }

}

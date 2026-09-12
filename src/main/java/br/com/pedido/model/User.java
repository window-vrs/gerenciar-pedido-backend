package br.com.pedido.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TB_USER")
@Data
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2642039280063196901L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "USERNAME", length = 20)
    private String username;

    @Column(name = "STR_OBSERVACAO", length = 200)
    private String observacao;

    @Column(name = "PASSWORD", length = 255)
    private String password;

    @CreatedDate
    @Column(name = "DT_CADASTRO")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDateTime dataCadastro;

    //@LastModifiedDate
    @Column(name = "DT_ULTIMA_ATUALIZACAO")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDateTime atualizacao;
    
    @Column(name = "STATUS")
    private Boolean status;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "RL_ROLE_USUARIO",
        joinColumns = @JoinColumn(name = "ID_USUARIO"),
        inverseJoinColumns = @JoinColumn(name = "ID_ROLE")
    )    
    private Set<Role> roles = new HashSet<>();
    
    //@PrePersist
    //protected void onCreate() {
    //    this.dataCadastro = LocalDateTime.now();
    //}
    
    
    @PreUpdate
    protected void onUpdate() {
        atualizacao = LocalDateTime.now();
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<GrantedAuthority> rolesauto = new HashSet<GrantedAuthority>();
		if (!roles.isEmpty()) {
			for (Role role : roles) {
				rolesauto.add(new SimpleGrantedAuthority(role.getNome()));
			}
		}
		
		return rolesauto;
	}

	@Override
	public String getUsername() {
		return username;
	}


}

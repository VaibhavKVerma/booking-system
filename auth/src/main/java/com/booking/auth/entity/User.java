package com.booking.auth.entity;

import com.booking.common.enums.auth.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("is_active = true")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    @NotNull
    private String password;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserRole role = UserRole.USER;

    @Column(updatable = false)
    private Instant createdDate;

    private Instant modifiedDate;

    private String createdBy;
    private String modifiedBy;

    @PrePersist
    public void onPrePersist() {
        Instant now = Instant.now();
        this.createdDate = now;
        this.modifiedDate = now;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.modifiedDate = Instant.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isActive;
    }
}
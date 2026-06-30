package com.inspire.blog_jpa.features.user.domain.entity;


import java.util.ArrayList;
import java.util.List;

import com.inspire.blog_jpa.features.blog.domain.entity.BlogEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "JPA_USER_TBL")
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  @Id
  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false, length = 200)
  private String password;

  @Column(length = 30)
  private String name;

  @Column(length = 50)
  private String role;

  // 부모가 삭제되도 삭제하지 않음(orphanRemoval = false)
  @OneToMany(mappedBy = "author", orphanRemoval = false)
  private List<BlogEntity> blogs = new ArrayList<>();
}

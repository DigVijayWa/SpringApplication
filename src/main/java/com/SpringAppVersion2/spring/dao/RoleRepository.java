package com.SpringAppVersion2.spring.dao;

import com.SpringAppVersion2.model.bean.Role;
import com.SpringAppVersion2.model.bean.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
  Optional<Role> findByName(RoleName roleName);

}

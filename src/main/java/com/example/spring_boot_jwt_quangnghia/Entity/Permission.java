package com.example.spring_boot_jwt_quangnghia.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "table_permission")
@Getter
@Setter
public class Permission extends BaseEntity {
    private String permissionName, permissionKey;
}

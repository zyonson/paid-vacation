package com.zyonson.example.paidvacation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class Users extends EntityBase{

	/** ID */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** ログインID */
	@Column(name="username", nullable=false)
	private String username;

	/** パスワード */
	@Column(name="password", nullable=false)
	private String password;

	/** 権限　*/
	@Column(name="enabled", nullable=false)
	private int  enable;

	/** 苗字*/
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	/** 名前 */
	@Column(name="last_name", nullable=false)
	private String lastName;

	/** メールアドレス */
	@Column(name="email", nullable=false)
	private String email;

}

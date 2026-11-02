package com.phoneshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MauSac")
public class MauSac {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaMauSac")
	private Integer maMauSac;

	@Column(name = "TenMau", nullable = false, unique = true)
	private String tenMau;

	public MauSac() {
	}

	public Integer getMaMauSac() {
		return maMauSac;
	}

	public void setMaMauSac(Integer maMauSac) {
		this.maMauSac = maMauSac;
	}

	public String getTenMau() {
		return tenMau;
	}

	public void setTenMau(String tenMau) {
		this.tenMau = tenMau;
	}
}

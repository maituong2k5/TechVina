package com.phoneshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Rom")
public class Rom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaRom")
	private Integer maRom;

	@Column(name = "DungLuong", nullable = false, unique = true)
	private Integer dungLuong;

	public Rom() {
	}

	public Integer getMaRom() {
		return maRom;
	}

	public void setMaRom(Integer maRom) {
		this.maRom = maRom;
	}

	public Integer getDungLuong() {
		return dungLuong;
	}

	public void setDungLuong(Integer dungLuong) {
		this.dungLuong = dungLuong;
	}
}

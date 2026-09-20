package com.phoneshop.entity;

//Import các annotation của JPA để ánh xạ Java class với bảng trong database
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity: Đánh dấu class này là một Entity của JPA
//JPA sẽ quản lý đối tượng Ram và ánh xạ nó với một bảng trong database
@Entity

//@Table(name = "Ram"): Chỉ định Entity Ram tương ứng với bảng "Ram" trong database
@Table(name = "Ram")
public class Ram {

	// @Id: Xác định đây là khóa chính (Primary Key) của bảng
	@Id

	// @GeneratedValue: Cho phép database tự động sinh giá trị cho khóa chính
	// GenerationType.IDENTITY: sử dụng cơ chế AUTO_INCREMENT của MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// @Column(name = "MaRam"): Ánh xạ thuộc tính maRam với cột MaRam trong database
	@Column(name = "MaRam")
	private Integer maRam;

	// Ánh xạ thuộc tính dungLuong với cột DungLuong trong database
	// nullable = false: Không cho phép giá trị NULL
	// unique = true: Không cho phép hai bản ghi có cùng dung lượng RAM
	@Column(name = "DungLuong", nullable = false, unique = true)
	private Integer dungLuong;

	// Constructor không tham số
	// JPA yêu cầu Entity phải có constructor không tham số
	public Ram() {
	}

	// Getter: Lấy giá trị của maRam
	public Integer getMaRam() {
		return maRam;
	}

	// Setter: Gán giá trị cho maRam
	public void setMaRam(Integer maRam) {
		this.maRam = maRam;
	}

	// Getter: Lấy giá trị của dungLuong
	public Integer getDungLuong() {
		return dungLuong;
	}

	// Setter: Gán giá trị cho dungLuong
	public void setDungLuong(Integer dungLuong) {
		this.dungLuong = dungLuong;
	}
}

package com.phoneshop.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phoneshop.dto.DangKyKhachHangRequestDTO;
import com.phoneshop.dto.DangKyKhachHangResponseDTO;
import com.phoneshop.dto.DangNhapRequestDTO;
import com.phoneshop.dto.DangNhapResponseDTO;
import com.phoneshop.entity.KhachHang;
import com.phoneshop.entity.NhanVien;
import com.phoneshop.entity.RefreshToken;
import com.phoneshop.entity.VaiTro;
import com.phoneshop.exception.InvalidCredentialsException;
import com.phoneshop.repository.KhachHangRepository;
import com.phoneshop.repository.NhanVienRepository;
import com.phoneshop.repository.VaiTroRepository;

/**
 * Service xử lý nghiệp vụ xác thực tài khoản. Bao gồm đăng ký, đăng nhập và
 * phân quyền người dùng.
 */
@Service
public class AuthService {

	// Repository truy vấn thông tin khách hàng.
	private final KhachHangRepository khachHangRepository;

	// Repository truy vấn thông tin nhân viên.
	private final NhanVienRepository nhanVienRepository;

	// Repository truy vấn các vai trò trong hệ thống.
	private final VaiTroRepository vaiTroRepository;

	// Thành phần mã hóa mật khẩu bằng BCrypt.
	private final PasswordEncoder passwordEncoder;

	// Service chuyên xử lý tạo và xác thực JWT.
	private final JwtService jwtService;

	// Service chuyên xử lý Refresh Token.
	private final RefreshTokenService refreshTokenService;

	/**
	 * Constructor Injection: Spring tự động cung cấp các Repository.
	 */
	public AuthService(KhachHangRepository khachHangRepository, NhanVienRepository nhanVienRepository,
			VaiTroRepository vaiTroRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
			RefreshTokenService refreshTokenService) {

		this.khachHangRepository = khachHangRepository;
		this.nhanVienRepository = nhanVienRepository;
		this.vaiTroRepository = vaiTroRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.refreshTokenService = refreshTokenService;
	}

	/**
	 * Kiểm tra email và số điện thoại đã được sử dụng chưa.
	 *
	 * @param request dữ liệu khách hàng gửi lên khi đăng ký
	 */
	private void kiemTraThongTinDangKy(DangKyKhachHangRequestDTO request) {

		// Kiểm tra email đã tồn tại trong bảng KhachHang.
		if (khachHangRepository.existsByEmail(request.getEmail())) {
			throw new IllegalArgumentException("Email đã được sử dụng");
		}

		// Kiểm tra email đã tồn tại trong bảng NhanVien.
		if (nhanVienRepository.existsByEmail(request.getEmail())) {
			throw new IllegalArgumentException("Email đã được sử dụng");
		}

		// Kiểm tra số điện thoại đã tồn tại trong bảng KhachHang.
		if (khachHangRepository.existsBySoDienThoai(request.getSoDienThoai())) {
			throw new IllegalArgumentException("Số điện thoại đã được sử dụng");
		}
	}

	/**
	 * Lấy vai trò CUSTOMER từ database.
	 *
	 * @return đối tượng VaiTro tương ứng với CUSTOMER
	 */
	private VaiTro layVaiTroKhachHang() {

		// Tìm vai trò CUSTOMER theo tên.
		VaiTro vaiTro = vaiTroRepository.findByTenVaiTro("CUSTOMER");

		// Kiểm tra vai trò có tồn tại trong database không.
		if (vaiTro == null) {
			throw new IllegalStateException("Không tìm thấy vai trò CUSTOMER trong hệ thống");
		}

		return vaiTro;
	}

	/**
	 * Tạo đối tượng KhachHang từ dữ liệu đăng ký.
	 *
	 * @param request dữ liệu khách hàng gửi lên
	 * @param vaiTro  vai trò CUSTOMER lấy từ database
	 * @return đối tượng KhachHang đã thiết lập thông tin
	 */
	private KhachHang taoKhachHang(DangKyKhachHangRequestDTO request, VaiTro vaiTro) {

		// Khởi tạo đối tượng khách hàng mới.
		KhachHang khachHang = new KhachHang();

		// Thiết lập thông tin cá nhân.
		khachHang.setHoTen(request.getHoTen());
		khachHang.setEmail(request.getEmail());
		khachHang.setSoDienThoai(request.getSoDienThoai());
		khachHang.setNgaySinh(request.getNgaySinh());
		khachHang.setGioiTinh(request.getGioiTinh());

		// Mã hóa mật khẩu trước khi lưu database.
		khachHang.setMatKhau(passwordEncoder.encode(request.getMatKhau()));

		// Gán vai trò CUSTOMER cho khách hàng.
		khachHang.setVaiTro(vaiTro);

		// Tài khoản mới được kích hoạt.
		khachHang.setTrangThai(true);

		return khachHang;
	}

	/**
	 * Lưu thông tin khách hàng vào database.
	 *
	 * @param khachHang đối tượng khách hàng cần lưu
	 * @return khách hàng sau khi được lưu
	 */
	private KhachHang luuKhachHang(KhachHang khachHang) {

		// Lưu khách hàng vào bảng KhachHang.
		return khachHangRepository.save(khachHang);
	}

	/**
	 * Chuyển thông tin Entity KhachHang thành DTO phản hồi.
	 *
	 * @param khachHang khách hàng đã được lưu vào database
	 * @return DTO chứa thông tin phản hồi
	 */
	private DangKyKhachHangResponseDTO taoPhanHoiDangKy(KhachHang khachHang) {

		// Khởi tạo DTO phản hồi.
		DangKyKhachHangResponseDTO response = new DangKyKhachHangResponseDTO();

		// Sao chép thông tin từ Entity sang DTO.
		response.setMaKH(khachHang.getMaKH());
		response.setHoTen(khachHang.getHoTen());
		response.setEmail(khachHang.getEmail());
		response.setSoDienThoai(khachHang.getSoDienThoai());
		response.setNgaySinh(khachHang.getNgaySinh());
		response.setGioiTinh(khachHang.getGioiTinh());

		// Chỉ trả về tên vai trò, không trả toàn bộ Entity VaiTro.
		response.setVaiTro(khachHang.getVaiTro().getTenVaiTro());

		return response;
	}

	/**
	 * Chuyển thông tin khách hàng thành DTO phản hồi đăng nhập.
	 *
	 * @param khachHang khách hàng đã xác thực thành công
	 * @return DTO chứa thông tin tài khoản
	 */
	private DangNhapResponseDTO taoPhanHoiDangNhap(KhachHang khachHang) {

		// Khởi tạo DTO phản hồi.
		DangNhapResponseDTO response = new DangNhapResponseDTO();

		// Sao chép thông tin khách hàng.
		response.setMaTaiKhoan(khachHang.getMaKH());
		response.setHoTen(khachHang.getHoTen());
		response.setEmail(khachHang.getEmail());

		// Lấy tên vai trò từ Entity VaiTro.
		response.setVaiTro(khachHang.getVaiTro().getTenVaiTro());

		// Tạo Access Token cho khách hàng.
		String accessToken = jwtService.taoAccessToken(khachHang.getMaKH(), khachHang.getEmail(),
				khachHang.getVaiTro().getTenVaiTro());

		// Gán Access Token vào DTO phản hồi.
		response.setAccessToken(accessToken);

		// Tạo Refresh Token và lưu Hash vào Database.
		String refreshToken = refreshTokenService.taoVaLuuRefreshToken(khachHang.getMaKH());

		// Gán Refresh Token để Controller thiết lập HttpOnly Cookie.
		response.setRefreshToken(refreshToken);

		return response;
	}

	/**
	 * Chuyển thông tin nhân viên thành DTO phản hồi đăng nhập.
	 *
	 * Áp dụng cho cả tài khoản STAFF và ADMIN.
	 *
	 * @param nhanVien nhân viên đã xác thực thành công
	 * @return DTO chứa thông tin tài khoản và token
	 */
	private DangNhapResponseDTO taoPhanHoiDangNhap(NhanVien nhanVien) {

		// Khởi tạo DTO phản hồi.
		DangNhapResponseDTO response = new DangNhapResponseDTO();

		// Sao chép thông tin nhân viên.
		response.setMaTaiKhoan(nhanVien.getMaNhanVien());
		response.setHoTen(nhanVien.getHoTen());
		response.setEmail(nhanVien.getEmail());

		// Lấy tên vai trò STAFF hoặc ADMIN.
		String vaiTro = nhanVien.getVaiTro().getTenVaiTro();
		response.setVaiTro(vaiTro);

		// Tạo Access Token cho nhân viên.
		String accessToken = jwtService.taoAccessToken(nhanVien.getMaNhanVien(), nhanVien.getEmail(), vaiTro);

		response.setAccessToken(accessToken);

		// Tạo Refresh Token và lưu Hash vào Database.
		String refreshToken = refreshTokenService.taoVaLuuRefreshTokenChoNhanVien(nhanVien.getMaNhanVien());

		// Gán Refresh Token để Controller thiết lập HttpOnly Cookie.
		response.setRefreshToken(refreshToken);

		return response;
	}

	/**
	 * Tạo Access Token mới cho khách hàng.
	 *
	 * @param khachHang khách hàng đã xác thực.
	 * @return Access Token mới.
	 */
	private String taoAccessTokenMoi(KhachHang khachHang) {

		// Tạo Access Token mới dựa trên thông tin khách hàng.
		return jwtService.taoAccessToken(khachHang.getMaKH(), khachHang.getEmail(),
				khachHang.getVaiTro().getTenVaiTro());
	}

	/**
	 * Tạo Access Token mới cho nhân viên.
	 *
	 * Áp dụng cho cả STAFF và ADMIN.
	 *
	 * @param nhanVien nhân viên đã xác thực.
	 * @return Access Token mới.
	 */
	private String taoAccessTokenMoi(NhanVien nhanVien) {

		// Lấy thông tin tài khoản và vai trò của nhân viên.
		return jwtService.taoAccessToken(nhanVien.getMaNhanVien(), nhanVien.getEmail(),
				nhanVien.getVaiTro().getTenVaiTro());
	}

	/**
	 * Kiểm tra Refresh Token và xác định tài khoản sở hữu.
	 *
	 * @param refreshTokenGoc Refresh Token nhận từ Cookie.
	 * @return RefreshToken hợp lệ.
	 */
	private RefreshToken timRefreshTokenHopLe(String refreshTokenGoc) {

		// Kiểm tra Refresh Token có hợp lệ hay không.
		RefreshToken refreshToken = refreshTokenService.kiemTraRefreshToken(refreshTokenGoc);

		// Trường hợp Refresh Token thuộc khách hàng.
		if (refreshToken.getKhachHang() != null) {

			KhachHang khachHang = refreshToken.getKhachHang();

			// Từ chối nếu tài khoản khách hàng đã bị vô hiệu hóa.
			if (!Boolean.TRUE.equals(khachHang.getTrangThai())) {
				throw new InvalidCredentialsException("Tài khoản đã bị vô hiệu hóa.");
			}

			return refreshToken;
		}

		// Trường hợp Refresh Token thuộc nhân viên.
		if (refreshToken.getNhanVien() != null) {

			NhanVien nhanVien = refreshToken.getNhanVien();

			// Từ chối nếu tài khoản nhân viên đã bị vô hiệu hóa.
			if (!Boolean.TRUE.equals(nhanVien.getTrangThai())) {
				throw new InvalidCredentialsException("Tài khoản đã bị vô hiệu hóa.");
			}

			return refreshToken;
		}

		// Trường hợp Refresh Token không có tài khoản sở hữu hợp lệ.
		throw new InvalidCredentialsException("Refresh Token không hợp lệ.");
	}

	/**
	 * Làm mới Access Token và xoay vòng Refresh Token.
	 *
	 * Hỗ trợ CUSTOMER, STAFF và ADMIN.
	 *
	 * @param refreshTokenGoc Refresh Token hiện tại.
	 * @return DTO chứa Access Token mới và Refresh Token mới.
	 */
	@Transactional
	public DangNhapResponseDTO lamMoiToken(String refreshTokenGoc) {

		// Bước 1: Kiểm tra Refresh Token và trạng thái tài khoản.
		RefreshToken refreshToken = timRefreshTokenHopLe(refreshTokenGoc);

		DangNhapResponseDTO response = new DangNhapResponseDTO();

		// Bước 2: Xử lý trường hợp tài khoản CUSTOMER.
		if (refreshToken.getKhachHang() != null) {

			KhachHang khachHang = refreshToken.getKhachHang();

			response.setMaTaiKhoan(khachHang.getMaKH());
			response.setHoTen(khachHang.getHoTen());
			response.setEmail(khachHang.getEmail());
			response.setVaiTro(khachHang.getVaiTro().getTenVaiTro());

			// Tạo Access Token mới cho khách hàng.
			response.setAccessToken(taoAccessTokenMoi(khachHang));
		}

		// Bước 3: Xử lý trường hợp tài khoản STAFF hoặc ADMIN.
		else if (refreshToken.getNhanVien() != null) {

			NhanVien nhanVien = refreshToken.getNhanVien();

			response.setMaTaiKhoan(nhanVien.getMaNhanVien());
			response.setHoTen(nhanVien.getHoTen());
			response.setEmail(nhanVien.getEmail());
			response.setVaiTro(nhanVien.getVaiTro().getTenVaiTro());

			// Tạo Access Token mới cho nhân viên.
			response.setAccessToken(taoAccessTokenMoi(nhanVien));
		}

		// Bước 4: Xoay vòng Refresh Token cũ thành token mới.
		String refreshTokenMoi = refreshTokenService.xoayVongRefreshToken(refreshTokenGoc);

		// Gán Refresh Token mới để Controller thiết lập HttpOnly Cookie.
		response.setRefreshToken(refreshTokenMoi);

		return response;
	}

	/**
	 * Tìm khách hàng đang hoạt động theo email.
	 *
	 * @param email email đăng nhập
	 * @return thông tin khách hàng
	 */
	private KhachHang timKhachHangDangHoatDong(String email) {
		return khachHangRepository.findByEmailAndTrangThai(email, true)
				.orElseThrow(() -> new InvalidCredentialsException("Email hoặc mật khẩu không chính xác"));
	}

	/**
	 * Tìm nhân viên đang hoạt động theo email.
	 *
	 * Áp dụng cho cả tài khoản STAFF và ADMIN.
	 *
	 * @param email email đăng nhập
	 * @return thông tin nhân viên
	 */
	private NhanVien timNhanVienDangHoatDong(String email) {

		// Tìm nhân viên theo email.
		NhanVien nhanVien = nhanVienRepository.findByEmail(email)
				.orElseThrow(() -> new InvalidCredentialsException("Email hoặc mật khẩu không chính xác"));

		// Kiểm tra tài khoản nhân viên có đang hoạt động không.
		if (!Boolean.TRUE.equals(nhanVien.getTrangThai())) {
			throw new InvalidCredentialsException("Email hoặc mật khẩu không chính xác");
		}

		return nhanVien;
	}

	/**
	 * Kiểm tra mật khẩu người dùng nhập có khớp với mật khẩu BCrypt đã lưu trong
	 * database hay không.
	 *
	 * @param matKhauNhap    mật khẩu người dùng nhập
	 * @param matKhauDaMaHoa mật khẩu BCrypt trong database
	 * @return true nếu mật khẩu chính xác
	 */
	private boolean kiemTraMatKhau(String matKhauNhap, String matKhauDaMaHoa) {

		// So sánh mật khẩu gốc với chuỗi BCrypt đã lưu.
		return passwordEncoder.matches(matKhauNhap, matKhauDaMaHoa);
	}

	/**
	 * Xử lý đăng nhập cho CUSTOMER, STAFF và ADMIN.
	 *
	 * @param request dữ liệu đăng nhập từ Frontend
	 * @return thông tin tài khoản sau khi đăng nhập thành công
	 */
	@Transactional
	public DangNhapResponseDTO dangNhap(DangNhapRequestDTO request) {

		String email = request.getEmail();
		String matKhau = request.getMatKhau();

		// Bước 1: Ưu tiên kiểm tra tài khoản khách hàng.
		var khachHangOptional = khachHangRepository.findByEmail(email);

		if (khachHangOptional.isPresent()) {

			KhachHang khachHang = khachHangOptional.get();

			// Kiểm tra trạng thái tài khoản.
			if (!Boolean.TRUE.equals(khachHang.getTrangThai())) {
				throw new InvalidCredentialsException("Email hoặc mật khẩu không chính xác");
			}

			// Kiểm tra mật khẩu BCrypt.
			if (!kiemTraMatKhau(matKhau, khachHang.getMatKhau())) {
				throw new InvalidCredentialsException("Email hoặc mật khẩu không chính xác");
			}

			// Tạo phản hồi đăng nhập cho CUSTOMER.
			return taoPhanHoiDangNhap(khachHang);
		}

		// Bước 2: Nếu không phải khách hàng, tìm tài khoản nhân viên.
		NhanVien nhanVien = timNhanVienDangHoatDong(email);

		// Kiểm tra mật khẩu BCrypt.
		if (!kiemTraMatKhau(matKhau, nhanVien.getMatKhau())) {
			throw new InvalidCredentialsException("Email hoặc mật khẩu không chính xác");
		}

		// Tạo phản hồi đăng nhập cho STAFF hoặc ADMIN.
		return taoPhanHoiDangNhap(nhanVien);
	}

	/**
	 * Xử lý quy trình đăng ký tài khoản khách hàng.
	 *
	 * @param request dữ liệu đăng ký nhận từ Frontend
	 * @return thông tin khách hàng sau khi đăng ký thành công
	 */
	@Transactional
	public DangKyKhachHangResponseDTO dangKyKhachHang(DangKyKhachHangRequestDTO request) {

		// Bước 1: Kiểm tra email và số điện thoại.
		kiemTraThongTinDangKy(request);

		// Bước 2: Lấy vai trò CUSTOMER.
		VaiTro vaiTro = layVaiTroKhachHang();

		// Bước 3: Tạo đối tượng khách hàng và mã hóa mật khẩu.
		KhachHang khachHang = taoKhachHang(request, vaiTro);

		// Bước 4: Lưu khách hàng vào database.
		KhachHang khachHangDaLuu = luuKhachHang(khachHang);

		// Bước 5: Chuyển Entity thành DTO phản hồi.
		return taoPhanHoiDangKy(khachHangDaLuu);
	}

	/**
	 * Xử lý đăng xuất tài khoản CUSTOMER, STAFF hoặc ADMIN.
	 *
	 * @param refreshTokenGoc Refresh Token nhận từ Cookie.
	 */
	@Transactional
	public void dangXuat(String refreshTokenGoc) {

		// Kiểm tra Refresh Token có tồn tại hay không.
		if (refreshTokenGoc == null || refreshTokenGoc.isBlank()) {
			throw new InvalidCredentialsException("Không tìm thấy Refresh Token.");
		}

		// Thu hồi Refresh Token trong Database.
		refreshTokenService.thuHoiRefreshToken(refreshTokenGoc);
	}
}

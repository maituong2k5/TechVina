import "./StaffDashboard.scss";

/**
 * Dashboard tổng quan dành cho nhân viên TechVina.
 */
const StaffDashboard = () => {
  // Dữ liệu thống kê tạm thời.
  // Sau này sẽ thay bằng dữ liệu lấy từ Spring Boot API.
  const statistics = [
    {
      id: 1,
      title: "Tổng đơn hàng",
      value: "128",
      description: "Tổng số đơn hàng trong hệ thống",
    },
    {
      id: 2,
      title: "Chờ xử lý",
      value: "15",
      description: "Đơn hàng cần được xử lý",
    },
    {
      id: 3,
      title: "Sản phẩm",
      value: "86",
      description: "Sản phẩm đang kinh doanh",
    },
    {
      id: 4,
      title: "Sắp hết hàng",
      value: "08",
      description: "Sản phẩm cần nhập thêm",
    },
  ];

  return (
    <div className="staff-dashboard">
      {/* Tiêu đề Dashboard */}
      <div className="staff-dashboard__heading">
        <div>
          <h2>Tổng quan</h2>
          <p>Chào mừng bạn quay trở lại với TechVina.</p>
        </div>
      </div>

      {/* Các thẻ thống kê */}
      <div className="staff-dashboard__statistics">
        {statistics.map((item) => (
          <div className="staff-stat-card" key={item.id}>
            <h3>{item.title}</h3>
            <p className="staff-stat-card__value">{item.value}</p>
            <span>{item.description}</span>
          </div>
        ))}
      </div>

      {/* Khu vực đơn hàng gần đây */}
      <section className="staff-dashboard__recent">
        <div className="staff-dashboard__section-heading">
          <h3>Đơn hàng gần đây</h3>
          <span>Danh sách đơn hàng mới nhất</span>
        </div>

        <div className="staff-dashboard__empty">Chưa có dữ liệu đơn hàng.</div>
      </section>
    </div>
  );
};

export default StaffDashboard;

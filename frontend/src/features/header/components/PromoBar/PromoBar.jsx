import "./PromoBar.scss";

/*Thanh thông báo phía trên Header*/

const PromoBar = () => {
  return (
    <div className="promo-bar">
      <div className="container">
        <p className="promo-bar__text">
          Ưu đãi hấp dẫn
          <span className="promo-bar__separator">•</span>
          Giao hàng toàn quốc
          <span className="promo-bar__separator">•</span>
          Hỗ trợ khách hàng 24/7
          <span className="promo-bar__separator">•</span>
          Liên hệ: 0337268951
        </p>
      </div>
    </div>
  );
};

export default PromoBar;

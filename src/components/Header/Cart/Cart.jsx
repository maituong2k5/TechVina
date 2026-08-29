import { IoCartOutline } from "react-icons/io5";
import { Link } from "react-router-dom";
import "./Cart.scss";

const Cart = () => {
  return (
    <div className="header__cart">
      <Link to="/cart" className="header__cart-link">
        <IoCartOutline className="header__cart-icon" />
        <span className="header__cart-text">Giỏ hàng</span>
      </Link>
    </div>
  );
};

export default Cart;

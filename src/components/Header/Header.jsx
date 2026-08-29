import { Container } from "react-bootstrap";
import { Link } from "react-router-dom";

import logo from "../../assets/TechVina-logo.png";
import SearchBar from "./SearchBar/SearchBar";
import Login from "./Login/Login";
import Cart from "./Cart/Cart";
import Location from "./Location/Location";

import "./Header.scss";

const Header = () => {
  return (
    <header className="header">
      <Container>
        <div className="header__top">
          <Link to="/" className="header__logo">
            <img className="header__img" src={logo} alt="TechVina" />
          </Link>
          <SearchBar />
          <Login />
          <Cart />
          <Location />
        </div>
      </Container>
    </header>
  );
};

export default Header;

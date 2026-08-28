import { Link } from "react-router-dom";
import "./Header.scss";
import { Container } from "react-bootstrap";
import logo from "../../assets/TechVina-logo.png";
import SearchBar from "./SearchBar/SearchBar";
import Login from "./Login/Login";

const Header = () => {
  return (
    <header className="header">
      <Container>
        <div className="header-top">
          <div className="header-logo ">
            <img src={logo} alt="TechVina" />
          </div>
          <SearchBar />
          <Login />
        </div>
      </Container>
    </header>
  );
};

export default Header;

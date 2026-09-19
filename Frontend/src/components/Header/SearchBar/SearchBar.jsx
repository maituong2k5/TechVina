import { Form } from "react-bootstrap";
import { FaSearch } from "react-icons/fa";
import "./SearchBar.scss";

const SearchBar = () => {
  return (
    <form className="search">
      <button className="search__button" type="submit">
        <FaSearch className="search__icon" />
      </button>

      <Form.Control
        className="search__input"
        type="search"
        placeholder="Tìm kiếm sản phẩm, thương hiệu..."
      />
    </form>
  );
};

export default SearchBar;

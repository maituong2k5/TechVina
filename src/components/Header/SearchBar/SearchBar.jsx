import { Form } from "react-bootstrap";
import { FaSearch } from "react-icons/fa";
import "./SearchBar.scss";

const SearchBar = () => {
  return (
    <div className="search-bar">
      <FaSearch className="search-icon" />

      <Form.Control
        type="search"
        placeholder="Tìm kiếm sản phẩm, thương hiệu..."
      />
    </div>
  );
};

export default SearchBar;

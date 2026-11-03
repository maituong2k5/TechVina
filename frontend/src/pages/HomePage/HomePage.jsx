import { Container, Carousel } from "react-bootstrap";
import "./HomePage.scss";
import HeroBanner from "./HeroBanner/HeroBanner";
import CategorySection from "./CategorySection/CategorySection";
import ProductSection from "./ProductSection/ProductSection";

const HomePage = () => {
  return (
    <Container>
      <div className="home-page">
        <HeroBanner />
        <CategorySection />
        <ProductSection />
      </div>
    </Container>
  );
};

export default HomePage;

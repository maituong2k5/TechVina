import { Container, Carousel } from "react-bootstrap";
import "./HomePage.scss";
import HeroBanner from "./HeroBanner/HeroBanner";
import CategorySection from "./CategorySection/CategorySection";

const HomePage = () => {
  return (
    <Container>
      <div className="home-page">
        <HeroBanner />
        <CategorySection />
      </div>
    </Container>
  );
};

export default HomePage;

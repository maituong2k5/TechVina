import { Container, Carousel } from "react-bootstrap";
import "./HomePage.scss";
import HeroBanner from "./HeroBanner/HeroBanner";

const HomePage = () => {
  return (
    <Container>
      <div className="home-page">
        <HeroBanner />
      </div>
    </Container>
  );
};

export default HomePage;

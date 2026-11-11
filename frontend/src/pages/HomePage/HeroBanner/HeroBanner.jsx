import { Carousel } from "react-bootstrap";
import "./HeroBanner.scss";

const HeroBanner = () => {
  return (
    <section className="hero-banner">
      <Carousel
        fade
        interval={4000}
        controls={true}
        indicators={true}
        pause="hover"
      >
        <Carousel.Item>
          <img
            className="hero-banner__image"
            src="/images/banners/471331_S26-Series-1200x375.webp"
            alt="Samsung S26 Series"
          />
        </Carousel.Item>

        <Carousel.Item>
          <img
            className="hero-banner__image"
            src="/images/banners/702904_KV-SS-A18-_-A08-1200x375-25w.webp"
            alt="Samsung A18"
          />
        </Carousel.Item>

        <Carousel.Item>
          <img
            className="hero-banner__image"
            src="/images/banners/804046_Banner-web_Q8H8B8_Preorder-t10-1200x375.webp"
            alt="Banner quảng cáo 3"
          />
        </Carousel.Item>

        <Carousel.Item>
          <img
            className="hero-banner_image"
            src="/images/banners/860851_OPPO-Find-X10-Series-web.webp"
            alt="Oppo find x10"
          />
        </Carousel.Item>

        <Carousel.Item>
          <img
            className="hero-banner_image"
            src="/images/banners/astra-2-web-083724546.webp"
            alt="Redmagic Astra 2"
          />
        </Carousel.Item>

        <Carousel.Item>
          <img
            className="hero-banner_image"
            src="/images/banners/iphone-18-web-082925157.webp"
            alt="Iphone 18"
          />
        </Carousel.Item>

        <Carousel.Item>
          <img
            className="hero-banner_image"
            src="/images/banners/iphone-duo-web-084156526.webp"
            alt="Iphone duo"
          />
        </Carousel.Item>
      </Carousel>
    </section>
  );
};

export default HeroBanner;

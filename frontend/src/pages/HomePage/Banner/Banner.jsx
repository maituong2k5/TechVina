import { Swiper, SwiperSlide } from "swiper/react";
import { Autoplay, Navigation, Pagination } from "swiper/modules";

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";

import "./Banner.scss";

import banner1 from "../../../assets/kv-quoc-khanh-web-175459101.webp";
import banner2 from "../../../assets/resize-banner-web-s26-fe-kmhh-1200x375-190744131.webp";
import banner3 from "../../../assets/xiaomi-note-17-web-172824319.webp";

const banners = [banner1, banner2, banner3];
const Banner = () => {
  return (
    <section className="banner">
      <Swiper
        className="banner__slider"
        modules={[Autoplay, Navigation, Pagination]}
        navigation
        pagination={{ clickable: true }}
        autoplay={{
          delay: 3000,
          disableOnInteraction: false,
        }}
        loop
      >
        {banners.map((banner, index) => (
          <SwiperSlide className="banner__slide" key={index}>
            <img
              className="banner__image"
              src={banner}
              alt={`Banner ${index + 1}`}
            />
          </SwiperSlide>
        ))}
      </Swiper>
    </section>
  );
};

export default Banner;

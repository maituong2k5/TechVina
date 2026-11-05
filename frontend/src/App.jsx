import "./App.css";
import { Outlet } from "react-router-dom";
import Header from "./features/header/Header";
import { CartProvider } from "./context/CartContext";

const App = () => {
  return (
    <>
      <CartProvider>
        <Header />
        <main>
          <Outlet />
        </main>
      </CartProvider>
    </>
  );
};

export default App;

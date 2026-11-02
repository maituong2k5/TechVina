import "./App.css";
import { Outlet } from "react-router-dom";
import Header from "./features/header/Header";

const App = () => {
  return (
    <>
      <Header />
      <main>
        <Outlet />
      </main>
    </>
  );
};

export default App;

import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";

//import thư viện bootstrap
import "bootstrap/dist/css/bootstrap.min.css";

// Import Design System của TechVina
import "./styles/variables.css";
import "./index.css";

import AppRoutes from "./routes/AppRoutes.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <BrowserRouter>
      <AppRoutes />
    </BrowserRouter>
  </StrictMode>,
);

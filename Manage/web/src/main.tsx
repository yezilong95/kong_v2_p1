import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { App } from "./App";
import { SITE_NAME } from "./config";

document.title = SITE_NAME;

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <App />
  </StrictMode>,
);

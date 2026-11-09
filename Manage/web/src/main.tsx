import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { App } from "./App";
import { getDisplaySiteName } from "./config";

document.title = getDisplaySiteName();

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <App />
  </StrictMode>,
);

import { config } from "../config.js";

export function getIceServers() {
  return config.webrtc.iceServers;
}

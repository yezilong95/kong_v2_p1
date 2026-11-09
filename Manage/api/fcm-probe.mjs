import { readFileSync } from "node:fs";
import { initializeApp, cert } from "firebase-admin/app";
import { getMessaging } from "firebase-admin/messaging";

const sa = JSON.parse(readFileSync("/Volumes/data/下载/firebase-adminsdk.json", "utf8"));
console.log("project_id =", sa.project_id);
console.log("client_email =", sa.client_email);

const app = initializeApp({ credential: cert(sa), projectId: sa.project_id });
const messaging = getMessaging(app);

const wakeUntil = Date.now() + 600_000;
console.log("wakeUntil =", wakeUntil);

const t0 = Date.now();
try {
  const id = await messaging.send({
    topic: "wake_all",
    data: { wakeUntil: String(wakeUntil), keepAwakeMs: "300000" },
    android: { priority: "high", ttl: 5 * 60 * 1000 },
  });
  console.log("SEND OK", id, "costMs =", Date.now() - t0);
} catch (e) {
  console.log("SEND FAILED costMs =", Date.now() - t0);
  console.log("code =", e?.code);
  console.log("message =", e?.message);
  console.log("errorInfo =", JSON.stringify(e?.errorInfo ?? null));
}

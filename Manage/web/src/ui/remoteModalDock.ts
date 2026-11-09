import type { ModalProps } from "antd";

/** 远程控制页右侧工具栏宽度，弹窗右缘贴其左边框 */
export const REMOTE_OPS_RAIL_WIDTH_PX = 84;

/** 远程页所有 Modal 共用：不居中，贴右轨 */
export const remoteDockModalProps: Pick<ModalProps, "centered" | "style" | "styles"> = {
  centered: false,
  style: {
    top: 56,
    margin: 0,
    paddingBottom: 24,
    position: "absolute",
    right: REMOTE_OPS_RAIL_WIDTH_PX,
    left: "auto",
  },
  styles: {
    wrapper: {
      overflow: "auto",
    },
  },
};

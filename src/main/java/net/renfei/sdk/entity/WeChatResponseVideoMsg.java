package net.renfei.sdk.entity;

import lombok.Data;

/**
 * <p>Title: WeChatResponseVideoMsg</p>
 * <p>Description: 微信消息回复视频类</p>
 *
 * @author RenFei
 * @date : 2020-07-19 12:58
 */
@Data
public class WeChatResponseVideoMsg extends WeChatResponseBaseMsg {
    private Video Video;

    public WeChatResponseVideoMsg(WeChatMsg weChatMessage) {
        super(weChatMessage, WeChatMsgType.VIDEO);
    }

    @Data
    public static class Video {
        private String MediaId;
        private String Title;
        private String Description;
    }
}

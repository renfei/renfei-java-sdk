package net.renfei.sdk.entity;

import lombok.Data;

/**
 * <p>Title: WeChatResponseImageMsg</p>
 * <p>Description: 微信消息回复图片类</p>
 *
 * @author RenFei
 * @date : 2020-07-19 12:58
 */
@Data
public class WeChatResponseImageMsg extends WeChatResponseBaseMsg {
    private Image Image;

    public WeChatResponseImageMsg(WeChatMsg weChatMessage) {
        super(weChatMessage, WeChatMsgType.IMAGE);
    }

    @Data
    public static class Image {
        private String MediaId;
    }
}

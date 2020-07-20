package net.renfei.sdk.entity;

import lombok.Data;

/**
 * <p>Title: WeChatResponseTextMsg</p>
 * <p>Description: 微信消息回复文本类</p>
 *
 * @author RenFei
 * @date : 2020-07-19 12:52
 */
@Data
public class WeChatResponseTextMsg extends WeChatResponseBaseMsg {
    private String Content;

    public WeChatResponseTextMsg(WeChatMsg weChatMessage) {
        super(weChatMessage, WeChatMsgType.TEXT);
    }
}

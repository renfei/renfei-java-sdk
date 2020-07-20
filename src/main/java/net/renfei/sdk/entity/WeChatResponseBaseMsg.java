package net.renfei.sdk.entity;

import lombok.Data;

/**
 * <p>Title: WeChatBaseMsg</p>
 * <p>Description: 微信消息回复基础类</p>
 *
 * @author RenFei
 * @date : 2020-07-19 12:49
 */
@Data
public abstract class WeChatResponseBaseMsg {
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String MsgId;

    private WeChatResponseBaseMsg(){}

    public WeChatResponseBaseMsg(WeChatMsg weChatMessage,WeChatMsgType weChatMessageType) {
        this.ToUserName = weChatMessage.getFromUserName();
        this.FromUserName = weChatMessage.getToUserName();
        this.CreateTime = System.currentTimeMillis();
        this.MsgType = weChatMessageType.value();
    }
}

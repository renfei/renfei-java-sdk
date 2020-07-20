package net.renfei.sdk.entity;

import lombok.Data;

/**
 * <p>Title: WeChatResponseVoiceMsg</p>
 * <p>Description: 微信消息回复语音类</p>
 *
 * @author RenFei
 * @date : 2020-07-19 12:59
 */
@Data
public class WeChatResponseVoiceMsg extends WeChatResponseBaseMsg {
    private Voice Voice;

    public WeChatResponseVoiceMsg(WeChatMsg weChatMessage) {
        super(weChatMessage, WeChatMsgType.VOICE);
    }

    @Data
    public static class Voice {
        private String MediaId;
    }
}

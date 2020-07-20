package net.renfei.sdk.entity;

import lombok.Data;

/**
 * <p>Title: WeChatResponseMusicMsg</p>
 * <p>Description: 微信消息回复音乐类</p>
 *
 * @author RenFei
 * @date : 2020-07-19 12:56
 */
@Data
public class WeChatResponseMusicMsg extends WeChatResponseBaseMsg {
    private Music Music;

    public WeChatResponseMusicMsg(WeChatMsg weChatMessage) {
        super(weChatMessage, WeChatMsgType.MUSIC);
    }

    @Data
    public static class Music {
        private String Title;
        private String Description;
        private String MusicUrl;
        private String HQMusicUrl;
        private String ThumbMediaId;
    }
}

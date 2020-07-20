package net.renfei.sdk.entity;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: WeChatResponseNewsMsg</p>
 * <p>Description: 微信消息回复图文类</p>
 *
 * @author RenFei
 * @date : 2020-07-19 12:54
 */
@Data
public class WeChatResponseNewsMsg extends WeChatResponseBaseMsg {
    private String ArticleCount;
    List<ArticlesItem> Articles;

    public WeChatResponseNewsMsg(WeChatMsg weChatMessage) {
        super(weChatMessage, WeChatMsgType.NEWS);
    }

    @Data
    public static class ArticlesItem {
        private String Title;
        private String Description;
        private String PicUrl;
        private String Url;
    }
}

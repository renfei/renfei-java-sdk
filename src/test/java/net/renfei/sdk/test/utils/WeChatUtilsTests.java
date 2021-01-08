package net.renfei.sdk.test.utils;

import lombok.extern.slf4j.Slf4j;
import net.renfei.sdk.entity.*;
import net.renfei.sdk.utils.WeChatUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: WeChatUtilsTests</p>
 * <p>Description: </p>
 *
 * @author RenFei
 * @date : 2020-07-19 13:46
 */
public class WeChatUtilsTests {
    private final WeChatUtils weChatUtils = new WeChatUtils();

    @Test
    public void test() {
        WeChatMsg weChatMsg = new WeChatMsg();
        weChatMsg.setToUserName("ToUserName");
        weChatMsg.setFromUserName("FromUserName");
        WeChatResponseTextMsg weChatResponseTextMsg = new WeChatResponseTextMsg(weChatMsg);
        weChatResponseTextMsg.setContent("TestContent");
        System.out.println(weChatUtils.textMessageToXml(weChatResponseTextMsg));
        WeChatResponseNewsMsg weChatResponseNewsMsg = new WeChatResponseNewsMsg(weChatMsg);
        List<WeChatResponseNewsMsg.ArticlesItem> articlesItemList = new ArrayList<>();
        WeChatResponseNewsMsg.ArticlesItem articlesItem = new WeChatResponseNewsMsg.ArticlesItem();
        articlesItem.setTitle("TITLE");
        articlesItemList.add(articlesItem);
        weChatResponseNewsMsg.setArticleCount("1");
        weChatResponseNewsMsg.setArticles(articlesItemList);
        System.out.println(weChatUtils.newsMessageToXml(weChatResponseNewsMsg));
        WeChatResponseImageMsg weChatResponseImageMsg = new WeChatResponseImageMsg(weChatMsg);
        WeChatResponseImageMsg.Image image = new WeChatResponseImageMsg.Image();
        image.setMediaId("Test");
        weChatResponseImageMsg.setImage(image);
        System.out.println(weChatUtils.imageMessageToXml(weChatResponseImageMsg));
        WeChatResponseMusicMsg weChatResponseMusicMsg = new WeChatResponseMusicMsg(weChatMsg);
        WeChatResponseMusicMsg.Music music = new WeChatResponseMusicMsg.Music();
        music.setTitle("Title");
        weChatResponseMusicMsg.setMusic(music);
        System.out.println(weChatUtils.musicMessageToXml(weChatResponseMusicMsg));
        WeChatResponseVideoMsg weChatResponseVideoMsg = new WeChatResponseVideoMsg(weChatMsg);
        WeChatResponseVideoMsg.Video video = new WeChatResponseVideoMsg.Video();
        video.setTitle("Title");
        weChatResponseVideoMsg.setVideo(video);
        System.out.println(weChatUtils.videoMessageToXml(weChatResponseVideoMsg));
        WeChatResponseVoiceMsg weChatResponseVoiceMsg = new WeChatResponseVoiceMsg(weChatMsg);
        WeChatResponseVoiceMsg.Voice voice = new WeChatResponseVoiceMsg.Voice();
        voice.setMediaId("Test");
        weChatResponseVoiceMsg.setVoice(voice);
        System.out.println(weChatUtils.voiceMessageToXml(weChatResponseVoiceMsg));
    }
}

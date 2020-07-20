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
@Slf4j
public class WeChatUtilsTests {
    private final WeChatUtils weChatUtils = new WeChatUtils();

    @Test
    public void test() {
        WeChatMsg weChatMsg = new WeChatMsg();
        weChatMsg.setToUserName("ToUserName");
        weChatMsg.setFromUserName("FromUserName");
        WeChatResponseTextMsg weChatResponseTextMsg = new WeChatResponseTextMsg(weChatMsg);
        weChatResponseTextMsg.setContent("TestContent");
        log.info(weChatUtils.textMessageToXml(weChatResponseTextMsg));
        WeChatResponseNewsMsg weChatResponseNewsMsg = new WeChatResponseNewsMsg(weChatMsg);
        List<WeChatResponseNewsMsg.ArticlesItem> articlesItemList = new ArrayList<>();
        WeChatResponseNewsMsg.ArticlesItem articlesItem = new WeChatResponseNewsMsg.ArticlesItem();
        articlesItem.setTitle("TITLE");
        articlesItemList.add(articlesItem);
        weChatResponseNewsMsg.setArticleCount("1");
        weChatResponseNewsMsg.setArticles(articlesItemList);
        log.info(weChatUtils.newsMessageToXml(weChatResponseNewsMsg));
        WeChatResponseImageMsg weChatResponseImageMsg = new WeChatResponseImageMsg(weChatMsg);
        WeChatResponseImageMsg.Image image = new WeChatResponseImageMsg.Image();
        image.setMediaId("Test");
        weChatResponseImageMsg.setImage(image);
        log.info(weChatUtils.imageMessageToXml(weChatResponseImageMsg));
        WeChatResponseMusicMsg weChatResponseMusicMsg = new WeChatResponseMusicMsg(weChatMsg);
        WeChatResponseMusicMsg.Music music = new WeChatResponseMusicMsg.Music();
        music.setTitle("Title");
        weChatResponseMusicMsg.setMusic(music);
        log.info(weChatUtils.musicMessageToXml(weChatResponseMusicMsg));
        WeChatResponseVideoMsg weChatResponseVideoMsg = new WeChatResponseVideoMsg(weChatMsg);
        WeChatResponseVideoMsg.Video video = new WeChatResponseVideoMsg.Video();
        video.setTitle("Title");
        weChatResponseVideoMsg.setVideo(video);
        log.info(weChatUtils.videoMessageToXml(weChatResponseVideoMsg));
        WeChatResponseVoiceMsg weChatResponseVoiceMsg = new WeChatResponseVoiceMsg(weChatMsg);
        WeChatResponseVoiceMsg.Voice voice = new WeChatResponseVoiceMsg.Voice();
        voice.setMediaId("Test");
        weChatResponseVoiceMsg.setVoice(voice);
        log.info(weChatUtils.voiceMessageToXml(weChatResponseVoiceMsg));
    }
}

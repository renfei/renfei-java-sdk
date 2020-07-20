package net.renfei.sdk.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import net.renfei.sdk.entity.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: WeChatUtils</p>
 * <p>Description: 微信公众号消息工具</p>
 *
 * @author RenFei
 * @date : 2020-07-20 11:54
 */
public class WeChatUtils {
    /**
     * 验证消息是否来自微信官方服务器
     *
     * @param request     请求对象
     * @param weChatToken 微信后台约定的Token
     * @return
     */
    public boolean checkWeChat(HttpServletRequest request, String weChatToken) {
        /**
         * 微信加密签名
         */
        String signature = request.getParameter("signature");
        /**
         * 随机字符串
         */
        String echostr = request.getParameter("echostr");
        /**
         * 时间戳
         */
        String timestamp = request.getParameter("timestamp");
        /**
         * 随机数
         */
        String nonce = request.getParameter("nonce");
        return checkWeChat(signature, echostr, timestamp, nonce, weChatToken);
    }

    /**
     * 验证消息是否来自微信官方服务器
     *
     * @param signature   微信加密签名
     * @param echostr     随机字符串
     * @param timestamp   时间戳
     * @param nonce       随机数
     * @param weChatToken 微信后台约定的Token
     * @return
     */
    public boolean checkWeChat(String signature, String echostr, String timestamp, String nonce, String weChatToken) {
        String[] str = {timestamp, nonce, weChatToken};
        //将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(str);
        StringBuilder sb = new StringBuilder();
        //将三个参数字符串拼接成一个字符串进行sha1加密
        for (String param : str) {
            sb.append(param);
        }
        //获取到加密过后的字符串
        String encryptStr = EncryptionUtils.encrypt("SHA1", sb.toString());
        //判断加密后的字符串是否与微信的签名一致
        return signature.equalsIgnoreCase(encryptStr);
    }

    /**
     * 解析微信服务器发过来的xml格式的消息将其转换为map
     *
     * @param request
     * @return Map<String, String>
     * @throws Exception
     */
    public Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中得到输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到XML的根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        @SuppressWarnings("unchecked")
        List<Element> elementList = root.elements();
        // 判断又没有子元素列表
        if (elementList.size() == 0) {
            map.put(root.getName(), root.getText());
        } else {
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
        }
        // 释放资源
        inputStream.close();
        return map;
    }

    /**
     * 文本信息转XML
     *
     * @param textMessage
     * @return
     */
    public String textMessageToXml(WeChatResponseTextMsg textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 图文类信息转XML
     *
     * @param newsMessage
     * @return
     */
    public String newsMessageToXml(WeChatResponseNewsMsg newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", WeChatResponseNewsMsg.ArticlesItem.class);
        return xstream.toXML(newsMessage);
    }

    /**
     * 图片类信息转XML
     *
     * @param imageMessage
     * @return
     */
    public String imageMessageToXml(WeChatResponseImageMsg imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * 语音类信息转XML
     *
     * @param voiceMessage
     * @return
     */
    public String voiceMessageToXml(WeChatResponseVoiceMsg voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }

    /**
     * 视频类信息转XML
     *
     * @param videoMessage
     * @return
     */
    public String videoMessageToXml(WeChatResponseVideoMsg videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }

    /**
     * 音乐类信息转XML
     *
     * @param musicMessage
     * @return
     */
    public String musicMessageToXml(WeChatResponseMusicMsg musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 对象到xml的处理，扩展xstream，使其支持CDATA块
     */
    private final XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                final boolean cdata = true;

                @Override
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}

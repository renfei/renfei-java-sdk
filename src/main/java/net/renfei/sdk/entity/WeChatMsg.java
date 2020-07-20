package net.renfei.sdk.entity;

import lombok.Data;
import net.renfei.sdk.utils.WeChatUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * <p>Title: WeChatMsg</p>
 * <p>Description: 微信公众号消息</p>
 *
 * @author RenFei
 * @date : 2020-07-20 12:08
 */
@Data
public class WeChatMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 发送发微信账号
     */
    private String fromUserName;
    /**
     * 接收方微信账号
     */
    private String toUserName;
    /**
     * 微信用户名
     */
    private String weixinUserName;
    /**
     * 消息类型
     */
    private WeChatMsgType msgType;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 图片链接（由系统生成）
     */
    private String picUrl;
    /**
     * 图片消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;
    /**
     * 语音格式，如amr，speex等
     */
    private String format;
    /**
     * 语音识别结果，UTF8编码
     */
    private String recognition;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String thumbMediaId;
    /**
     * 地理位置维度
     */
    private String location_X;
    /**
     * 地理位置经度
     */
    private String location_Y;
    /**
     * 地图缩放大小
     */
    private String scale;
    /**
     * 地理位置信息
     */
    private String label;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息描述
     */
    private String description;
    /**
     * 消息链接
     */
    private String url;
    /**
     * 消息ID
     */
    private String msgId;
    /**
     * 事件类型
     */
    private WeChatMsgType.EventEnum eventEnum;
    /**
     * 事件KEY值
     */
    private String eventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String ticket;
    /**
     * 地理位置纬度
     */
    private String latitude;
    /**
     * 地理位置经度
     */
    private String longitude;
    /**
     * 地理位置精度
     */
    private String precision;

    public WeChatMsg() {
    }

    public WeChatMsg(HttpServletRequest request) throws Exception {
        WeChatUtils weChatUtils = new WeChatUtils();
        Map<String, String> map = weChatUtils.parseXml(request);
        this.fromUserName = map.get("FromUserName");
        this.toUserName = map.get("ToUserName");
        this.msgType = WeChatMsgType.valueOf(map.get("MsgType").toUpperCase());
        this.msgId = map.get("MsgId");
        this.eventEnum = map.get("Event") == null ? null : WeChatMsgType.EventEnum.valueOf(map.get("Event").toUpperCase());
        this.content = map.get("Content");
        this.picUrl = map.get("PicUrl");
        this.mediaId = map.get("MediaId");
        this.format = map.get("Format");
        this.recognition = map.get("Recognition");
        this.thumbMediaId = map.get("ThumbMediaId");
        this.location_X = map.get("Location_X");
        this.location_X = map.get("Location_Y");
        this.scale = map.get("Scale");
        this.label = map.get("Label");
        this.title = map.get("Title");
        this.description = map.get("Description");
        this.url = map.get("Url");
        this.eventKey = map.get("EventKey");
        this.ticket = map.get("Ticket");
        this.latitude = map.get("Latitude");
        this.longitude = map.get("Longitude");
        this.precision = map.get("Precision");
    }
}

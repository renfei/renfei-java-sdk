package net.renfei.sdk.entity;

import com.sun.istack.internal.NotNull;

/**
 * <p>Title: WeChatMsgType</p>
 * <p>Description: 微信消息类型</p>
 *
 * @author RenFei
 * @date : 2020-07-19 11:21
 */
public enum WeChatMsgType {
    /**
     * 文本类型
     */
    TEXT("text"),
    /**
     * 图片类型
     */
    IMAGE("image"),
    /**
     * 语音类型
     */
    VOICE("voice"),
    /**
     * 视频类型
     */
    VIDEO("video"),
    /**
     * 小视频类型
     */
    SHORTVIDEO("shortvideo"),
    /**
     * 地理位置类型
     */
    LOCATION("location"),
    /**
     * 链接类型
     */
    LINK("link"),
    /**
     * 事件类型
     */
    EVENT("event"),
    /**
     * 音乐类型（用于回复消息）
     */
    MUSIC("music"),
    /**
     * 图文类型（用于回复消息）
     */
    NEWS("news");
    private final String type;

    WeChatMsgType(String type) {
        this.type = type;
    }

    public String value() {
        return this.type;
    }

    /**
     * 事件类型
     */
    public enum EventEnum {
        /**
         * 订阅事件
         */
        SUBSCRIBE("subscribe"),
        /**
         * 取消订阅事件
         */
        UNSUBSCRIBE("unsubscribe"),
        /**
         * 用户已关注时的事件推送
         */
        SCAN("SCAN"),
        /**
         * 扫扫码推事件
         */
        SCANCODE_PUSH("scancode_push"),
        /**
         * 扫描显示消息接受中
         */
        SCANCODE_WAITMSG("scancode_waitmsg"),
        /**
         * 弹出系统拍照发图
         */
        PIC_SYSPHOTO("pic_sysphoto"),
        /**
         * 弹出拍照或者相册发图
         */
        PIC_PHOTO_OR_ALBUM("pic_photo_or_album"),
        /**
         * 弹出微信相册发图器
         */
        PIC_WEIXIN("pic_weixin"),
        /**
         * 弹出地理位置选择器
         */
        LOCATION_SELECT("location_select"),
        /**
         * 上报地理位置事件
         */
        LOCATION("LOCATION"),
        /**
         * 点击菜单拉取消息时的事件推送
         */
        CLICK("CLICK"),
        /**
         * 点击菜单跳转链接时的事件推送
         */
        VIEW("VIEW"),
        /**
         * 模板消息发送结束
         */
        TEMPLATESENDJOBFINISH("TEMPLATESENDJOBFINISH"),
        /**
         * 群发消息后的通知
         */
        MASSSENDJOBFINISH("MASSSENDJOBFINISH"),
        /**
         * 微信小店订单支付后的通知
         */
        MERCHANT_ORDER("merchant_order"),
        /**
         * 资质认证成功
         */
        QUALIFICATION_VERIFY_SUCCESS("qualification_verify_success"),
        /**
         * 资质认证失败
         */
        QUALIFICATION_VERIFY_FAIL("qualification_verify_fail"),
        /**
         * 名称认证成功
         */
        NAMING_VERIFY_SUCCESS("naming_verify_success"),
        /**
         * 名称认证失败
         */
        NAMING_VERIFY_FAIL("naming_verify_fail"),
        /**
         * 年审通知
         */
        ANNUAL_RENEW("annual_renew"),
        /**
         * 认证过期失效通知
         */
        VERIFY_EXPIRED("verify_expired");

        private String eventType;

        EventEnum(String eventType) {
            this.eventType = eventType;
        }
    }
}

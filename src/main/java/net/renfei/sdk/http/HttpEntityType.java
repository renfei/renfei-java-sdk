package net.renfei.sdk.http;

/**
 * Http实体类型
 *
 * @author RenFei
 */
public enum HttpEntityType {
    /**
     * 字符串类型
     */
    ENTITY_STRING(0, "StringEntity"),
    /**
     * 文件类型
     */
    ENTITY_FILE(1, "FileEntity"),
    /**
     * 字节数组类型
     */
    ENTITY_BYTES(2, "ByteArrayEntity"),
    /**
     * 字节流类型
     */
    ENTITY_INPUT_STREAM(3, "ENTITY_INPUT_STREAM"),
    /**
     * 序列化对象
     */
    ENTITY_SERIALIZABLE(4, "SerializableEntity"),
    /**
     * MultipartEntity类型
     */
    ENTITY_MULTIPART(5, "MultipartEntity"),
    /**
     * Url编码类型
     */
    ENTITY_FORM(6, "UrlEncodedFormEntity");

    private int code;
    private String name;

    private HttpEntityType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}

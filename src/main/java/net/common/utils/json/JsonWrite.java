package net.common.utils.json;

/**
 * JSON响应数据输出
 * User: shijingui
 * Date: 2015/12/18
 */
public class JsonWrite {

    /**
     * 提示信息
     */
    private String msg;
    /**
     * 是否成功
     */
    private boolean success = Boolean.TRUE;
    /**
     * 响应数据
     */
    private Object data;

    public JsonWrite() {

    }

    public JsonWrite(Object data) {
        this.data = data;
    }

    public JsonWrite(Object data, String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}

package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zhangyu
 * @date: 2019-12-16
 * @param:
 * @return:
 * 功能描述: 根据开发文档得到的结果类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private boolean flag;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private Object data;// 返回数据

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}

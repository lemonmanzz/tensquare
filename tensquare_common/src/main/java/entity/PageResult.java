package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * @author: zhangyu
 * @date: 2019-12-16
 * @param:
 * @return:
 * 功能描述: 分页查询类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private long total; //总记录数
    private List<T> rows; //每页的结果集
}

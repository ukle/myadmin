package me.star.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表的数据信息
 * @author Zheng Jie
 * @date 2019-01-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableInfo {

    @ApiModelProperty(value = "表名称")
    private Object tableName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期：yyyy-MM-dd HH:mm:ss")
    private Object createTime;

    @ApiModelProperty(value = "数据库引擎")
    private Object engine;

    @ApiModelProperty(value = "编码集")
    private Object coding;

    @ApiModelProperty(value = "备注")
    private Object remark;
}

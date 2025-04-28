package me.star.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 交易详情，按需应该存入数据库，这里存入数据库，仅供临时测试
 * @author Zheng Jie
 * @date 2018-12-31
 */
@Data
public class TradeVo {

    @NotBlank
    @ApiModelProperty(value = "商品描述")
    private String body;

    @NotBlank
    @ApiModelProperty(value = "商品名称")
    private String subject;

    @ApiModelProperty(value = "商户订单号", hidden = true)
    private String outTradeNo;

    @ApiModelProperty(value = "第三方订单号", hidden = true)
    private String tradeNo;

    @NotBlank
    @ApiModelProperty(value = "价格")
    private String totalAmount;

    @ApiModelProperty(value = "订单状态,已支付，未支付，作废", hidden = true)
    private String state;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createTime;

    @ApiModelProperty(value = "作废时间", hidden = true)
    private Date cancelTime;
}

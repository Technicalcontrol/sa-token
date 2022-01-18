package com.xl.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author XL
 * 操作信息表实体
 * @date 2022/1/14 13:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("operation_log")
public class OperationLog implements Serializable {

    @ApiModelProperty("操作编号")
    @TableId(value = "operation_id",type = IdType.AUTO)
    private Long operationId;

    @ApiModelProperty("操作用户")
    @TableField("operation_name")
    private String operationName;

    @ApiModelProperty("业务类型")
    @TableField("operation_type")
    private String operationType;

    @ApiModelProperty("操作标题")
    @TableField("operation_title")
    private String operationTitle;

    @ApiModelProperty("操作时间")
    @TableField("operation_time")
    private Date operationTime;
}

package com.tgw.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tgw.mybatis.enums.ApiChannelCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tgw
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("loan_api_channel")
public class LoanApiChannel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 渠道编码
     */
    private ApiChannelCode apiCode;

    /**
     * 渠道名称
     */
    private String apiName;
    /**
     * 逻辑删除字段
     * @TableLogic 表字段逻辑处理注解（逻辑删除）
     *             :删除 1:正常
     */
    @TableLogic(value="1",delval="0")
    @JsonIgnore
    private Integer isDelete;


}

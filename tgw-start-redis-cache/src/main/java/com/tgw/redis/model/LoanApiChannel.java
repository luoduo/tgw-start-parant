package com.tgw.redis.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author tgw
 * @since 2019-11-28
 */

@Entity
@Table(name = "loan_api_channel")
@Data
public class LoanApiChannel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    /**
     * 渠道编码
     */
    @Column(name = "api_code")
    private String apiCode;

    /**
     * 渠道名称
     */
    @Column(name = "api_name")
    private String apiName;
    /**
     * 逻辑删除字段
     * @TableLogic 表字段逻辑处理注解（逻辑删除）
     *             :删除 1:正常
     */
    @Column(name = "is_delete")
    private Integer isDelete;
    
    @Column(name = "create_time")
    private LocalDateTime createTime;


}

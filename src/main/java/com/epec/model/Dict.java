package com.epec.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 * @Author zhaoxianxing
 * @Date 2023/5/15 15:30
 */
@Data
@TableName(value ="t_dict")
public class Dict {
    @TableId(value = "dict_id")
    private Long dictId;

    private String statusCode;

    private String statusValue;

    private Short enabled;
}

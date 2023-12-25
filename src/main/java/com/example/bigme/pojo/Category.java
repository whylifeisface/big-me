package com.example.bigme.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class Category {

    @NotNull(groups = Update.class)
    private Integer id;//主键ID

    @NotEmpty
    private String categoryName;//分类名称
    @NotEmpty
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;//更新时间


    //指定分组校验
    //https://zhuanlan.zhihu.com/p/447694886
    public interface Add extends Default {

    }

    public interface Update extends Default {
    }
}

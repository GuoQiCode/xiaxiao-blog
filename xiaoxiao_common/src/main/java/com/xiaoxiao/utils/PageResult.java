package com.xiaoxiao.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @project_name:bz_parent
 * @date:2019/9/17:11:37
 * @author:shinelon
 * @Describe:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult
{
    /**
     * 数据
     */
    private List<?> result;
    /**
     * 总共多少条
     */
    private Long totalRows;
    /**
     * 当前页
     */
    private Integer curPage = 1;
    /**
     * 每页多少条数据
     */
    private Integer pageSize = 10;
    /**
     * 总共多少页
     */
    private Integer totalPages;




}

package com.xl.demo.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author XL
 * 树形结构实体
 * @date 2022/1/18 16:33
 */
@Data
public class TreeList implements Serializable {

    private Long id;

    private String label;

    private Long parentId;

    private List<TreeList> children;

}

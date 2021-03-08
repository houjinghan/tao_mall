package com.taotao.manage.service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.mapper.ItemParamMapper;
import com.taotao.manage.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.pojo.ItemParam;

import java.util.List;

@Service
public class ItemParamService extends BaseService<ItemParam>{

    @Autowired
    private ItemParamMapper itemParamMapper;

    public EasyUIResult queryItemList(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        Example example=new Example(ItemParam.class);
        // 按照创建时间排序
        example.setOrderByClause("created DESC");
        List<ItemParam> list=this.itemParamMapper.selectByExample(example);
        PageInfo pageInfo=new PageInfo(list);
        return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
    }

}

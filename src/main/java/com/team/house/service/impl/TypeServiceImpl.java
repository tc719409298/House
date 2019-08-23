package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;
import com.team.house.mapper.TypeMapper;
import com.team.house.service.TypeService;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(null) ;
    }

    @Override
    public PageInfo<Type> getTypeByPage(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        TypeExample typeExample = new TypeExample();
        List<Type> list = typeMapper.selectByExample(typeExample);
        PageInfo<Type> typePageInfo = new PageInfo<>(list);
        return typePageInfo;
    }

    @Override
    public int addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override
    public Type selectOneType(int id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public int delOneType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMOreType(Integer[] ids) {
        return typeMapper.delMOreType(ids);
    }
}

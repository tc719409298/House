package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
   private StreetMapper streetMapper;
    @Override
    //查询整个区域
    public List<District> getAllDistrict() {
        DistrictExample districtExample = new DistrictExample();
        List<District> list = districtMapper.selectByExample(districtExample);
        return list ;
    }

    @Override
    public PageInfo<District> getDistrictByPage(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        DistrictExample districtExample = new DistrictExample();
        List<District> list = districtMapper.selectByExample(districtExample);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public District selectOneDistrict(int id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    @Transactional//事务
    public int delOneDistrict(Integer id) {
         districtMapper.deleteByPrimaryKey(id);
         streetMapper.delStreetBydid(id);
         return 1;

    }

    @Override //区域删除
    public int delMOreDistrict(Integer[] ids) {
        return districtMapper.delMOreDistrict(ids);
    }
}

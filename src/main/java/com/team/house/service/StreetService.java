package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.utils.Page;

import java.util.List;

public interface StreetService {
        PageInfo<Street> getStreetByDid(Page page,Integer did);
        int addStreet(Street street);
        List<Street>getAllStreetByDid(Integer did);

}

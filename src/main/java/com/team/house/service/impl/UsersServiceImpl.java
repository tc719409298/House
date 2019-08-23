package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UsersService;
import com.team.house.utils.MD5Utils;
import com.team.house.utils.UsersCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo<Users> getUsers(UsersCondition usersCondition) {
        //开启分页
        PageHelper.startPage(usersCondition.getPage(),usersCondition.getRows());
        //条件查询
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(0);
        //查看名称条件
        if (usersCondition.getName()!=null){
            criteria.andNameLike("%"+usersCondition.getName()+"%");
        }
        //查看电话号码条件
        if (usersCondition.getTelephone()!=null){
            criteria.andTelephoneLike("%"+usersCondition.getTelephone()+"%");
        }
        List<Users> list = usersMapper.selectByExample(usersExample);
        return new PageInfo<Users>(list);
    }

    @Override
    public int addUser(Users users) {
        String password = users.getPassword();
        String s = MD5Utils.md5Encrypt(password);
        users.setPassword(s);
        users.setIsadmin(0);
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users getOneUsers(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override //查询用户名称是否存在
    public int getUserName(String name) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
         criteria.andIsadminEqualTo(0);
         criteria.andNameEqualTo(name);
        List<Users> list = usersMapper.selectByExample(usersExample);
         return list.size();
    }

    @Override
    public Users login(String name, String password) {
        //传用户 跟密码
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        // 加密密码，看是否跟数据库的密码匹配
         password=MD5Utils.md5Encrypt(password);
        criteria.andPasswordEqualTo(password);
        //执行查询
        List<Users> list = usersMapper.selectByExample(usersExample);
        //判断l用户与密码是否存在
        if (list.size()==0){
            return null;
        }else {
            return list.get(0);
        }
    }
}

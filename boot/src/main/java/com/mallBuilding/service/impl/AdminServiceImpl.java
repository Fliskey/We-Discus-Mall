package com.mallBuilding.service.impl;

import com.mallBuilding.entity.Admin;
import com.mallBuilding.mapper.AdminMapper;
import com.mallBuilding.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Fliskey
 * @since 2021-07-07
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}

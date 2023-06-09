package com.huasheng.dingding.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huasheng.dingding.domain.entity.ClockIn;
import com.huasheng.dingding.domain.entity.CustomerBaobiao;
import com.huasheng.dingding.domain.entity.CustomerRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustomerRecordBaoBiaoMapper extends BaseMapper<CustomerBaobiao> {

    Page<CustomerBaobiao> selectCustomerBaoBiaoVo(IPage<CustomerBaobiao> page,
                                                 @Param(Constants.WRAPPER) Wrapper<CustomerBaobiao> queryWrapper);

    List<CustomerBaobiao> exportCustomerBaoBiaoVo(@Param(Constants.WRAPPER) Wrapper<CustomerBaobiao> queryWrapper);

}

package com.huasheng.dingding.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huasheng.dingding.domain.entity.ClockIn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.toolkit.Constants;

import java.util.List;

@Mapper
@Repository
public interface ClockInMapper extends BaseMapper<ClockIn> {

    Page<ClockIn> selectByCallInCondition(IPage<ClockIn> page, @Param(Constants.WRAPPER) Wrapper<ClockIn> queryWrapper);

    List<ClockIn> exportByCallInCondition(@Param(Constants.WRAPPER) Wrapper<ClockIn> queryWrapper);

}

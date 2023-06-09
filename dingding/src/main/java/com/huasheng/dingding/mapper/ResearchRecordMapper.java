package com.huasheng.dingding.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huasheng.dingding.domain.entity.CustomerRecord;
import com.huasheng.dingding.domain.entity.ResearchRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ResearchRecordMapper extends BaseMapper<ResearchRecord> {

    Page<ResearchRecord> selectResearchRecord(IPage<ResearchRecord> page, @Param(Constants.WRAPPER) Wrapper<ResearchRecord> queryWrapper);

}

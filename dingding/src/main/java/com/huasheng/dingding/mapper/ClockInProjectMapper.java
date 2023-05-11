package com.huasheng.dingding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huasheng.dingding.domain.entity.CallInProject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClockInProjectMapper extends BaseMapper<CallInProject> {

    boolean upProjectStatus (Long id);

}

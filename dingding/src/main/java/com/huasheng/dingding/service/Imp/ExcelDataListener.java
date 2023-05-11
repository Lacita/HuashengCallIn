package com.huasheng.dingding.service.Imp;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.huasheng.dingding.domain.entity.ExcelEntity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ExcelDataListener implements ReadListener<ExcelEntity> {

    private static final int BATCH_COUNT = 100;

    private static final List<ExcelEntity> CACHED_DATA_LIST = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Getter
    private String errorMsg;

    @Override
    public void invoke(ExcelEntity excelEntity, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSONUtil.toJsonStr(excelEntity));
        this.errorMsg = StrFormatter.format("导入数据第{}行校验不通过！", analysisContext.readRowHolder().getRowIndex());
        CACHED_DATA_LIST.add(excelEntity);
        if (CACHED_DATA_LIST.size() >= BATCH_COUNT) {
            CACHED_DATA_LIST.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        log.info("所有数据解析完成！");
    }
}

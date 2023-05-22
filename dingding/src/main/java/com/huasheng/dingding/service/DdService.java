package com.huasheng.dingding.service;

import com.huasheng.dingding.common.Result.Result;
import com.huasheng.dingding.domain.dto.ClockInDto;
import com.huasheng.dingding.domain.dto.ClockQueryDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface DdService {

    Result<String> login(String userName,String password);

    Result<String> clockInMessage(String userName, String userId, String type, String location, String project,String note,double longitude,double latitude);

    Result<Map<String, Object>> getUserInfo(String code);

    Result<Map<String, Object>> getClockInRecord(ClockQueryDto clockQueryDto);

    void exportCallInRecord(ClockQueryDto clockQueryDto, HttpServletResponse response) throws IOException;

    Result<Map<String, Object>> showProject(String projectId,String projectName, Integer page, Integer size);

    Result<String> addNewProject(String projectId, String projectName);

    Result<String> updateProject(Long id);

    Result<Map<String, Object>> getClockInRecordWithNoProject(ClockQueryDto clockQueryDto);

    void exportWithNoProject(ClockQueryDto clockQueryDto, HttpServletResponse response) throws IOException;
}

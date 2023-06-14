package com.huasheng.dingding.service.Imp;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huasheng.dingding.Exception.MyException;
import com.huasheng.dingding.common.Result.Result;
import com.huasheng.dingding.common.Result.ResultUtils;
import com.huasheng.dingding.config.DateUtils;
import com.huasheng.dingding.domain.dto.CustomerInfoDto;
import com.huasheng.dingding.domain.dto.CustomerRecordDto;
import com.huasheng.dingding.domain.dto.ResearchRecordDto;
import com.huasheng.dingding.domain.entity.*;
import com.huasheng.dingding.mapper.CustomerInfoMapper;
import com.huasheng.dingding.mapper.CustomerRecordBaoBiaoMapper;
import com.huasheng.dingding.mapper.CustomerRecordMapper;
import com.huasheng.dingding.mapper.ResearchRecordMapper;
import com.huasheng.dingding.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImp extends ServiceImpl<CustomerInfoMapper,CustomerInfo> implements CustomerService {

    private static final String[] IGNORE_ISOLATOR_PROPERTIES = new String[]{"id"};

    private static final String[] IGNORE_ISOLATOR_PROPERTIES_BAO_BIAO = new String[]{"id","updateTime"};

    @Resource
    private CustomerInfoMapper customerInfoMapper;

    @Resource
    private CustomerRecordMapper customerRecordMapper;

    @Resource
    private ResearchRecordMapper researchRecordMapper;

    @Resource
    private CustomerRecordBaoBiaoMapper customerRecordBaoBiaoMapper;

    @Override
    public Result<CustomerInfo> showCustomerInfo(CustomerInfoDto customerInfoDto) {
        try {
            QueryWrapper<CustomerInfo> wrapper = new QueryWrapper<>();
            wrapper.eq(customerInfoDto.getId()>0, "id", customerInfoDto.getId());
            CustomerInfo customerInfo = customerInfoMapper.selectOne(wrapper);
            return ResultUtils.SUCCESS_DATA(customerInfo);
        } catch (Exception e) {
            log.error("客户信息查询失败:" + e);
            throw new MyException(e.toString());
        }
    }

    @Override
    @Transactional
    public Result<String> updateCustomerInfo(CustomerInfoDto customerInfoDto) {
         long id = customerInfoDto.getId();
         if(id <= 0){
            return ResultUtils.ERROR("客户信息异常");
         }
        CustomerInfo customerInfo = new CustomerInfo();
        BeanUtils.copyProperties(customerInfoDto,customerInfo);
        boolean update = this.update(customerInfo, null);
        if (update){
            return ResultUtils.SUCCESS();
        }
        return ResultUtils.ERROR("客户信息更新失败");
    }

    @Override
    @Transactional
    public Result<String> insertCustomerInfo(CustomerInfoDto customerInfoDto) {
        if (StringUtils.isBlank(customerInfoDto.getCustomerName())) {
            return ResultUtils.ERROR("客户名称不能为空");
        }
        QueryWrapper<CustomerInfo> customerInfoQueryWrapper = new QueryWrapper<>();
        customerInfoQueryWrapper.eq("customer_name",customerInfoDto.getCustomerName());
        Integer count = customerInfoMapper.selectCount(customerInfoQueryWrapper);
        if (count > 0){
            return ResultUtils.ERROR("客户信息已录入");
        }
        CustomerInfo customerInfo = new CustomerInfo();
//        customerInfo.setCustomerName(customerInfoDto.getCustomerName());
//        customerInfo.setCustomerCode(customerInfoDto.getCustomerCode());
        BeanUtils.copyProperties(customerInfoDto,customerInfo,IGNORE_ISOLATOR_PROPERTIES);
        boolean save = this.save(customerInfo);
        if (!save){
            return ResultUtils.ERROR("客户信息插入异常");
        }
        return ResultUtils.SUCCESS();
    }

    @Override
    public Result<Map<String,Object>> selectCustomerInfoRecord(CustomerInfoDto customerInfoDto) {
        try{
            QueryWrapper<CustomerRecord> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(StringUtils.isNotBlank(customerInfoDto.getCustomerCode()), "customer_code", customerInfoDto.getCustomerCode());
            Page<CustomerRecord> callInProjectPage = new Page<>(customerInfoDto.getPage(),customerInfoDto.getSize());
            Page<CustomerRecord> customerRecordPage = customerRecordMapper.selectCustomerRecord(callInProjectPage, queryWrapper);
            List<CustomerRecord> records = customerRecordPage.getRecords();
            long total = customerRecordPage.getTotal();
            Map<String, Object> map = new HashMap<>();
            map.put("data",records);
            map.put("total",total);
            return ResultUtils.SUCCESS_DATA(map);
        }
        catch (Exception e){
            log.error("客户进展记录查询失败,异常代码为:{}",e.getMessage());
            return ResultUtils.SUCCESS_DATA(null);
        }
    }

    @Override
    public Result<Map<String,Object>> selectResearchInfoRecord(CustomerInfoDto customerInfoDto) {
        try{
            QueryWrapper<ResearchRecord> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(StringUtils.isNotBlank(customerInfoDto.getCustomerCode()) , "customer_code", customerInfoDto.getCustomerCode());
            Page<ResearchRecord> callInProjectPage = new Page<>(customerInfoDto.getPage(),customerInfoDto.getSize());
            Page<ResearchRecord> customerRecordPage = researchRecordMapper.selectResearchRecord(callInProjectPage, queryWrapper);
            List<ResearchRecord> records = customerRecordPage.getRecords();
            long total = customerRecordPage.getTotal();
            Map<String, Object> map = new HashMap<>();
            map.put("data",records);
            map.put("total",total);
            return ResultUtils.SUCCESS_DATA(map);
        }
        catch (Exception e){
            log.error("项目打样记录查询失败,异常代码为:{}",e.getMessage());
            return ResultUtils.SUCCESS_DATA(null);
        }
    }

    @Override
    @Transactional
    public Result<String> insertCustomerProcess(CustomerRecordDto customerRecordDto) {
        long customerId = customerRecordDto.getCustomerId();
        if (customerId <= 0){
            return ResultUtils.ERROR("客户信息不能为空");
        }
        CustomerRecord customerRecord = new CustomerRecord();
        BeanUtils.copyProperties(customerRecordDto,customerRecord,IGNORE_ISOLATOR_PROPERTIES);
        int insert = customerRecordMapper.insert(customerRecord);
        if (insert>0){
            CustomerBaobiao customerBaobiao = new CustomerBaobiao();
            BeanUtils.copyProperties(customerRecord,customerBaobiao,IGNORE_ISOLATOR_PROPERTIES_BAO_BIAO);
            customerBaobiao.setCustomerUpdateTime(DateUtils.getStringDate());
            customerRecordBaoBiaoMapper.insert(customerBaobiao);
            return ResultUtils.SUCCESS();
        }
        return ResultUtils.ERROR("数据填写失败");
    }

    @Override
    @Transactional
    public Result<String> insertResearchProcess(ResearchRecordDto researchRecordDto) {
        long customerId = researchRecordDto.getCustomerId();
        if (customerId <= 0){
            return ResultUtils.ERROR("客户信息不能为空");
        }
        ResearchRecord researchRecord = new ResearchRecord();
        BeanUtils.copyProperties(researchRecordDto,researchRecord,IGNORE_ISOLATOR_PROPERTIES);
        int insert = researchRecordMapper.insert(researchRecord);
        if (insert>0){
            CustomerBaobiao customerBaobiao = new CustomerBaobiao();
            customerBaobiao.setCustomerId(researchRecordDto.getCustomerId());
            customerBaobiao.setResearchExistingProblem(researchRecordDto.getExistingProblem());
            customerBaobiao.setResearchSituation(researchRecordDto.getResearchSituation());
            customerBaobiao.setResearchSolution(researchRecordDto.getSolution());
            customerBaobiao.setResearchFeedbackUser(researchRecordDto.getFeedbackUser());
            customerBaobiao.setResearchUpdateTime(DateUtils.getStringDate());
            customerRecordBaoBiaoMapper.insert(customerBaobiao);
            return ResultUtils.SUCCESS();
        }
        return ResultUtils.ERROR("数据填写失败");
    }

    @Override
    public Result<List<CustomerInfo>> getCustomerLists() {
        try {
            QueryWrapper<CustomerInfo> customerInfoQueryWrapper = new QueryWrapper<>();
            customerInfoQueryWrapper.orderByDesc("id");
            List<CustomerInfo> customerInfos = customerInfoMapper.selectList(customerInfoQueryWrapper);
            return ResultUtils.SUCCESS_DATA(customerInfos);
        }
        catch (Exception e){
            log.error("获取客户项目失败，问题为：{}",e.toString());
            return ResultUtils.SUCCESS_DATA(null);
        }
    }

    @Override
    public Result<Map<String, Object>> showCustomerVo(CustomerInfoDto customerInfoDto) {
        try {
            QueryWrapper<CustomerInfo> infoQueryWrapper = new QueryWrapper<>();
            infoQueryWrapper.like(StringUtils.isNotBlank(customerInfoDto.getCustomerName()),"customer_name",customerInfoDto.getCustomerName());
            infoQueryWrapper.like(StringUtils.isNotBlank(customerInfoDto.getCustomerCode()),"customer_code",customerInfoDto.getCustomerCode());
            infoQueryWrapper.eq(StringUtils.isNotBlank(customerInfoDto.getCurrentStatus()),"current_status",customerInfoDto.getCurrentStatus());
            Page<CustomerInfo> infoPage = new Page<>(customerInfoDto.getPage(), customerInfoDto.getSize());
            Page<CustomerInfo> customerInfoPage = customerInfoMapper.selectCustomByVo(infoPage, infoQueryWrapper);
            HashMap<String, Object> map = new HashMap<>();
            List<CustomerInfo> records = customerInfoPage.getRecords();
            long total = customerInfoPage.getTotal();
            map.put("data",records);
            map.put("total",total);
            return ResultUtils.SUCCESS_DATA(map);
        }catch (Exception e){
            log.error("用户条件查询失败：{}",e.toString());
            throw  new MyException(e.toString());
        }
    }

    @Override
    public void exportCustomerRecord(CustomerInfoDto customerInfoDto, HttpServletResponse response) {
        try{
            String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx" );
            EasyExcel.write(response.getOutputStream(), CustomerExcel.class)
                    .sheet("客户报表").doWrite(getCustomerBaoBIAO(customerInfoDto));
        }catch (Exception e){
            log.error(e.toString());
            throw new MyException(e.toString());
        }
    }

    private List<CustomerExcel> getCustomerBaoBIAO(CustomerInfoDto customerInfoDto) {
        QueryWrapper<CustomerInfo> customerInfoQueryWrapper = new QueryWrapper<>();
        customerInfoQueryWrapper.eq(StringUtils.isNotBlank(customerInfoDto.getCurrentStatus()),"current_status",
                customerInfoDto.getCurrentStatus());
        customerInfoQueryWrapper.like(StringUtils.isNotBlank(customerInfoDto.getCustomerName()),"customer_name",
                customerInfoDto.getCustomerName());
        List<Long> collect = customerInfoMapper.selectList(customerInfoQueryWrapper).
                stream().map(CustomerInfo::getId).
                collect(Collectors.toList());
        List<CustomerExcel> excelParamList = Lists.newArrayList();
        try {
            if (CollectionUtils.isNotEmpty(collect)) {
                for (Long aLong : collect) {
                    QueryWrapper<CustomerBaobiao> customerId = new QueryWrapper<CustomerBaobiao>().eq("customer_id", aLong);
                    List<CustomerBaobiao> customerRecords = customerRecordBaoBiaoMapper.exportCustomerBaoBiaoVo(customerId);
                    customerRecords.forEach(r->{
                        CustomerExcel customerExcel = new CustomerExcel();
                        customerExcel.setCustomerName(r.getCustomerName());
                        customerExcel.setExistingProblem(r.getExistingProblem());
                        customerExcel.setCustomerUpdateTime(r.getCustomerUpdateTime());
                        customerExcel.setFeedbackUser(r.getFeedbackUser());
                        customerExcel.setVisitSituation(r.getVisitSituation());
                        customerExcel.setSolution(r.getSolution());
                        customerExcel.setPlan(r.getPlan());
                        customerExcel.setSamplingInfo(r.getSamplingInfo());
                        customerExcel.setResearchSituation(r.getResearchSituation());
                        customerExcel.setCoatingScheme(r.getCoatingScheme());
                        customerExcel.setResearchFeedbackUser(r.getResearchFeedbackUser());
                        customerExcel.setResearchUpdateTime(r.getResearchUpdateTime());
                        customerExcel.setResearchSolution(r.getSolution());
                        customerExcel.setResearchSituation(r.getResearchSituation());
                        excelParamList.add(customerExcel);
                    });
                }
                return excelParamList;
            }
            return null;
        }catch (Exception e){
            throw new MyException(e.toString());
        }
    }

    @Override
    public Result<Map<String, Object>> showCustomerReportVo(CustomerInfoDto customerInfoDto) {
        try{
            QueryWrapper<CustomerBaobiao> customerBaobiaoQueryWrapper = new QueryWrapper<>();
            customerBaobiaoQueryWrapper.eq(StringUtils.isNotBlank(customerInfoDto.getCurrentStatus()),
                    "current_status",customerInfoDto.getCurrentStatus());
            customerBaobiaoQueryWrapper.like(StringUtils.isNotBlank(customerInfoDto.getCustomerName()),
                    "customer_name",customerInfoDto.getCustomerName());
            Page<CustomerBaobiao> baobiaoPage = new Page<>(customerInfoDto.getPage(),customerInfoDto.getSize());
            Page<CustomerBaobiao> customerBaobiaoPage = customerRecordBaoBiaoMapper.selectCustomerBaoBiaoVo(baobiaoPage, customerBaobiaoQueryWrapper);
            List<CustomerBaobiao> records = customerBaobiaoPage.getRecords();
            long total = customerBaobiaoPage.getTotal();
            Map<String, Object> map = new HashMap<>();
            map.put("data",records);
            map.put("total",total);
            return ResultUtils.SUCCESS_DATA(map);
        }catch (Exception e){
            log.error("用户报表查询失败：{}",e.toString());
            return ResultUtils.SUCCESS_DATA(null);
        }
    }

}

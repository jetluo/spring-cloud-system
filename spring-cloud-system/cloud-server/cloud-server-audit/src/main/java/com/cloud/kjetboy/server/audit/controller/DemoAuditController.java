package com.cloud.kjetboy.server.audit.controller;

import com.cloud.kjetboy.server.audit.annotation.Audit;
import com.cloud.kjetboy.server.audit.entity.Demo;
import com.cloud.kjetboy.server.common.bean.RestResult;
import com.cloud.kjetboy.server.common.constants.CommonConstants;
import com.cloud.kjetboy.server.common.controller.BaseController;
import com.cloud.kjetboy.server.common.exception.BusinessException;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jet
 */
@RestController
@RequestMapping("/audit")
@Audit(parentName = "审计/查询审计数据")
public class DemoAuditController extends BaseController {
    protected final static Log logger = LogFactory.getLog(DemoAuditController.class);

    @Audit(categoryName = "/查询所有数据", defaultAuditItem = true)
    @RequestMapping("/findAll")
    public RestResult findAll(@RequestParam Map<String, Object> param) {
        PageInfo<Demo> pageInfo = new PageInfo<Demo>();
        try {
            List<Demo> list = new ArrayList<>();
            pageInfo = new PageInfo<Demo>(list);
        } catch (BusinessException e) {
            return RestResult.restFail(CommonConstants.ERROR_RESPONSE_CODE, "xxx信息查询失败");
        }
        return RestResult.restSuccess(CommonConstants.SUCCESS_RESPONSE_CODE, pageInfo);
    }

}

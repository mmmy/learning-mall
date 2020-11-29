package com.example.mall.learning.controller;

import com.example.mall.learning.common.api.CommonPage;
import com.example.mall.learning.common.api.CommonResult;
import com.example.mall.learning.mbg.model.PmsBrand;
import com.example.mall.learning.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService demoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        List<PmsBrand> brandList = demoService.listAllBrand();
        return CommonResult.success(brandList);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = demoService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
        }
        LOGGER.debug("create Brand " + (count == 1 ? "success" : "failed") + ":{}", pmsBrand);
        return commonResult;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = demoService.updateBrand(id, pmsBrand);
        commonResult = count == 1 ? CommonResult.success(pmsBrand) : CommonResult.failed("操作失败");
        LOGGER.debug("update Brand " + (count == 1 ? "success" : "failed") + ":{}", pmsBrand);
        return commonResult;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = demoService.deleteBrand(id);
        boolean isSuccess = count == 1;
        LOGGER.debug("delete Brand " + (isSuccess ? "success" : "failed") + ":id={}", id);
        return isSuccess ? CommonResult.success(null) : CommonResult.failed("操作失败");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<PmsBrand> brandList = demoService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.resetPage(brandList));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") long id) {
        return CommonResult.success(demoService.getBrand(id));
    }
}

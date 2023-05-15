package com.epec.controller;

import com.alibaba.fastjson2.JSON;
import com.epec.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author zhaoxianxing
 * @Date 2023/5/15 15:40
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @GetMapping("getDict")
    public String getDict(@RequestParam(value = "statusCode", required = false) String statusCode) {
        return JSON.toJSONString(dictService.getDict(statusCode));
    }

    @GetMapping("saveDict")
    public String saveDict(@RequestParam(value = "statusCode", required = false) String statusCode,
                           @RequestParam(value = "statusValue", required = false) String statusValue) {
        return JSON.toJSONString(dictService.saveDict(statusCode, statusValue));
    }

    @GetMapping("updateDict")
    public String updateDict(@RequestParam(value = "statusCode", required = false) String statusCode,
                             @RequestParam(value = "statusValue", required = false) String statusValue) {
        return JSON.toJSONString(dictService.updateDict(statusCode, statusValue));
    }
}

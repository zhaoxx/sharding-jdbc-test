package com.epec.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.epec.config.ShardingJdbcMaster;
import com.epec.mapper.DictMapper;
import com.epec.model.Dict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author zhaoxianxing
 * @Date 2023/5/15 15:33
 */
@Service
public class DictService {

    @Resource
    private DictMapper dictMapper;

    public int saveDict(String statusCode, String statusValue) {
        Dict dict = new Dict();
        dict.setStatusCode(statusCode);
        dict.setStatusValue(statusValue);
        return dictMapper.insert(dict);
    }

    public int updateDict(String statusCode, String statusValue) {
        LambdaUpdateWrapper<Dict> updateWrapper = new LambdaUpdateWrapper();
        updateWrapper.eq(Dict::getStatusCode, statusCode);
        updateWrapper.set(Dict::getStatusValue, statusValue);
        return dictMapper.update(null, updateWrapper);
    }

    @ShardingJdbcMaster
    public List<Dict> getDict(String statusCode) {
        LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Dict::getStatusCode, statusCode);
        return dictMapper.selectList(queryWrapper);
    }
}

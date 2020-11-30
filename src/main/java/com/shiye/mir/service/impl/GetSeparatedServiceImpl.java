package com.shiye.mir.service.impl;

import com.shiye.mir.service.GetSeparatedService;
import org.springframework.stereotype.Service;

@Service
public class GetSeparatedServiceImpl implements GetSeparatedService {
    @Override
    public void doSeparate(Integer uid, boolean getBeat, boolean getVocal){
        //1.从缓存中调出uid

        //2.调出redis中的文件

        //3.调用separate,输入

        //4.导出文件
    }
}

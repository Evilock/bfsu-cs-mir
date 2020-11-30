package com.shiye.mir.service;


/**
 * 启动伴奏分离类
 * @author fangshaozu_sx
 */
public interface GetSeparatedService {
    /**
     * 启动分离
     * @param uid uid是多少
     * @param getBeat 是否要Beat
     * @param getVocal 是否要vocal
     */
    void doSeparate(Integer uid, boolean getBeat, boolean getVocal);
}

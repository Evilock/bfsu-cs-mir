package com.shiye.mir.service;

import com.shiye.mir.enums.EnumResponseCode;


/**
 * 脚本服务
 * @author fangshaozu
 */
public interface ScriptsService {

    /**
     * 生成脚本
     * @param fileName 文件名
     */
    void generateBat(String fileName);

    /**
     * 执行脚本
     * @param cmdPath 脚本路径
     * @return 结果状态码
     */
    EnumResponseCode scriptExec(String cmdPath);

    /**
     * 生成脚本
     * @param fileName 文件名
     */
    void generateShell(String fileName);

    /**
     * 执行脚本
     * @param shellPath 脚本路径
     * @return 结果状态码
     */
    EnumResponseCode shellExec(String shellPath);
}

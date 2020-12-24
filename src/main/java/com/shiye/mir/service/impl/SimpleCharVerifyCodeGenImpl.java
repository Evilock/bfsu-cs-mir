package com.shiye.mir.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.shiye.mir.config.LocalCacheConfig;
import com.shiye.mir.entity.VerifyCode;
import com.shiye.mir.service.IVerifyCodeGen;
import com.shiye.mir.utils.LocalMapCache;
import com.shiye.mir.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Random;

/**
 * 验证码实现类
 * @author fangshaozu
 */
@Slf4j
@Service
public class SimpleCharVerifyCodeGenImpl implements IVerifyCodeGen {

    @Resource(name="localCacheOne")
    private Cache<String,Object> localCache;

    private static final String[] FONT_TYPES = { "\u5b8b\u4f53", "\u65b0\u5b8b\u4f53", "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66" };

    private static final int VALICATE_CODE_LENGTH = 4;

    private static final int VALICATE_CODE_LENGTH_FOR_EMAIL = 10;

    /**
     * 设置背景颜色及大小，干扰线
     */
    private static void fillBackground(Graphics graphics, int width, int height) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        // 加入干扰线条
        for (int i = 0; i < 8; i++) {
            graphics.setColor(RandomUtils.randomColor(40, 150));
            Random random = new Random();
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            graphics.drawLine(x, y, x1, y1);
        }
    }

    /**
     * 生成随机字符
     */
    @Override
    public String generate(int width, int height, OutputStream os) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        fillBackground(graphics, width, height);
        String randomStr = RandomUtils.randomString(VALICATE_CODE_LENGTH);
        createCharacter(graphics, randomStr);
        graphics.dispose();
        ImageIO.write(image, "JPEG", os);
        return randomStr;
    }

    /**
     * 验证码生成
     */
    @Override
    public VerifyCode generate(int width, int height) {
        VerifyCode verifyCode = null;
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            String code = generate(width, height, baos);
            verifyCode = new VerifyCode();
            verifyCode.setCode(code);
            verifyCode.setImgBytes(baos.toByteArray());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            verifyCode = null;
        }
        return verifyCode;
    }

    /**
     * 设置字符颜色大小
     */
    private void createCharacter(Graphics g, String randomStr) {
        char[] charArray = randomStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            g.setColor(new Color(50 + RandomUtils.nextInt(100),
                    50 + RandomUtils.nextInt(100), 50 + RandomUtils.nextInt(100)));
            g.setFont(new Font(FONT_TYPES[RandomUtils.nextInt(FONT_TYPES.length)], Font.BOLD, 26));
            g.drawString(String.valueOf(charArray[i]), 15 * i + 5, 19 + RandomUtils.nextInt(8));
        }
    }

    @Override
    public String emailVerifyCode(String to){
        String verifyCode = RandomUtils.randomString(VALICATE_CODE_LENGTH_FOR_EMAIL);
        //localCache.put(to,verifyCode);
        System.out.println(verifyCode);
        LocalMapCache.getEmailCache().put(to,verifyCode);
        return verifyCode;
    }
}
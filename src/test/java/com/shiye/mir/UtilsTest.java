package com.shiye.mir;

import com.shiye.mir.utils.CompressDirUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilsTest {


    @Test
    public void compressTwo(){
        //需要压缩的文件夹的目录
        String compressPath = "C:\\Users\\fangshaozu_sx\\Desktop\\123";
        //压缩文件
        boolean bl = CompressDirUtil.compressFileToZip(compressPath);
        if(bl){
            System.out.println("压缩成功");
        }
    }
}

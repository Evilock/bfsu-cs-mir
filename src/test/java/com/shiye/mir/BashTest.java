package com.shiye.mir;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class BashTest {

    @Test
    public void testCmd(){
        try{
            Process p =  Runtime.getRuntime().exec("C:\\Users\\Thinkpad\\music.bat");
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while(output.readLine() != null) {
                System.out.println(output.readLine());
            }
        }catch(Exception e) {
            System.out.println("startup Exception: " + e);
        }
    }

    @Test
    public void testMa() throws IOException {
        System.out.println("testMa");
        Process p;
        List<String> commandList = Lists.newArrayList();
        commandList.add("cmd /c mysql --version");
        for(String command : commandList){
            System.out.println(command);
            p = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line + "\n");
            }
        }
    }

    @Test
    public void kkk() {
        Process p;
        //test.bat中的命令是ipconfig/all
        String cmd="C:\\Users\\Thinkpad\\music.bat";
        try {
            //执行命令
            p = Runtime.getRuntime().exec(cmd);
            //取得命令结果的输出流
            InputStream fis=p.getInputStream();
            //用一个读输出流类去读
            InputStreamReader isr=new InputStreamReader(fis);
            //用缓冲器读行
            BufferedReader br=new BufferedReader(isr);
            String line=null;
            //直到读完为止
            while((line=br.readLine())!=null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}

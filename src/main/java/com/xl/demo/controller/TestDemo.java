package com.xl.demo.controller;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xl.demo.annotation.Log;
import com.xl.demo.domain.Menu;
import com.xl.demo.domain.Role;
import com.xl.demo.domain.User;
import com.xl.demo.domain.vo.TreeList;
import com.xl.demo.service.MenuService;
import com.xl.demo.service.UserService;
import com.xl.demo.utils.ExcelDataListener;
import com.xl.demo.utils.MyUtils;
import com.xl.demo.utils.RedisUtils;
import com.xl.demo.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

/**
 * @author XL
 * 测试
 * @date 2022/1/11 14:59
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试集合")
@Slf4j
public class TestDemo {

    @Autowired
    private MenuService menuService;

    @Autowired
    private  RedisUtils redisUtils;

    @Autowired
    private UserService userServicel;

    @GetMapping("/demo")
    public String demo(){
        return "Hello World!";
    }

    @GetMapping("/set/key")
    @ApiOperation("设置键值")
    @ApiOperationSupport(order = 250)
    public ResultJson<String> setKey(String name,String value){
        redisUtils.set(name,value);
        return ResultJson.success(name+"设置成功！");
    }

    @GetMapping("/get/key")
    @ApiOperation("获取键值")
    @ApiOperationSupport(order = 251)
    public ResultJson<Object> getKey(String name){
        String value = "没有"+name;
        Object obj = redisUtils.get(name);
        if(obj==null){
            obj = value;
        }
        return ResultJson.success(obj);
    }

    @GetMapping("/md")
    public String md(String md){
        StpUtil.checkPermission("test-md");
        return SaSecureUtil.md5(md);
    }

    @GetMapping("/sha1")
    @ApiOperation("sha1加密")
    public ResultJson<String> password(String pa){
        return ResultJson.success(SaSecureUtil.sha1(pa));
    }

    @GetMapping("/sha2")
    @ApiOperation("sha256加密")
    public ResultJson<String> password2(String pa){
        return ResultJson.success(SaSecureUtil.sha1(pa));
    }


    @GetMapping("/menu")
    @ApiOperationSupport(order = 200)
    @ApiOperation("获取菜单")
    public ResultJson<List<TreeList>> getMenus(){
        log.info("菜单获取");
        System.out.println(userServicel.getById(6));
        return ResultJson.success(menuService.getTreeMenu());
    }

    @ApiOperationSupport(order = 1)
    @GetMapping("/testDemo")
    public ResultJson<String> testDemo(){
        StpUtil.checkPermission("test-testDemo");
        return ResultJson.success("this is a JSON!");
    }

    @Log(title = "测试地址" ,type = "系统测试")
    @GetMapping("/url")
    public ResultJson<Object> testUrl(String ip){
        StpUtil.checkPermission("test-url");
        return ResultJson.success(MyUtils.getLocationByIp(ip));
    }

    @ApiOperationSupport(order = 20)
    @ApiOperation("动态获取")
    @GetMapping("/user")
    public ResultJson<List<User>> getUsers(){
        return ResultJson.success(userServicel.getUsersAll());
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation("导出Excel")
    @GetMapping("/write")
    public void write(String name){
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入

        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName = "E://"+name+System.currentTimeMillis()+".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, User.class)
                .sheet("用户信息").autoTrim(true)
                .doWrite(userServicel.getUsersAll());

    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("导入Excel")
    @GetMapping("/impl")
    public void impl(){
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入

        String fileName = "E:/user.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, User.class, new PageReadListener<User>(dataList -> {
            for (User user : dataList) {
                System.out.println(user);
            }
        })).sheet().doRead();

    }
}

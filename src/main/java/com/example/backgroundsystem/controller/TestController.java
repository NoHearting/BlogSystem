package com.example.backgroundsystem.controller;

import com.example.backgroundsystem.exception.BlogException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {


    @GetMapping("markdown")
    public String markdown(Integer id,Map<String,Integer> map){
        map.put("id",id);
        return "test/testMarkDown";
    }

    @GetMapping("singleMarkdown")
    public String singleMarkdown(Integer ids,Map<String,Integer> map){
        if(null != ids){
            System.out.println(ids);
            map.put("id",ids);
        }else{
            System.out.println("没有id");
        }
        map.put("id",1);
        return "test/markdown";
    }




    @RequestMapping("/testForm")
    public String testForm(){
        return "test/testForm";
    }

    @ResponseBody
    @RequestMapping("testFormInfo")
    public String testFormInfo(String name){
        System.out.println(name);
        return "success";
    }

    @RequestMapping("testPage")
    public String test(){
        return "test/testPage";
    }

    @RequestMapping("testWriteMarkdown")
    public String testWrite(){
        return "test/testWriteMarkdown";
    }


    @RequestMapping("bgIndex")
    public String  bkIndex(){
        return "BkSys/index";
    }

    @RequestMapping("bgCharts")
    public String bgCharts(){
        return "BkSys/pages/charts/chartjs";
    }

    @RequestMapping("bgForms")
    public String bgForms(){
        return "BkSys/pages/forms/basic_elements";
    }

    @RequestMapping("bgIcons")
    public String bgIcons(){
        return "BkSys/pages/icons/mdi";
    }

    @RequestMapping("bgBlankPage")
    public String bgBlankPage(){
        return "BkSys/pages/samples/blank-page";
    }

    @RequestMapping("bgError404")
    public String bgError404(){
        return "BkSys/pages/samples/error-404";
    }

    @RequestMapping("bgError500")
    public String bgError500(){
        return "BkSys/pages/samples/error-500";
    }

    @RequestMapping("bgLogin")
    public String bgLogin(){
        return "BkSys/pages/samples/login";
    }
    @RequestMapping("bgRegister")
    public String bgRegister(){
        return "BkSys/pages/samples/register";
    }

    @RequestMapping("bgBasicTable")
    public String bgBasicTable(){
        return "BkSys/pages/samples/basic-table";
    }

    @RequestMapping("bgButtons")
    public String bgButtons(){
        return "BkSys/pages/ui-features/buttons";
    }

    @RequestMapping("bgTypography")
    public String bgTypography(){
        return "BkSys/pages/ui-features/typography";
    }


    @RequestMapping("successInsert")
    public String successInsert(Map<String,Object> model){
        model.put("username","test");
        return "BkSys/pages/success/success-add-article";
    }


    @RequestMapping("exception")
    public String testException() throws BlogException {
        throw new BlogException("测试错误",500);
    }
}

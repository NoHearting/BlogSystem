package com.example.backgroundsystem.service.utils;

public class PageUtils {
    /**
     * 计算查询的起始位置
     * @param pageMaxItems  页面最大评论条数
     * @param currentPage   当前页面页码
     * @return
     */
    public static int calBeginItemIndex(int pageMaxItems,int currentPage){
        int begin = 0;
        if(currentPage < 1){
            return begin;
        }
        begin = pageMaxItems * (currentPage-1);
        return begin;
    }


    /**
     * 计算总页面条数
     * @param totalItems   总条目数
     * @param pageMaxItems  页面最大条目数
     * @return
     */
    public static int calTotalPages(int totalItems,int pageMaxItems){
        if(totalItems < pageMaxItems){
            return 1;
        }else if(totalItems % pageMaxItems==0){
            return totalItems/pageMaxItems;
        }else{
            return totalItems / pageMaxItems+1;
        }
    }
}

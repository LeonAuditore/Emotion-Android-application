package com.project.emotion.utils;

import com.project.emotion.entity.Story;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/3/17 22:13
 */
public class Utils {
    /**
     * 每日一句
     * @return
     */
    public static List<String> getStrList(){
        List<String> stringList = new ArrayList<>();
        stringList.add("世上只有一种英雄主义，就是在认清生活真相之后依然热爱生活");
        stringList.add("要想赢，就一定不能怕输。不怕输，结果未必能赢。但是怕输，结果则一定是输。");
        stringList.add("你要做的就是别人换不掉的，那你做不到怪谁，就是你自己没用！");
        stringList.add("含泪播种的人一定能含笑收获。");
        stringList.add("不要等待机会，而要创造机会。");
        stringList.add("没有伞的孩子，必须努力奔跑！");
        stringList.add("真正成功的人生，不在于成就的大小，而在于你是否努力地去实现自我，喊出自己的声音，走出属于自己的道路。");
        stringList.add("生活不是林黛玉，不会因为忧伤而风情万种。");
        stringList.add("勤奋可以弥补聪明的不足，但聪明无法弥补懒惰的缺陷。");
        stringList.add("人走到喧华的群众中去，是为了淹没他自己沉默的呼号。");
        return stringList;
    }

    /**
     * 故事大全
     * @return
     */
    public static List<Story> getStoryList(){
        List<Story> storyList = new ArrayList<>();
        storyList.add(new Story("生命的脊梁","https://gushi.1or9.com/chengbaigushi/5664.html"));
        storyList.add(new Story("奇特的选人方法","https://gushi.1or9.com/zhichanggushi/5280.html"));
        storyList.add(new Story("咬断后腿的狼","https://gushi.1or9.com/zheligushi/5663.html"));
        storyList.add(new Story("小时候听过的一个笑话","https://gushi.1or9.com/renshenggushi/5416.html"));
        storyList.add(new Story("狐狸的悲哀","https://gushi.1or9.com/zheligushi/5662.html"));
        storyList.add(new Story("看不见的爱","https://gushi.1or9.com/qinqinggushi/4461.html"));
        storyList.add(new Story("可悲的误会","https://gushi.1or9.com/youqinggushi/5661.html"));
        storyList.add(new Story("在取得之前，要先学会付出","https://gushi.1or9.com/zheligushi/5313.html"));
        storyList.add(new Story("误会","https://gushi.1or9.com/zheligushi/5660.html"));
        storyList.add(new Story("两个和尚","https://gushi.1or9.com/zheligushi/4789.html"));
        storyList.add(new Story("征服","https://gushi.1or9.com/renshenggushi/5659.html"));
        storyList.add(new Story("女孩和七百颗安定的故事","https://gushi.1or9.com/aiqinggushi/4634.html"));
        return storyList;
    }



    public static String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
}

package com.ccpc.edu.xidian.cn.ccpc.example.publish;

import com.ccpc.edu.xidian.cn.ccpc.annoations.NotRecommend;
import com.ccpc.edu.xidian.cn.ccpc.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape(){
        //还未构建完成就发布
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass(){
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}

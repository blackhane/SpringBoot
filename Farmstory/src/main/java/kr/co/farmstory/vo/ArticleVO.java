package kr.co.farmstory.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleVO {

    private int no;
    private int parent;
    private int comment;
    private String cate;
    private String title;
    private String content;
    private int file;
    private int hit;
    private String uid;
    private String regip;
    private String rdate;
    private String nick;
    private String fname;

    public String getRdate() {
        return rdate.substring(2,10);
    }
}

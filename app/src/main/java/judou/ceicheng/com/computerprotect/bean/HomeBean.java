package judou.ceicheng.com.computerprotect.bean;

import android.graphics.Bitmap;

/**
 * Created by sunbo on 2018/9/9.
 */

public class HomeBean {
    private String name;
    private String word;
    private int userpic;
    private  int pic;

    public HomeBean() {

    }

    public HomeBean(String name, String word, int userpic, int pic) {
        this.name = name;
        this.word = word;
        this.userpic = userpic;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getUserpic() {
        return userpic;
    }

    public void setUserpic(int userpic) {
        this.userpic = userpic;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }


}

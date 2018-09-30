package judou.ceicheng.com.computerprotect.bean;

import android.graphics.Bitmap;

/**
 * Created by sunbo on 2018/9/26.
 */

public class EventHomeBean {

    private String name;
    private String word;
    private int userpic;
    private Bitmap bitmap;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public EventHomeBean(String name,String word,String type,int userpic,Bitmap bitmap) {
        this.name = name;
        this.word = word;
        this.userpic = userpic;
        this.bitmap = bitmap;
        this.type = type;
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

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}

package judou.ceicheng.com.computerprotect.bean;

import java.util.List;

/**
 * @Class SearchSelectBean
 * @Author sunbo
 * @DATE 2018/8/15 19:47
 * @Explanatory
 */
public class SearchSelectBean {
    private  String TypeName;
    private List<Children> children;

    public String getTypeName() {
        return TypeName;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public static class Children{
        private  String value;
        private  Boolean isSelected=false;

        public void setValue(String value) {
            this.value = value;
        }

        public void setIsSelected(Boolean isSelected) {
            this.isSelected = isSelected;
        }

        public String getValue() {
            return value;
        }

        public Boolean getIsSelected() {
            return isSelected;
        }
    }


}

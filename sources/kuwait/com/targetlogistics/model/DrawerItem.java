package kuwait.com.targetlogistics.model;

public class DrawerItem {
    private DrawerEnum drawerEnum;
    private int imgRes;
    private String name;

    public DrawerItem(String name, int image, DrawerEnum drawerEnum) {
        this.name = name;
        this.imgRes = image;
        this.drawerEnum = drawerEnum;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgRes() {
        return this.imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public DrawerEnum getDrawerEnum() {
        return this.drawerEnum;
    }

    public void setDrawerEnum(DrawerEnum drawerEnum) {
        this.drawerEnum = drawerEnum;
    }
}

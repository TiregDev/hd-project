package tiregdev.hi_depok.model;

/**
 * Created by Muhammad63 on 10/6/2017.
 */

public class itemObject_avatar {

    private String nama;
    private int avatar;

    public itemObject_avatar(String nama, int avatar) {
        this.nama = nama;
        this.avatar = avatar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}

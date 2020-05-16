package hellojpa.entity;

public enum MemberType {
    ADMIN(0,"관리자"),
    USER(1,"유저");

    private int value;
    private String desc;

    private MemberType(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

}

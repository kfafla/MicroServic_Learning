package top.xmy.contentservice.page;

public class UserVO {
    private Integer id;

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    private Integer bonus;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    private String name;
    private String avatarUrl;

    public UserVO(Integer id, String name, String avatarUrl, Integer bonus) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.bonus = bonus;
    }
}

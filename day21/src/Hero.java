/*
 * @author F3ver1
 * @date 2018/12/4 18:30
 */
public class Hero {
    private int id;
    private String name;
    private String Local;
    private String sex;
    private int Birth;
    private int Death;
    private int power;

    public Hero(int id, String name, String local, String sex, int birth, int death, int power) {
        this.id = id;
        this.name = name;
        Local = local;
        this.sex = sex;
        Birth = birth;
        Death = death;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Local='" + Local + '\'' +
                ", sex='" + sex + '\'' +
                ", Birth=" + Birth +
                ", Death=" + Death +
                ", power=" + power +
                '}';
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getBirth() {
        return Birth;
    }

    public void setBirth(int birth) {
        Birth = birth;
    }

    public int getDeath() {
        return Death;
    }

    public void setDeath(int death) {
        Death = death;
    }
}

package com.example.testproject;

/**
 * Class to define individual combatants
 */
public class Combatant {

    // Class Variables
    private int id, m_ini, m_hp, m_hpMax, m_ac;
    private String m_name, m_cond, m_url;

    /**
     * Constructor to create a combatant, take the following fields:
     *
     * @param id - unique id (get from MyActivity)
     * @param ini - initiative score
     * @param hp - current hp
     * @param hpMax - max hp
     * @param name - combatant name
     * @param cond - currant conditions if any
     * @param url - image URL (or tag)
     * @param ac - current armour class
     */
    public Combatant(int id, int ini, int hp, int hpMax, String name, String cond, String url, int ac) {
        this.m_ini = ini;
        this.m_hp = hp;
        this.m_hpMax = hpMax;
        this.m_name = name;
        this.m_cond = cond;
        this.m_url = url;
        this.m_ac = ac;

        this.id = id;
    }

    //region Getter and Setter Methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getM_ini() {
        return m_ini;
    }

    public void setM_ini(int m_ini) {
        this.m_ini = m_ini;
    }

    public int getM_hp() {
        return m_hp;
    }

    public void setM_hp(int m_hp) {
        this.m_hp = m_hp;
    }

    public int getM_hpMax() {
        return m_hpMax;
    }

    public void setM_hpMax(int m_hpMax) {
        this.m_hpMax = m_hpMax;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_cond() {
        return m_cond;
    }

    public void setM_cond(String m_cond) {
        this.m_cond = m_cond;
    }

    public String getM_url() {
        return m_url;
    }

    public void setM_url(String m_url) {
        this.m_url = m_url;
    }

    public int getM_ac() {
        return m_ac;
    }

    public void setM_ac(int m_ac) {
        this.m_ac = m_ac;
    }

    //endregion


    /**
     *  returns a string containing all combatant traits
     */
    @Override
    public String toString() {
        return "Combatant{" +
                "id=" + id +
                ", m_ini=" + m_ini +
                ", m_hp=" + m_hp +
                ", m_hpMax=" + m_hpMax +
                ", m_name='" + m_name + '\'' +
                ", m_cond='" + m_cond + '\'' +
                ", m_url='" + m_url + '\'' +
                '}';
    }
}

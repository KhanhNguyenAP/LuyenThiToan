package com.thud.huynhnhu.luyenthitoan.utils.interfaces;

/**
 * Created by Le Nhu on 12/05/2016.
 */
public interface ActivityInterface {
    /**
     * Init all variable for Flags
     */
    public void initFlags();

    /**
     * Init all control of View
     */
    public void initControl();

    /**
     * set all event if have for control
     */
    public void setEventForControl();

    /**
     * get Data to show on the view
     */
    public void getData(String... params);

    /**
     * set Data for every control if have
     */
    public void setData();
}

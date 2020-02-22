package com.venada.efinance.common.util;

import java.io.Serializable;


/**
 * 〈OrderBean〉
 * 〈功能详细描述〉
 * @author    Administrator
 * @version   V1.00 2009-10-15[版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     TP V1.0R001 [产品/模块版本] 
 */
public class OrderBean implements Serializable
{
    private static final long serialVersionUID = 647958405335027261L;

    private String propertyName;

    private boolean ascending;

    /**
     *〈 〉
     * @return  
     */
    public OrderBean()
    {

    }

    /**
     *〈 〉
     *@param propertyName value
     *@param ascending value
     * @return 
     */
    protected OrderBean(String propertyName, boolean ascending)
    {
        this.propertyName = propertyName;
        this.ascending = ascending;
    }

    /**
     *〈〉
     *@param propertyName value
     * @return OrderBean
     */
    public static OrderBean asc(String propertyName)
    {
        return new OrderBean(propertyName, true);
    }

    /**
     *〈〉
     *@param propertyName value
     * @return OrderBean 
     */
    public static OrderBean desc(String propertyName)
    {
        return new OrderBean(propertyName, false);
    }

    public String getPropertyName()
    {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName)
    {
        this.propertyName = propertyName;
    }

    /**
     *〈〉
     * @return 
     */
    public void reverse()
    {
        this.ascending = !this.ascending;
    }

    public void setAscending(boolean ascending)
    {
        this.ascending = ascending;
    }

    public boolean isAscending()
    {
        return this.ascending;
    }

    /**
     *〈〉
     *@param other value
     * @return boolean
     */
    public boolean equals(Object other)
    {
        if (!(other instanceof OrderBean))
        {
            return false;
        }

        OrderBean order = (OrderBean)other;
        if (this.propertyName.equals(order.getPropertyName())
            && (this.ascending == order.isAscending()))
        {
            return true;
        }
        return false;
    }
    /**
     * hashCode
     * @return int hashcode
     */
    public int hashCode()
    {
        return this.propertyName.hashCode();
    }

    /**
     *〈〉
     * @return String
     */
    public String toString()
    {
        return propertyName + ' ' + (ascending ? "asc" : "desc");
    }

}

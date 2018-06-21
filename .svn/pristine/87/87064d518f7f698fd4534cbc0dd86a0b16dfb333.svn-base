package com.bt.edca.util.helper;


import java.util.ArrayList;
import java.util.List;

public enum OrderSearchType
{

  UN_ASSIGNED("Unassigned", "UA"), PENDING("Pending", "P"), AAF_PENDING("AAF Pending","AAFP"),AAF_DELEGATED("AAF Delegated","AAFD"),CANCELLED("Cancelled","C"),
  DELEGATED("Delegated","D"),ORDER_BY_TEAM("Order by Team","V"),SERVICE_ACTIVATION_SUMMARY("Service Activation Summary","SAS"),CLASSIC("Classic","UTC"),
  CLASSIC_BY_TEAM("Classic By Team","UTCT"),INVENTORY("Inventory","Inventory");

  private String displayValue;

  private String code;

  private OrderSearchType(String displayValue, String code)
  {
    this.displayValue = displayValue;
    this.code = code;
  }

  public String getDisplayValue()
  {
    return displayValue;
  }

  public void setDisplayValue(String displayValue)
  {
    this.displayValue = displayValue;
  }

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public static List<String> getDisplayValuesList()
  {
    List<String> displayValues = new ArrayList<String>();
    for (OrderSearchType orderSearchType : OrderSearchType.values())
    {
      displayValues.add(orderSearchType.getDisplayValue());
    }
    return displayValues;
  }

  public static String getSearchTypeByDisplayName(String displayName)
  {

    for (OrderSearchType orderSearchType : OrderSearchType.values())
    {
      if (orderSearchType.getDisplayValue().equalsIgnoreCase(displayName))
      {
        return orderSearchType.getCode();
      }

    }
    return null;
  }
  
}

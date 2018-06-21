package com.bt.edca.common.dto;

public class ConfigureVlanDTO {

  private String serviceInstanceId;

  private int vlanId;

  private String pafName;

  private String vlanType;

  private String lanswitch;

  private String manufacturer;

  private String aEndSite;

  private String zEndSite;

  private String bandWidth;

  private String broadCast;

  private String aEndVlanId;

  private String zEndVlanId;

  private String osiLayer;

  // private String vlanType;

  private String providerName;

  private String memberName;

  private String vlanIdentifier;

  private String customerHandOffMode;

  private String lblError;

  private String sanName;

  private String sanValue;

  int sanId;

  public int getSanId() {
    return sanId;
  }

  public void setSanId(int sanId) {
    this.sanId = sanId;
  }

  public String getSanValue() {
    return sanValue;
  }

  public void setSanValue(String sanValue) {
    this.sanValue = sanValue;
  }

  public String getSanName() {
    return sanName;
  }

  public void setSanName(String sanName) {
    this.sanName = sanName;
  }

  public String getServiceInstanceId() {
    return serviceInstanceId;
  }

  public void setServiceInstanceId(String serviceInstanceId) {
    this.serviceInstanceId = serviceInstanceId;
  }

  public int getVlanId() {
    return vlanId;
  }

  public void setVlanId(int vlanId) {
    this.vlanId = vlanId;
  }

  public String getPafName() {
    return pafName;
  }

  public void setPafName(String pafName) {
    this.pafName = pafName;
  }

  public String getVlanType() {
    return vlanType;
  }

  public void setVlanType(String vlanType) {
    this.vlanType = vlanType;
  }

  public String getLanswitch() {
    return lanswitch;
  }

  public void setLanswitch(String lanswitch) {
    this.lanswitch = lanswitch;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getaEndSite() {
    return aEndSite;
  }

  public void setaEndSite(String aEndSite) {
    this.aEndSite = aEndSite;
  }

  public String getzEndSite() {
    return zEndSite;
  }

  public void setzEndSite(String zEndSite) {
    this.zEndSite = zEndSite;
  }

  public String getBandWidth() {
    return bandWidth;
  }

  public void setBandWidth(String bandWidth) {
    this.bandWidth = bandWidth;
  }

  public String getBroadCast() {
    return broadCast;
  }

  public void setBroadCast(String broadCast) {
    this.broadCast = broadCast;
  }

  public String getaEndVlanId() {
    return aEndVlanId;
  }

  public void setaEndVlanId(String aEndVlanId) {
    this.aEndVlanId = aEndVlanId;
  }

  public String getzEndVlanId() {
    return zEndVlanId;
  }

  public void setzEndVlanId(String zEndVlanId) {
    this.zEndVlanId = zEndVlanId;
  }

  public String getOsiLayer() {
    return osiLayer;
  }

  public void setOsiLayer(String osiLayer) {
    this.osiLayer = osiLayer;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  public String getVlanIdentifier() {
    return vlanIdentifier;
  }

  public void setVlanIdentifier(String vlanIdentifier) {
    this.vlanIdentifier = vlanIdentifier;
  }

  public String getCustomerHandOffMode() {
    return customerHandOffMode;
  }

  public void setCustomerHandOffMode(String customerHandOffMode) {
    this.customerHandOffMode = customerHandOffMode;
  }

  public String getLblError() {
    return lblError;
  }

  public void setLblError(String lblError) {
    this.lblError = lblError;
  }

}

package com.bt.edca.da.domain;

import java.util.Date;

/**
 * Description : Class used to Declare variables.
 *
 * @author Niranjan das Basa.
 *
 */
/**
 * @author SATHYA.
 *
 */
public class SitePageDomain {
	
	/**
	 * Declaration of secureOverride.
	 */
	
	private String secureOverride;

	/**
	 * Declaration of fiberConnectivity.
	 */

	private String fiberConnectivity;
	/**
	 * Declaration of coreDiversity.
	 */
	private String coreDiversity;

	/**
	 * Declaration of siteId.
	 */
	private long siteId;

	/**
	 * Declaration of dcaReferenceId.
	 */
	private String dcaReferenceId;

	/**
	 * Declaration of resilience.
	 */
	private String resilience;

	/**
	 * Declaration of pricing.
	 */
	private String pricing;

	/**
	 * Declaration of crd.
	 */
	private Date crd;

	/**
	 * Declaration of highestSiteSla.
	 */
	private String highestSiteSla;

	/**
	 * Declaration of specialBidReference.
	 */
	private String specialBidReference;

	/**
	 * Declaration of structureCabling.
	 */
	private String structureCabling;

	/**
	 * Declaration of zone.
	 */
	private String zone;

	/**
	 * Declaration of accessOption.
	 */
	private String accessOption;

	/**
	 * Declaration of nativeultraset.
	 */
	private String nativeultraset;

	/**
	 * Declaration of onvDemarcOption.
	 */
	private String onvDemarcOption;

	/**
	 * Declaration of orderId.
	 */
	private int orderId;

	/**
	 * Declaration of override.
	 */
	private boolean override;

	private String lastMileAccess;

	private String accessOptionFlag;

	private String serviceInstance;
	
	
	

	/**
	 * @return the secureOverride
	 */
	public String getSecureOverride() {
		return secureOverride;
	}

	/**
	 * @param secureOverride the secureOverride to set
	 */
	public void setSecureOverride(String secureOverride) {
		this.secureOverride = secureOverride;
	}

	/**
	 * @return the serviceInstance
	 */
	public String getServiceInstance() {
		return serviceInstance;
	}

	/**
	 * @param serviceInstance
	 *            the serviceInstance to set
	 */
	public void setServiceInstance(String serviceInstance) {
		this.serviceInstance = serviceInstance;
	}

	/**
	 * @return override.
	 */
	public boolean isOverride() {
		return override;
	}

	/**
	 * @param override.
	 */
	public void setOverride(boolean override) {
		this.override = override;
	}

	/**
	 * Gets the dcaReferenceId.
	 *
	 * @return dcaReferenceId
	 */
	public String getDcaReferenceId() {
		return dcaReferenceId;
	}

	/**
	 * @param dcaReferenceId.
	 */
	public void setDcaReferenceId(String dcaReferenceId) {
		this.dcaReferenceId = dcaReferenceId;
	}

	/**
	 * Gets the specialBidReference.
	 *
	 * @return specialBidReference
	 */
	public String getSpecialBidReference() {
		return specialBidReference;
	}

	/**
	 * @param specialBidReference.
	 */
	public void setSpecialBidReference(String specialBidReference) {
		this.specialBidReference = specialBidReference;
	}

	/**
	 * Gets the siteId.
	 *
	 * @return siteId
	 */
	public long getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId.
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}

	/**
	 * Gets the resilience.
	 *
	 * @return resilience
	 */
	public String getResilience() {
		return resilience;
	}

	/**
	 * @param resilience.
	 */
	public void setResilience(String resilience) {
		this.resilience = resilience;
	}

	/**
	 * Gets the pricing.
	 *
	 * @return pricing
	 */
	public String getPricing() {
		return pricing;
	}

	/**
	 * @param pricing.
	 */
	public void setPricing(String pricing) {
		this.pricing = pricing;
	}

	/**
	 * Gets the crd.
	 *
	 * @return crd
	 */
	public Date getCrd() {
		return crd;
	}

	/**
	 * @param crd.
	 */
	public void setCrd(Date crd) {
		this.crd = this.crd = crd != null ? new Date(crd.getTime()) : null;
	}

	/**
	 * Gets the highestSiteSla.
	 *
	 * @return highestSiteSla
	 */
	public String getHighestSiteSla() {
		return highestSiteSla;
	}

	/**
	 * @param highestSiteSla.
	 */
	public void setHighestSiteSla(String highestSiteSla) {
		this.highestSiteSla = highestSiteSla;
	}

	/**
	 * Gets the structureCabling.
	 *
	 * @return structureCabling
	 */
	public String getStructureCabling() {
		return structureCabling;
	}

	/**
	 * @param structureCabling.
	 */
	public void setStructureCabling(String structureCabling) {
		this.structureCabling = structureCabling;
	}

	/**
	 * Gets the zone.
	 *
	 * @return zone
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * @param zone.
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * Gets the accessOption.
	 *
	 * @return accessOption
	 */
	public String getAccessOption() {
		return accessOption;
	}

	/**
	 * @param accessOption.
	 */
	public void setAccessOption(String accessOption) {
		this.accessOption = accessOption;
	}

	/**
	 * Gets the nativeultraset.
	 *
	 * @return nativeultraset
	 */
	public String getNativeultraset() {
		return nativeultraset;
	}

	/**
	 * @param nativeultraset.
	 */
	public void setNativeultraset(String nativeultraset) {
		this.nativeultraset = nativeultraset;
	}

	/**
	 * Gets the onvDemarcOption.
	 *
	 * @return onvDemarcOption
	 */
	public String getOnvDemarcOption() {
		return onvDemarcOption;
	}

	/**
	 * @param onvDemarcOption.
	 */
	public void setOnvDemarcOption(String onvDemarcOption) {
		this.onvDemarcOption = onvDemarcOption;
	}

	/**
	 * Gets the orderId.
	 *
	 * @return orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId.
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getLastMileAccess() {
		return lastMileAccess;
	}

	public void setLastMileAccess(String lastMileAccess) {
		this.lastMileAccess = lastMileAccess;
	}

	public String getAccessOptionFlag() {
		return accessOptionFlag;
	}

	public void setAccessOptionFlag(String accessOptionFlag) {
		this.accessOptionFlag = accessOptionFlag;
	}

	/**
	 * @return the fiberConnectivity
	 */
	public String getFiberConnectivity() {
		return fiberConnectivity;
	}

	/**
	 * @param fiberConnectivity
	 *            the fiberConnectivity to set
	 */
	public void setFiberConnectivity(String fiberConnectivity) {
		this.fiberConnectivity = fiberConnectivity;
	}

	/**
	 * @return the coreDiversity
	 */
	public String getCoreDiversity() {
		return coreDiversity;
	}

	/**
	 * @param coreDiversity
	 *            the coreDiversity to set
	 */
	public void setCoreDiversity(String coreDiversity) {
		this.coreDiversity = coreDiversity;
	}

	/**
	 * Converting into String and displaying values in console.
	 */
	@Override

	public String toString() {

		final StringBuilder sb = new StringBuilder();

		sb.append("SitePageDomain = {");

		sb.append("resilience = ").append(resilience).append(",");

		sb.append("pricing = ").append(pricing).append(",");

		sb.append("crd = ").append(crd).append(",");

		sb.append("orderId = ").append(orderId).append(",");

		sb.append("highestSiteSla = ").append(highestSiteSla).append(",");

		sb.append("structureCabling = ").append(structureCabling).append(",");

		sb.append("zone = ").append(zone).append(",");

		sb.append("accessOption = ").append(accessOption).append(",");

		sb.append("nativeultraset = ").append(nativeultraset).append(",");

		sb.append("onvDemarcOption = ").append(onvDemarcOption).append(",");

		sb.append("specialBidReference = ").append(specialBidReference);

		sb.append("}");

		return sb.toString();

	}
}

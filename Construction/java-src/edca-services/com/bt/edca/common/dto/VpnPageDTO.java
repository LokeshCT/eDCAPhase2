package com.bt.edca.common.dto;

/**
 * The Class VpnPageDTO.
 */
public class VpnPageDTO {

	/** The vpn id. */
	private long vpnId;

	/** The connection name. */
	private String connectionName;

	/** The connection type. */
	private String connectionType;

	/** The connection band width. */
	private long connectionBandWidth;

	/** The resilience. */
	private String resilience;

	/** The mcast_flag. */
	private String mcast_flag;

	/**
	 * Gets the vpn id.
	 * 
	 * @return the vpn id
	 */
	public long getVpnId() {
		return vpnId;
	}

	/**
	 * Sets the vpn id.
	 * 
	 * @param vpnId
	 *            the new vpn id
	 */
	public void setVpnId(long vpnId) {
		this.vpnId = vpnId;
	}

	/**
	 * Gets the connection name.
	 * 
	 * @return the connection name
	 */
	public String getConnectionName() {
		return connectionName;
	}

	/**
	 * Sets the connection name.
	 * 
	 * @param connectionName
	 *            the new connection name
	 */
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	/**
	 * Gets the connection type.
	 * 
	 * @return the connection type
	 */
	public String getConnectionType() {
		return connectionType;
	}

	/**
	 * Sets the connection type.
	 * 
	 * @param connectionType
	 *            the new connection type
	 */
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	/**
	 * Gets the connection band width.
	 * 
	 * @return the connection band width
	 */
	public long getConnectionBandWidth() {
		return connectionBandWidth;
	}

	/**
	 * Sets the connection band width.
	 * 
	 * @param connectionBandWidth
	 *            the new connection band width
	 */
	public void setConnectionBandWidth(long connectionBandWidth) {
		this.connectionBandWidth = connectionBandWidth;
	}

	/**
	 * Gets the resilience.
	 * 
	 * @return the resilience
	 */
	public String getResilience() {
		return resilience;
	}

	/**
	 * Sets the resilience.
	 * 
	 * @param resilience
	 *            the new resilience
	 */
	public void setResilience(String resilience) {
		this.resilience = resilience;
	}

	/**
	 * Gets the mcast_flag.
	 * 
	 * @return the mcast_flag
	 */
	public String getMcast_flag() {
		return mcast_flag;
	}

	/**
	 * Sets the mcast_flag.
	 * 
	 * @param mcast_flag
	 *            the new mcast_flag
	 */
	public void setMcast_flag(String mcast_flag) {
		this.mcast_flag = mcast_flag;
	}

}

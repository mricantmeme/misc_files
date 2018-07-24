
package com.dicv.truck.dto;

/* 
 * @author Ramakrishna
 *
 */
public class VehicleCanParamDto {

	private Integer canEngineSpeed;

	private Long canCoolantTemp;

	private Long engFuelRate;

	private Long engInstFuelEco;

	private Long engExhBrkLamp;

	private Integer fuelTankLevel;

	private Integer batteryHealth;
	
	private Double adblueLevel;

	public Integer getCanEngineSpeed() {
		return canEngineSpeed;
	}

	public void setCanEngineSpeed(Integer canEngineSpeed) {
		this.canEngineSpeed = canEngineSpeed;
	}

	public Long getCanCoolantTemp() {
		return canCoolantTemp;
	}

	public void setCanCoolantTemp(Long canCoolantTemp) {
		this.canCoolantTemp = canCoolantTemp;
	}

	public Long getEngFuelRate() {
		return engFuelRate;
	}

	public void setEngFuelRate(Long engFuelRate) {
		this.engFuelRate = engFuelRate;
	}

	public Long getEngInstFuelEco() {
		return engInstFuelEco;
	}

	public void setEngInstFuelEco(Long engInstFuelEco) {
		this.engInstFuelEco = engInstFuelEco;
	}

	public Long getEngExhBrkLamp() {
		return engExhBrkLamp;
	}

	public void setEngExhBrkLamp(Long engExhBrkLamp) {
		this.engExhBrkLamp = engExhBrkLamp;
	}

	public Integer getFuelTankLevel() {
		return fuelTankLevel;
	}

	public void setFuelTankLevel(Integer fuelTankLevel) {
		this.fuelTankLevel = fuelTankLevel;
	}

	public Integer getBatteryHealth() {
		return batteryHealth;
	}

	public void setBatteryHealth(Integer batteryHealth) {
		this.batteryHealth = batteryHealth;
	}

	public Double getAdblueLevel() {
		return adblueLevel;
	}

	public void setAdblueLevel(Double adblueLevel) {
		this.adblueLevel = adblueLevel;
	}

}

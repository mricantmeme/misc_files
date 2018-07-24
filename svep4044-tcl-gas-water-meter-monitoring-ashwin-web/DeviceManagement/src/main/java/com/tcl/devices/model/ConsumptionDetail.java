package com.tcl.devices.model;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Document(collection = "consumption_details")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ConsumptionDetail {
	
	private static final long serialVersionUID = -4240718002259642160L;

	@Id
	@Field("consumption_id")
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String consumptionId;

	@Field("customer_name")
	private String customerName;
	
	@Field("zone_name")
	private String zoneName;
	
	
	@Field("location_name")
	private String locationName;
	
	
	@Field("property_id")
	private String propertyId;
	
	
	@Field("consumer_name")
	private String consumerName;
	
	
	@Field("consumption_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date consumptionDate;
	
	@Field("op_balance")
	private String opBalance;

	@Field("lora_data")
	private String loraData;
	
	@Field("col_balance")
	private String colBalance;
	
	@Field("manual_adjustment")
	private String manualAdjustment;
	
	
	@Field("consumption")
	private String consumption;

	
	@Field("consumption_status")
	private Boolean consumptionStatus;
	
	@Field("is_active")
	private Boolean isActive;

	
	@Field("created_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate;

	@Field("modified_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date modifiedDate;

	@Field("created_by")
	private String createdBy;

	@Field("modified_by")
	private String modifiedBy;
}

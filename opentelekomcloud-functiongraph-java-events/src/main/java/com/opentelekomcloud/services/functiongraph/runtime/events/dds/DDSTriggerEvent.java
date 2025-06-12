package com.opentelekomcloud.services.functiongraph.runtime.events.dds;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(includeFieldNames=true)
public class DDSTriggerEvent implements TriggerEvent {

  @SerializedName("records")
  private ArrayList<DDSRecord> records;

}

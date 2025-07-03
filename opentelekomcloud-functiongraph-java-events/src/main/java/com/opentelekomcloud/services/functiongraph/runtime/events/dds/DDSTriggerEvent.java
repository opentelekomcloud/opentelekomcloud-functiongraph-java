package com.opentelekomcloud.services.functiongraph.runtime.events.dds;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class DDSTriggerEvent implements TriggerEvent {

  @SerializedName("records")
  private ArrayList<DDSRecord> records;

}

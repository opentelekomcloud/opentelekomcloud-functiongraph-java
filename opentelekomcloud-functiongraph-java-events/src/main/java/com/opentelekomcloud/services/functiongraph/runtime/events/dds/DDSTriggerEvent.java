package com.opentelekomcloud.services.functiongraph.runtime.events.dds;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(includeFieldNames=true)
public class DDSTriggerEvent {

  @SerializedName("records")
  private ArrayList<DDSRecord> records;

}

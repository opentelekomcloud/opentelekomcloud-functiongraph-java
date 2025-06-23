package com.opentelekomcloud.samples;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class EventBody {

  @SerializedName("name")
  private String name;
  
}
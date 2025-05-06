package com.opentelekomcloud.services.functiongraph.runtime.events.dms4kafka;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class DMS4KafkaRecord {

  /**
   * Kafka messages
   */
  private String[] messages;

  /**
   * topic id
   */
  @SerializedName("topic_id")
  private String topicId;

  /**
   * clone of kafka messages
   * @return messages.
   */
  public String[] getMessages() {
    return (String[]) this.messages.clone();
  }
  
  public void setMessages(String[] messages) {
    this.messages = (String[]) messages.clone();
  }

}

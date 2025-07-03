package com.opentelekomcloud.services.functiongraph.runtime.events.smn;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class SMNBody {

  /**
   * Topic URN
   */
  @SerializedName("topic_urn")
  private String topicUrn;

  /**
   * Timestamp
   */
  @SerializedName("timestamp")
  private String timestamp;

  /**
   * Message attributes
   */
  @SerializedName("message_attributes")
  private Map<String, String> messageAttributes;

  /**
   * SMN Message
   */
  @SerializedName("message")
  private String message;

  /**
   * SMN Type
   */
  @SerializedName("type")
  private String type;

  /**
   * SMN message id
   */
  @SerializedName("message_id")
  private String messageId;

  /**
   * MSN subject
   */
  @SerializedName("subject")
  private String subject;
 
}

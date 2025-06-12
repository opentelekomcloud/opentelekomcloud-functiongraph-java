package com.opentelekomcloud.services.functiongraph.runtime.events.s3obs;

import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

public interface IObsTriggerInfo extends TriggerEvent {
  /**
   * 
   * @return bucket name
   */
  String getBucketName();

  /**
   * 
   * @return object key
   */
  String getObjectKey();

  /**
   * Possible values:
   * <ul>
   * <li>ObjectCreated:Put</li>
   * <li>ObjectCreated:Post</li>
   * <li>ObjectCreated:Put</li>
   * <li>ObjectCreated:Copy</li>
   * <li>Created:CompleteMultipartUpload</li>
   * <li>ObjectRemoved:Delete</li>
   * <li>ObjectRemoved:DeleteMarkerCreated</li>
   * </ul>
   * 
   * @return event name
   */
  String getEventName();
}

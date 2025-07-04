/*
 * Copyright (c) 2025 T-Systems International GmbH.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

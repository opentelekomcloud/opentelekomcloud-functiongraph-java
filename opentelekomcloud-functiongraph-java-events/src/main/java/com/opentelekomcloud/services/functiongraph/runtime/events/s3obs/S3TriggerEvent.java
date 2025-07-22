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

import java.util.Optional;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * S3TriggerEvent is used to represent the event triggered by S3/OBS.
 * It contains an array of S3TriggerEventRecord.
 */
@NoArgsConstructor
@Data
@ToString(includeFieldNames = true)
/*
 * Trigger on OBS Events
 * <p>
 * Hint: OBS events can only trigger FunctionGraphs in main project,
 * not in sub projects.
 */
public class S3TriggerEvent {

  /**
   * Records of the S3 trigger event
   */
  @SerializedName("Records")
  private S3TriggerEventRecord[] records;

  /**
   * Constructor for S3TriggerEvent
   *
   * @param records S3TriggerEventRecord array
   */
  public S3TriggerEvent(S3TriggerEventRecord[] records) {
    this.records = records;
  }

  /**
   * Check if records are not null and have at least one record.
   * If not, throw IllegalArgumentException.
   *
   * @return Optional of S3TriggerEventRecord array
   */
  private Optional<S3TriggerEventRecord[]> check() {
    if (this.records != null && this.records.length >= 1) {
      if (this.records.length > 1) {
        throw new IllegalArgumentException("Record's length is to long! ");
      } else {
        return Optional.of(this.records);
      }
    } else {
      throw new IllegalArgumentException("Record can't be null. ");
    }
  }

  /**
   * @return bucket name
   */
  public String getBucketName() {
    Optional<S3TriggerEventRecord[]> s3Records = this.check();
    return s3Records.map((r) -> {
      return r[0].getS3().getBucket().getName();
    }).orElseThrow(IllegalArgumentException::new);
  }

  /**
   * @return object key
   */
  public String getObjectKey() {
    Optional<S3TriggerEventRecord[]> obsRecords = this.check();
    return obsRecords.map((r) -> {
      return r[0].getS3().getObject().getKey();
    }).orElseThrow(IllegalArgumentException::new);
  }

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
  public String getEventName() {
    Optional<S3TriggerEventRecord[]> s3Records = this.check();
    return s3Records.map((r) -> {
      return r[0].getEventName();
    }).orElseThrow(IllegalArgumentException::new);
  }

}

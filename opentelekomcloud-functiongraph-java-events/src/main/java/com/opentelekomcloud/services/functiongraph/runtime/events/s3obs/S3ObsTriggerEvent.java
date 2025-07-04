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

@NoArgsConstructor
@Data
@ToString(includeFieldNames=true)
/**
 * Trigger on OBS Events
 * 
 * Hint: OBS events can only trigger FunctionGraphs in main project, 
 * not in sub projects.
 */
public class S3ObsTriggerEvent implements IObsTriggerInfo {

  /**
   * Records
   */
  @SerializedName("Records")
  private S3Record[] record;

  public S3ObsTriggerEvent(S3Record[] record) {
    this.record = record;
  }

  private Optional<S3Record[]> check() {
    if (this.record != null && this.record.length >= 1) {
      if (this.record.length > 1) {
        throw new IllegalArgumentException("Record's length is to long! ");
      } else {
        return Optional.of(this.record);
      }
    } else {
      throw new IllegalArgumentException("Record can't be null. ");
    }
  }

  public String getBucketName() {
    Optional<S3Record[]> s3Record = this.check();
    return (String) s3Record.map((r) -> {
      return r[0].getS3obs().getBucket().getName();
    }).orElseThrow(IllegalArgumentException::new);
  }

  public String getObjectKey() {
    Optional<S3Record[]> obsRecord = this.check();
    return (String) obsRecord.map((r) -> {
      return r[0].getS3obs().getObsobject().getKey();
    }).orElseThrow(IllegalArgumentException::new);
  }

  public String getEventName() {
    Optional<S3Record[]> s3Record = this.check();
    return (String) s3Record.map((r) -> {
      return r[0].getEventName();
    }).orElseThrow(IllegalArgumentException::new);
  }
 
}

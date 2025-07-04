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
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(includeFieldNames=true)
public class S3Object {

   /**
   * size
   */
  @SerializedName("size")
  private int size;

  /**
   * key
   */
  @SerializedName("key")
  private String key;

  /**
   * etag
   */
  @SerializedName("eTag")
  private String eTag;

  /**
   * version id
   */
  @SerializedName("versionId")
  private String versionId;

  /**
   * sequencer (e.g. "00000000193BF923E8B8A86120000000")
   */
  @SerializedName("sequencer")
  private String sequencer;

  public S3Object(int size, String key, String eTag, String versionId, String sequencer) {
    this.size = size;
    this.key = key;
    this.eTag = eTag;
    this.versionId = versionId;
    this.sequencer = sequencer;
  }
 
}
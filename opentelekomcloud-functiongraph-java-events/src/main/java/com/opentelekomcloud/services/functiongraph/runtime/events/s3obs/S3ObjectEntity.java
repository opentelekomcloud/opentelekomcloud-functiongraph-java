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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * S3ObjectEntity is used to represent the object entity in S3/OBS events.
 * It contains information such as size, key, eTag, version ID, and sequencer.
 */
@Data
@NoArgsConstructor
@ToString(includeFieldNames = true)
public class S3ObjectEntity {

  private static final String DEFAULT_ENCODING = "UTF-8";
  /**
   * size
   */
  @SerializedName("size")
  private long size;

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

    
  public S3ObjectEntity(long size, String key, String eTag, String versionId, String sequencer) {
    this.size = size;
    this.key = key;
    this.eTag = eTag;
    this.versionId = versionId;
    this.sequencer = sequencer;
  }

  /**
   * S3 URL encodes the key of the object involved in the event. This is
   * a convenience method to automatically URL decode the key.
   * 
   * @return The URL decoded object key.
   */
  public String getUrlDecodedKey() {
    return urlDecode(getKey());
  }

  /**
   * Decode a string for use in the path of a URL; uses URLDecoder.decode,
   * which decodes a string for use in the query portion of a URL.
   *
   * @param value The value to decode
   * @return The decoded value if parameter is not null, otherwise, null is
   *         returned.
   */
  private static String urlDecode(final String value) {
    if (value == null) {
      return null;
    }

    try {
      return URLDecoder.decode(value, DEFAULT_ENCODING);
    } catch (UnsupportedEncodingException ex) {
      throw new RuntimeException(ex);
    }
  }
}
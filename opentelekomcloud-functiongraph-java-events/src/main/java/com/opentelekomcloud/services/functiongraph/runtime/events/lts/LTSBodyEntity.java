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

package com.opentelekomcloud.services.functiongraph.runtime.events.lts;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * LTSBodyEntity is used to represent the body entity in Log Tank Service (LTS).
 * It contains the LTS data and provides a method to get the raw data after Base64 decoding.
 */
@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class LTSBodyEntity {

  /**
   * LTS data
   */
  @SerializedName("data")
  private String data;

  /**
   * 
   * @return get base64 decoded raw data
   */
  public String getRawData() throws UnsupportedEncodingException {
    byte[] decoded = Base64.getMimeDecoder().decode(this.data);
    return new String(decoded, StandardCharsets.UTF_8);
  }

}

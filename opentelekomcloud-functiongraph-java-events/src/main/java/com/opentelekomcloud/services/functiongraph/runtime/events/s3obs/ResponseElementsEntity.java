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

/**
 * ResponseElementsEntity is used to represent the response elements entity in S3/OBS events.
 * It contains information such as x-amz-id-2 and x-amz-request-id.
 */
@Data
@NoArgsConstructor
@ToString(includeFieldNames = true)
public class ResponseElementsEntity {

  @SerializedName("x-amz-id-2")
  private String xAmzId2;

  @SerializedName("x-amz-request-id")
  private String xAmzRequestId;

  public ResponseElementsEntity(String xAmzId2, String xAmzRequestId) {
    this.xAmzId2 = xAmzId2;
    this.xAmzRequestId = xAmzRequestId;
  }

}
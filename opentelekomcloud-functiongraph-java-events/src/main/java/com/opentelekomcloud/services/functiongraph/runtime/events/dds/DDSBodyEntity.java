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

package com.opentelekomcloud.services.functiongraph.runtime.events.dds;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DDSBodyEntity is used to represent the body entity in Data Distributed Service (DDS).
 * It contains information such as size in bytes, token, full document, and namespace.
 */
@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class DDSBodyEntity {

  @SerializedName("size_bytes")
  private String sizeBytes;

  @SerializedName("token")
  private String token;

  @SerializedName("full_document")
  private String fullDocument;

  @SerializedName("ns")
  private String ns;

  
}

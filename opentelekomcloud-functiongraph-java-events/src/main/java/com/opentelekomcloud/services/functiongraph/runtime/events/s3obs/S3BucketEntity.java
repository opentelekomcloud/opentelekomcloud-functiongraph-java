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
 * S3BucketEntity is used to represent the bucket entity in S3/OBS events.
 * It contains information such as bucket name, ARN, and owner identity.
 */
@NoArgsConstructor
@Data
@ToString(includeFieldNames=true)
public class S3BucketEntity {
  /**
   * Bucket name
   */
  @SerializedName("name")
  private String name;

  /**
   * ARN
   */
  @SerializedName("arn")
  private String arn;

  /**
   * Owner Identity
   */
  @SerializedName("ownerIdentity")
  private UserIdentityEntity ownerIdentity;

  public S3BucketEntity(String name, UserIdentityEntity ownerIdentity, String arn) {
    this.name = name;
    this.arn = arn;
    this.ownerIdentity = ownerIdentity;
  }

}

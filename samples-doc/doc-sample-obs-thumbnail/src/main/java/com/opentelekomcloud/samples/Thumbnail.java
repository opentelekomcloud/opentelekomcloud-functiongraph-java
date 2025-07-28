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
package com.opentelekomcloud.samples;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.obs.services.ObsClient;
import com.obs.services.model.ObjectMetadata;
import com.obs.services.model.PutObjectResult;
import com.opentelekomcloud.services.functiongraph.runtime.events.s3obs.S3TriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.events.s3obs.S3TriggerEventRecord;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

/**
 * Thumbnail is a sample function that processes an image from OBS,
 * resizes it to fit within a maximum dimension, and uploads the resized
 * image back to OBS.
 * It supports JPEG and PNG formats.
 */
public class Thumbnail {

  private final String OBS_ENDPOINT = "https://obs.otc.t-systems.com";

  private static final float MAX_DIMENSION = 100;
  private final String REGEX = ".*\\.([^\\.]*)";
  private final String JPG_TYPE = "jpg";
  private final String JPG_MIME = "image/jpeg";
  private final String PNG_TYPE = "png";
  private final String PNG_MIME = "image/png";

  public String handleRequest(final S3TriggerEvent event, final Context context) {

    RuntimeLogger log = context.getLogger();

    try {
      // Log the event details
      log.log(String.format("Received OBS event: %s", event));

      S3TriggerEventRecord[] records = event.getRecords();

      S3TriggerEventRecord record = records[0];

      String srcBucket = record.getS3().getBucket().getName();

      String srcKey = record.getS3().getObject().getUrlDecodedKey();
      
      log.log("Processing file: " + srcBucket + "/" + srcKey);
      
      String dstBucket = context.getUserData("OUTPUT_BUCKET");
      String dstKey = "resized-" + srcKey;

      // Infer the image type.
      Matcher matcher = Pattern.compile(REGEX).matcher(srcKey);
      if (!matcher.matches()) {
        log.log("Unable to infer image type for key " + srcKey);
        return "";
      }

      // Check if the image type is supported (jpg/png)
      String imageType = matcher.group(1);
      if (!(JPG_TYPE.equals(imageType)) && !(PNG_TYPE.equals(imageType))) {
        log.log("Skipping non-image " + srcKey);
        return "";
      }

      // Create an OBS client with the credentials from the context
      String ak = context.getSecurityAccessKey();
      String sk = context.getSecuritySecretKey();
      String stoken = context.getSecurityToken();
      final ObsClient obsClient = new ObsClient(ak, sk, stoken, OBS_ENDPOINT);

      // Download the source image from OBS
      // https://support.huaweicloud.com/intl/en-us/sdk-java-devg-obs/obs_21_0702.html
      InputStream s3Object = obsClient.getObject(srcBucket, srcKey).getObjectContent();

      // Read the source image and resize it
      BufferedImage srcImage = ImageIO.read(s3Object);
      BufferedImage newImage = resizeImage(srcImage);

      // Re-encode image to target format
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      ImageIO.write(newImage, imageType, outputStream);

      // Upload new image to S3
      putObject(obsClient, outputStream, dstBucket, dstKey, imageType, log);

      log.log("Successfully resized " + srcBucket + "/"
          + srcKey + " and uploaded to " + dstBucket + "/" + dstKey);
      return "Ok";

    } catch (Exception e) {
      log.error("Error processing OBS event: " + e.getMessage());
      return "Error processing OBS event";
    }

  }

  /**
   * Uploads the resized image to OBS.
   * 
   * @param obsClient    The OBS client to use for uploading.
   * @param outputStream The output stream containing the resized image.
   * @param bucket       The target bucket name.
   * @param key          The target object key.
   * @param imageType    The type of the image (jpg/png).
   * @param logger       The logger to log messages.
   */
  private void putObject(ObsClient obsClient, ByteArrayOutputStream outputStream,
      String bucket, String key, String imageType, RuntimeLogger logger) {

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength((long) outputStream.size());

    if (JPG_TYPE.equals(imageType)) {
      metadata.setContentType(JPG_MIME);
    } else if (PNG_TYPE.equals(imageType)) {
      metadata.setContentType(PNG_MIME);
    }

    logger.log("Writing to: " + bucket + "/" + key);

    // https://support.huaweicloud.com/intl/en-us/sdk-java-devg-obs/obs_21_0602.html
    PutObjectResult  putObjectResult =
    obsClient.putObject(bucket, key, new ByteArrayInputStream(outputStream.toByteArray()), metadata);

    if (putObjectResult.getStatusCode() != 200) {
      logger.error("Failed to upload resized image to OBS, stauscode.: " + putObjectResult.getStatusCode());
      throw new RuntimeException("Failed to upload resized image to OBS: " + putObjectResult.getStatusCode());
    } 
    
  }

  /**
   * Resizes the source image to fit within the maximum dimension while
   * maintaining
   * aspect ratio.
   * The scaling factor is determined based on the value of
   * MAX_DIMENSION. The resulting new image has max(height, width)
   * = MAX_DIMENSION.
   * 
   * @param srcImage The source image to resize.
   * @return A resized BufferedImage.
   */
  private BufferedImage resizeImage(BufferedImage srcImage) {
    int srcHeight = srcImage.getHeight();
    int srcWidth = srcImage.getWidth();

    // Infer scaling factor to avoid stretching image unnaturally
    float scalingFactor = Math.min(
        MAX_DIMENSION / srcWidth, MAX_DIMENSION / srcHeight);
    int width = (int) (scalingFactor * srcWidth);
    int height = (int) (scalingFactor * srcHeight);

    BufferedImage resizedImage = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = resizedImage.createGraphics();
    
    // Fill with white before applying semi-transparent (alpha) images
    graphics.setPaint(Color.white);
    graphics.fillRect(0, 0, width, height);

    // Simple bilinear resize
    graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    graphics.drawImage(srcImage, 0, 0, width, height, null);
    graphics.dispose();
    return resizedImage;
  }

}

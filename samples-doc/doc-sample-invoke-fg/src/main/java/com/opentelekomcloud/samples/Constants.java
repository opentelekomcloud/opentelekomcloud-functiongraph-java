package com.opentelekomcloud.samples;

public class Constants {
  private static Constants instance;

  private String userName;
  private String userPassword;
  private String projectId;
  private String accountName;
  private String region;

  private String authUrl;

  private Constants() {
    userName = System.getenv("OTC_USER_NAME");
    userPassword = System.getenv("OTC_USER_PASSWORD");
    projectId = System.getenv("OTC_SDK_PROJECTID");
    accountName = System.getenv("OTC_DOMAIN_NAME");

    authUrl = System.getenv("OTC_AUTH_URL"); // e.g. https://iam.eu-de.otc.t-systems.com
    if (authUrl == null || authUrl.equals("")){
      authUrl="https://iam.eu-de.otc.t-systems.com";
    }

    region = System.getenv("OTC_TENANT_NAME");

  }

  public static Constants getInstance() {
    if (instance == null) {
      instance = new Constants();
    }
    return instance;
  }

  public String getRegion() {
    return region;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  /**
   * Specifies a subproject ID.
   * This parameter is mandatory only in multi-project scenarios.
   * @return
   */
  public String getProjectId() {
    return projectId;
  }

  /**
   * 
   * @return
   */
  public String getAccountName() {
    return accountName;
  }

  public String getTokenUri() {
    return String.format("%s/%s", authUrl, "v3/auth/tokens?nocatalog=true");
  }

  public String getFunctionGraphHost() {
    return String.format("functiongraph.%s.otc.t-systems.com", region);
  }

  public String getFunctionUrn(String functionName) {
    return String.format("urn:fss:%s:%s:function:default:%s:latest", //
        region, //
        projectId, //
        functionName);
  }

  public String getInvokeUri(String functionName) {
    return String.format("https://%s/v2/%s/fgs/functions/%s/invocations",
        Constants.getInstance().getFunctionGraphHost(), //
        Constants.getInstance().getProjectId(), //
        Constants.getInstance().getFunctionUrn(functionName));
  }

}

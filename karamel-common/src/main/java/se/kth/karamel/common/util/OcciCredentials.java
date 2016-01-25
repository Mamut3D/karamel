package se.kth.karamel.common.util;

/**
 * Occi Credentials
 * Created by Mamut on 2016-01-18.
 */
public class OcciCredentials {
  private String userCertificatePath;
  private String systemCertDir;
  private String endpoint;

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public String getUserCertificatePath() {
    return userCertificatePath;
  }

  public void setUserCertificatePath(String accountCertificate) {
    this.userCertificatePath = accountCertificate;
  }

  public String getSystemCertDir() {
    return systemCertDir;
  }

  public void setSystemCertDir(String systemCertDir) {
    this.systemCertDir = systemCertDir;
  }
}

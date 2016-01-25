package se.kth.karamel.common.clusterdef;

import se.kth.karamel.common.exception.ValidationException;
import se.kth.karamel.common.util.settings.OcciSetting;

/**
 * Created by Mamut3D on 2016-1-18.
 */
public class Occi extends Provider {

  //username from provider is not used
  private String image;
  private String userCertificatePath;
  //private String endpoint;

  public static Occi makeDefault() {
    Occi occi = new Occi();
    return occi.applyDefaults();
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
  
  /*public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }*/
  
  public String getUserCertificatePath() {
    return userCertificatePath;
  }

  public void setUserCertificatePath(String userCertificatePath) {
    this.userCertificatePath = userCertificatePath;
  }
  
  @Override
  public Occi cloneMe() {
    Occi occi = new Occi();
    occi.setUsername(getUsername());
    occi.setImage(image);
    occi.setUserCertificatePath(userCertificatePath);
    //occi.setEndpoint(endpoint);
    return occi;
  }

  @Override
  public Occi applyParentScope(Provider parentScopeProvider) {
    Occi clone = cloneMe();
    if (parentScopeProvider instanceof Occi) {
      Occi parentOcci = (Occi) parentScopeProvider;
      if (clone.getUsername() == null) {
        clone.setUsername(parentOcci.getUsername());
      }
      if (clone.getImage() == null) {
        clone.setImage(parentOcci.getImage());
      }
      if (clone.getUserCertificatePath() == null) {
        clone.setUserCertificatePath(parentOcci.getUserCertificatePath());
      }
    }
    return clone;
  }

  @Override
  public Occi applyDefaults() {
    Occi clone = cloneMe();
    //TODO add default settings for occi
    if (clone.getUsername() == null) {
      clone.setUsername(OcciSetting.OCCI_DEFAULT_USERNAME.getParameter());
    }
    if (clone.getImage() == null) {
      clone.setImage(OcciSetting.OCCI_DEFAULT_IMAGE.getParameter());
    }
    
      /*OCCI_DEFAULT_USERNAME("default username here!"),
  OCCI_DEFAULT_CERTIFICATE_DIR("default flavor here!"),
  OCCI_DEFAULT_AUTH_CERT("default region here!"),
  OCCI_DEFAULT_IMAGE("default image here!"),*/
    return clone;
  }

  @Override
  public void validate() throws ValidationException {
    //TODO validation exception to think of
  }
}

package  com.lotus.uccp.vo;

public class ClientdetailsVO   {

	private String appid;
	private String resourceids;
	private String appsecret;
	private String scope;
	private String granttypes;
	private String redirecturl;
	private String authorities;
	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;
	private String additionalinformation;
	private String autoapprovescopes;

	public ClientdetailsVO() {
	}

	public ClientdetailsVO(String appid) {
		this.appid = appid;
	}

	public ClientdetailsVO(String appid, String resourceids, String appsecret, String scope, String granttypes,
			String redirecturl, String authorities, Integer accessTokenValidity, Integer refreshTokenValidity,
			String additionalinformation, String autoapprovescopes) {
		this.appid = appid;
		this.resourceids = resourceids;
		this.appsecret = appsecret;
		this.scope = scope;
		this.granttypes = granttypes;
		this.redirecturl = redirecturl;
		this.authorities = authorities;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.additionalinformation = additionalinformation;
		this.autoapprovescopes = autoapprovescopes;
	}

	public String getAppid() {
		return this.appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getResourceids() {
		return this.resourceids;
	}

	public void setResourceids(String resourceids) {
		this.resourceids = resourceids;
	}

	public String getAppsecret() {
		return this.appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getGranttypes() {
		return this.granttypes;
	}

	public void setGranttypes(String granttypes) {
		this.granttypes = granttypes;
	}

	public String getRedirecturl() {
		return this.redirecturl;
	}

	public void setRedirecturl(String redirecturl) {
		this.redirecturl = redirecturl;
	}

	public String getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public Integer getAccessTokenValidity() {
		return this.accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return this.refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalinformation() {
		return this.additionalinformation;
	}

	public void setAdditionalinformation(String additionalinformation) {
		this.additionalinformation = additionalinformation;
	}

	public String getAutoapprovescopes() {
		return this.autoapprovescopes;
	}

	public void setAutoapprovescopes(String autoapprovescopes) {
		this.autoapprovescopes = autoapprovescopes;
	}

}

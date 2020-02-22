package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;
import com.venada.efinance.enumtype.UserStatus;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * 用户pojo，存放用户的关键信息
 * 
 * @author zhangwy
 * 
 */
@SuppressWarnings("deprecation")
public class User extends BaseEntity implements UserDetails,Serializable {
	private static final long serialVersionUID = 2960084143364510373L;
	private int index;
	private String mobileNumber;
	private String name;
	private String nickName;
	private String password;
	private UserStatus status;
	private int securityStatus;
	private Integer level;
	/**
	 * @author zhangwy
	 * 积分
	 */
	private BigDecimal balance;
	private Integer credits;
	private int wealth;
	private int income;
	private int loginCount;
	private byte[] photo;
	private String role;
	private Integer gender;
	private String email;
	private String idCard;
	private List<Role> userRoles;
	private Integer isMarried;
	private String inviteCodeFromOther;
	private String inviteCodeSelf;
	private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	private String roleSelid;
	
	private Date birthday;
	private String graduated;
	private int liveProvince;
	private int liveCity;
	private int liveArea;
	private Integer eduLevel;
	private String address;
	private String province;
	private String city;
	private String area;
	private String vipTag;
	private BigDecimal sumBenfit;
	private Integer num;
	private Integer type;
	private String fatherid;
	private Double svipRate;
	private Double myRate;//分成
	private String vipInitPassword;//svip初试密码
	private BigDecimal totalWithdrawal;//SVIP累计提现
	private BigDecimal totalWithdrawalFee;//SVIP累计扣除的手续费
	//新加qq号码
	private String qq;
	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return this.status.getIndex();
	}

	public void setStatus(int status) {
		this.status = UserStatus.getEnum(status);
	}

	public int getSecurityStatus() {
		return securityStatus;
	}

	public void setSecurityStatus(int securityStatus) {
		this.securityStatus = securityStatus;
	}

	public int getWealth() {
		return wealth;
	}

	public void setWealth(int wealth) {
		this.wealth = wealth;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		for (String r : StringUtils.split(this.role, ",")) {
//			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(
//					r);
//			authsList.add(simpleGrantedAuthority);
//		}
//		return authsList;
//	}
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		for (Role role : this.getUserRoles()) {
			authorities.add(new GrantedAuthorityImpl(role.getRoleName().toString()));
		}
		return authorities;
	}
	
	@Override
	public String getUsername() {
		return this.mobileNumber;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getRole() {
		return role;
	}

	@Override
	public int hashCode() {
		return mobileNumber.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User u = (User) obj;
		return u.mobileNumber.equals(mobileNumber);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getIsMarried() {
		return isMarried;
	}

	public void setIsMarried(Integer isMarried) {
		this.isMarried = isMarried;
	}

	public List<Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<Role> userRoles) {
		this.userRoles = userRoles;
	}

	public String getRoleSelid() {
		return roleSelid;
	}

	public void setRoleSelid(String roleSelid) {
		this.roleSelid = roleSelid;
	}

	public String getInviteCodeFromOther() {
		return inviteCodeFromOther;
	}

	public void setInviteCodeFromOther(String inviteCodeFromOther) {
		this.inviteCodeFromOther = inviteCodeFromOther;
	}

	public String getInviteCodeSelf() {
		return inviteCodeSelf;
	}

	public void setInviteCodeSelf(String inviteCodeSelf) {
		this.inviteCodeSelf = inviteCodeSelf;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGraduated() {
		return graduated;
	}

	public void setGraduated(String graduated) {
		this.graduated = graduated;
	}

	public int getLiveProvince() {
		return liveProvince;
	}

	public void setLiveProvince(int liveProvince) {
		this.liveProvince = liveProvince;
	}

	public int getLiveCity() {
		return liveCity;
	}

	public void setLiveCity(int liveCity) {
		this.liveCity = liveCity;
	}

	public int getLiveArea() {
		return liveArea;
	}

	public void setLiveArea(int liveArea) {
		this.liveArea = liveArea;
	}

	public Integer getEduLevel() {
		return eduLevel;
	}

	public void setEduLevel(Integer eduLevel) {
		this.eduLevel = eduLevel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	

	public String getVipTag() {
		return vipTag;
	}

	public void setVipTag(String vipTag) {
		this.vipTag = vipTag;
	}


	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public BigDecimal getSumBenfit() {
		return sumBenfit;
	}

	public void setSumBenfit(BigDecimal sumBenfit) {
		this.sumBenfit = sumBenfit;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getFatherid() {
		return fatherid;
	}

	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}

	public Double getSvipRate() {
		return svipRate;
	}

	public void setSvipRate(Double svipRate) {
		this.svipRate = svipRate;
	}

	public String getVipInitPassword() {
		return vipInitPassword;
	}

	public void setVipInitPassword(String vipInitPassword) {
		this.vipInitPassword = vipInitPassword;
	}

	@Override
	public String toString() {
		return "User [index=" + index + ", mobileNumber=" + mobileNumber
				+ ", name=" + name + ", nickName=" + nickName + ", password="
				+ password + ", status=" + status + ", securityStatus="
				+ securityStatus + ", level=" + level + ", balance=" + balance
				+ ", credits=" + credits + ", wealth=" + wealth + ", income="
				+ income + ", loginCount=" + loginCount + ", photo="
				+ Arrays.toString(photo) + ", role=" + role + ", gender="
				+ gender + ", email=" + email + ", idCard=" + idCard
				+ ", userRoles=" + userRoles + ", isMarried=" + isMarried
				+ ", inviteCodeFromOther=" + inviteCodeFromOther
				+ ", inviteCodeSelf=" + inviteCodeSelf + ", authorities="
				+ authorities + ", roleSelid=" + roleSelid + ", birthday="
				+ birthday + ", graduated=" + graduated + ", liveProvince="
				+ liveProvince + ", liveCity=" + liveCity + ", liveArea="
				+ liveArea + ", eduLevel=" + eduLevel + ", address=" + address
				+ ", province=" + province + ", city=" + city + ", area="
				+ area + ", vipTag=" + vipTag + ", sumBenfit=" + sumBenfit
				+ ", num=" + num + ", type=" + type + ", fatherid=" + fatherid
				+ ", svipRate=" + svipRate + ", vipInitPassword="
				+ vipInitPassword + ", qq=" + qq + "]";
	}

	public Double getMyRate() {
		return myRate;
	}

	public void setMyRate(Double myRate) {
		this.myRate = myRate;
	}

	public BigDecimal getTotalWithdrawal() {
		return totalWithdrawal;
	}

	public void setTotalWithdrawal(BigDecimal totalWithdrawal) {
		this.totalWithdrawal = totalWithdrawal;
	}

	public BigDecimal getTotalWithdrawalFee() {
		return totalWithdrawalFee;
	}

	public void setTotalWithdrawalFee(BigDecimal totalWithdrawalFee) {
		this.totalWithdrawalFee = totalWithdrawalFee;
	}
	
}
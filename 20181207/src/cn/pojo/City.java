package cn.pojo;
/**
 * 
 * 类描述：城市实体类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月10日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class City {

	// 城市id
	private int cityId;
	
	// 城市名称
	private String cityName;

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * 
	 */
	public City() {
		
	}
}

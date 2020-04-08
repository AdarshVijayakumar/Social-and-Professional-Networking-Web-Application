
public class UserProfile implements java.io.Serializable{
	 String username;
	 String firstname;
	 String lastname;
	 String education;
	 String summary;
	 String companyname;
	 String experiencePeriod;
	 String skills;
	 String certifications;
	 String projects;

	public UserProfile(String username,String firstname,String lastname,String education, String summary,String companyname,String experiencePeriod,
		String skills,String certifications, String projects){
		this.username=username;
		this.firstname=firstname;
		this.lastname=lastname;
		this.education= education;
		this.summary = summary;
		this.companyname = companyname;
		this.experiencePeriod = experiencePeriod;
		this.skills = skills;
		this.certifications = certifications;
		this.projects = projects;
	}
	
	
	public UserProfile() {	
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getfirstname() {
		return firstname;
	}

	public void setfirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getlastname() {
		return lastname;
	}

	public void setlastname(String lastname) {
		this.lastname = lastname;
	}

	public String geteducation() {
		return education;
	}

	public void seteducation(String education) {
		this.education = education;
	}
	
	public String getsummary() {
		return summary;
	}

	public void setsummary(String summary) {
		this.summary = summary;
	}

	public String getcompanyname() {
		return companyname;
	}

	public void setcompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getexperiencePeriod() {
		return experiencePeriod;
	}

	public void setexperiencePeriod(String experiencePeriod) {
		this.experiencePeriod = experiencePeriod;
	}

	public String getskills() {
		return skills;
	}

	public void setskills(String skills) {
		this.skills = skills;
	}

	public String getcertifications() {
		return certifications;
	}

	public void setcertifications(String certifications) {
		this.certifications = certifications;
	}

	public String getprojects() {
		return projects;
	}

	public void setprojects(String projects) {
		this.projects = projects;
	}
}

      
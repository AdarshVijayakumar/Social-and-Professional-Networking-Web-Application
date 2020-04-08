import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class AddJob
  implements Serializable
{
  String companyname;
  String jobid;
  int id;
  String salary;
  String category;
  String username;
  String jobtype;
  String jobposition;
  String state;
  
  public AddJob(String companyname, String username, String salary, String category, String jobid, String jobtype, String  jobposition, String state)
  {
    this.companyname = companyname;
    this.username = username;
    this.salary = salary;
    this.category = category;
    this.jobid = jobid;
    this.jobtype = jobtype;
    this.jobposition = jobposition;
    this.state = state;
  }
  
  void setJobId(String jobid)
  {
    this.jobid = this.jobid;
  }
  
  public String getJobId() {
    return jobid;
  }
  
  void setId(int Id) {
    this.id = this.id;
  }
  
  public int getId() {
    return id;
  }
  
  void setCompanyName(String companyname)
  {
    this.companyname = companyname;
  }
  
  public String getCompanyName()
  {
    return companyname;
  }
  
  void setCategory(String category) {
    this.category = category;
  }
  
  public String getCategory() {
    return category;
  }
  
  void setUsername(String username) {
    this.username = username;
  }
  
  public String getUsername() {
    return username;
  }
  
  void setSalary(String salary) 
  {
	  this.salary = salary; 
  }
  
  public String getSalary()
  {
    return salary;
  }
  
  void setJobType(String jobtype) {
    this.jobtype = jobtype;
  }
  
  public String getJobType() {
    return jobtype;
  }
  
  void setJobPosition(String jobposition)
  {
    this.jobposition = jobposition;
  }
  
  public String getJobPosition() {
    return jobposition;
  }
  
  void setState(String state)
  {
    this.state = state;
  }
  
  public String getState() {
    return state;
  }
}

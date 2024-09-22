package pojo;

public class GetCoursesParent {
	private String instructor;
	private String services;	
	private String expertise;
	private Courses courses; // Here return type will be Courses(pojo class) instead of String bcoz This courses object contain 3 mini/nested json (webautomation, api, mobile) and its related to class course. so by doing this mini json code(Courses class code) will be injecting in courses variable
	private String linkedIn;
	private String url;
	//to generate getters n setter by eclipse ALT+SHIFT+S
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public Courses getCourses() {
		return courses;
	}
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
	




}

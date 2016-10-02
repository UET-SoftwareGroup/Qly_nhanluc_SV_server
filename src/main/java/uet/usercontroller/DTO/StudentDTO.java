package uet.usercontroller.DTO;

/**
 * Created by Trung on 7/11/2016.
 */
public class StudentDTO {
    private InfoBySchoolDTO infoBySchoolDTO1;
    private JobSkillDTO jobSkillDTO;
    private InfoBySchoolDTO infoBySchoolDTO;
    private int id;
    private int userId;
    private String studentName;

    public InfoBySchoolDTO getInfoBySchoolDTO1() {
        return infoBySchoolDTO1;
    }

    public void setInfoBySchoolDTO1(InfoBySchoolDTO infoBySchoolDTO1) {
        this.infoBySchoolDTO1 = infoBySchoolDTO1;
    }

    public JobSkillDTO getJobSkillDTO() {
        return jobSkillDTO;
    }

    public void setJobSkillDTO(JobSkillDTO jobSkillDTO) {
        this.jobSkillDTO = jobSkillDTO;
    }

    public InfoBySchoolDTO getInfoBySchoolDTO() {
        return infoBySchoolDTO;
    }

    public void setInfoBySchoolDTO(InfoBySchoolDTO infoBySchoolDTO) {
        this.infoBySchoolDTO = infoBySchoolDTO;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) {this.userId = userId; }

    public String getStudentName() { return studentName; }

    public void setStudentName(String studentName) { this.studentName = studentName; }
}

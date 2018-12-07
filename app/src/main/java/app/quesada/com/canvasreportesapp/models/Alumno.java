package app.quesada.com.canvasreportesapp.models;

public class Alumno {
    private String course_id;
    private String name;
    private String integration_id;

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntegration_id() {
        return integration_id;
    }

    public void setIntegration_id(String integration_id) {
        this.integration_id = integration_id;
    }

    //@Override
    /*
    public String toString() {
    return "Alumno{" +
                "course_id='" + course_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
     */

    @Override
    public String toString() {
        return "Alumno{" +
                "course_id='" + course_id + '\'' +
                ", name='" + name + '\'' +
                ", integration_id='" + integration_id + '\'' +
                '}';
    }
}

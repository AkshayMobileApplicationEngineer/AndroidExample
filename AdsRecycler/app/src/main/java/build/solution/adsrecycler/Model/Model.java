package build.solution.adsrecycler.Model;

public class Model {
    private String header;
    private String description;
    private int imageName;

    public Model(String header, String description, int imageName) {
        this.header = header;
        this.description = description;
        this.imageName = imageName;
    }

    public Model() {

    }

    public String getHeader(String cProgrammingLanguage) {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription(String desktopProgramingLanguage) {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageName(int u) {
        return imageName;
    }

    public void setImageName(int imageName) {
        this.imageName = imageName;
    }
}

package bluepanther.jiljungjuk.recycler;


public class DataModel {


    String title;
    String category;
    String uploader;
    int image;

    public DataModel(String title, String category, String uploader, int image) {
        this.title = title;
        this.category= category;
        this.uploader = uploader;
        this.image=image;
    }


    public String getName() {
        return title;
    }


    public String getVersion() {
        return category;
    }

    public int getImage() {
        return image;
    }

    public String getId() {
        return uploader;
    }
}
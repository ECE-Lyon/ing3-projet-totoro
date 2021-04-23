public class Movie {
    private String title;
    private enum enumGenre {ACTION, AVENTURE, COMEDIE, FAMILLE, DRAME, CATASTROPHE, POLICIER, DOCUMENTAIRE, FANTASTIQUE, HISTORIQUE, HORREUR, COMEDIE_MUSICALE, SCIENCE_FICTION, THRILLER, WESTERN}
    private enumGenre[] genre;
    private String date;
    private Integer time;

    public Movie(String title, String date, Integer time, enumGenre[] genre) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}

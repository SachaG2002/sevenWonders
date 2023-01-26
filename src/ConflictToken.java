public class ConflictToken {
    private String visibleFace;

    public ConflictToken() {
        this.visibleFace = "Paix";
    }

    public String getVisibleFace() {
        return this.visibleFace;
    }

    public void turnToken(String visibleFace) {
        this.visibleFace = visibleFace;
    }
}

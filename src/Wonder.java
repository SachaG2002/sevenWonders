import java.util.ArrayList;

public class Wonder {
    private String name;
    private ArrayList<WonderStage> stagesList = new ArrayList<>();

    public Wonder(String name) {
        this.name = name;
        for (int i = 0; i < 5 ; i++) {

            if (i == 0 || i == 1) {
                this.stagesList.add(new WonderStage(2, true));
            }
            if (i == 2 || i == 3) {
                this.stagesList.add(new WonderStage(3, true));
            }
            else {
                this.stagesList.add(new WonderStage(4, true));
            }

        }
        if (this.name.equals("Gizeh")) {
            for (WonderStage stage : this.stagesList) {
                stage.setVictoryPoints(stage.getVictoryPoints()*2);
            }
        }
    }

    public ArrayList<WonderStage> getStagesList() {
        return this.stagesList;
    }

    public boolean isFinished() {
        boolean result = false;
        int finished = 0;
        for (WonderStage stage : this.stagesList) {
            if (stage.isConstructed()) {
                finished += 1;
            }
        }
        if (finished == 5) {
            result = true;
        }
        return result;
    }
}

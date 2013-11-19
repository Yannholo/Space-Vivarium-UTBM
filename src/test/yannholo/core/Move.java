package test.yannholo.core;

public class Move implements Action {
    
    private Bestiole bestiole;
    private Case destination;

    public Move(Bestiole bestiole, Case destination) {
        this.bestiole = bestiole;
        this.destination = destination;
    }

    @Override
    public void doit() {
        bestiole.getLaCase().setBestiole(null);
        bestiole.setLaCase(destination);
        destination.setBestiole(bestiole);
    }

}

package UI;

import Team_Member.Crew;

import java.util.ArrayList;

public class Squad {
    private ArrayList<Crew> mySquad;

    public void addCrew(Crew solider) {
        mySquad.add(mySquad.size(), solider);
    }

    public void removeCrew(Crew soldier) {
        mySquad.remove(soldier);
    }
}

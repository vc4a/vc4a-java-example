package biz.vc4africa.java;

/**
 * @author ebot tabi <ebot.tabi@gmail.com>
 */
public class Ventures implements Constants {

    private Rest r;

    public Ventures() {
        r = new Rest();
    }

    public static void main(String[] args) {
        Ventures v = new Ventures();
        v.getVentures(); //Getting a list of ventures normally we will send more parameters to the api server such as offset & limit :)
        //v.getVentureActivity(22); //Getting the activities of a particular venture.
        //v.getVenture(22); //Getting a unique venture
        //v.getVentureTeam(22); //Getting a venture's team member's IDs.
        //v.search("Amazing"); //Searching for a venture with name Amazing.

    }

    public void getVentures() {

        try {
            String response = r.Execute(Methods.GET, VENTURES_URL);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getVentureActivity(int ventureId) {
        String url = String.format(VENTURE_ACTIVITY_URL, ventureId);
        try {
            String response = r.Execute(Methods.GET, url);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getVenture(int ventureId) {
        String url = String.format(VENTURE_URL, ventureId);
        try {
            String response = r.Execute(Methods.GET, url);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getVentureTeam(int ventureId) {
        String url = String.format(VENTURE_TEAM_URL, ventureId);
        try {
            String response = r.Execute(Methods.GET, url);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search(String ventureName) { //normally you can submit more parameter for the search.
        try {
            r.AddParam("name", ventureName); //here are sending a sample of a venture to be search against the db.
            String response = r.Execute(Methods.GET, VENTURE_SEARCH_URL);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

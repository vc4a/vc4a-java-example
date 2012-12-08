package biz.vc4africa.java;

/**
 *  @author ebot tabi <ebot.tabi@gmail.com>
 */
interface Constants {

    String API_URL = "http://api.vc4africa.biz/v1";
    String API_HOST = "api.vc4africa.biz";
    String VENTURE_SEARCH_URL = API_URL+"/venture/search.json";
    String VENTURE_ACTIVITY_URL = API_URL+"/venture/%s/activity.json";
    String VENTURE_TEAM_URL = API_URL+"/venture/%s/team.json";
    String VENTURE_URL = API_URL+"/venture/%s.json";
    String VENTURES_URL = API_URL+"/ventures.json";
    String PARTNER_ID = "";
    String API_KEY = "";
    int HOST_PORT =80;
}

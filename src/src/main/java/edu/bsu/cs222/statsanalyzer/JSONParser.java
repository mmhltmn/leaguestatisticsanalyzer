package edu.bsu.cs222.statsanalyzer;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.match.Match;
import com.robrua.orianna.type.core.matchlist.MatchReference;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.AggregatedStats;
import com.robrua.orianna.type.core.stats.ChampionStats;
import com.robrua.orianna.type.core.stats.PlayerStatsSummary;
import com.robrua.orianna.type.core.stats.PlayerStatsSummaryType;
import com.robrua.orianna.type.core.summoner.Summoner;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JSONParser {
    public static void main(String[] args) {
        RiotAPI.setRegion(Region.NA);
        RiotAPI.setAPIKey("70f18885-23eb-4106-ad64-04554ffc5b4d");
        Summoner ID = RiotAPI.getSummonerByName("Hiloka");
        Map statss = RiotAPI.getRankedStats(ID);
        Champion Fiddlesticks = RiotAPI.getChampionByName("Fiddlesticks");
        ChampionStats FiddlesticksS = (ChampionStats) statss.get(Fiddlesticks);
        AggregatedStats FiddlesticksStats = FiddlesticksS.getStats();
        //System.out.println(FiddlesticksStats.getTotalWins());
        List<MatchReference> MatchList = RiotAPI.getMatchList(ID);
        MatchReference lastMatch = (MatchList.get(0));
        System.out.println(lastMatch.getMatch().getParticipants().get(3).getStats().getDeaths());



    }
}

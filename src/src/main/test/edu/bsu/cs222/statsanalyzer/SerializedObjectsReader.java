package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.type.core.game.Game;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;

public class SerializedObjectsReader{
    @SuppressWarnings("unchecked")
    //This method will always work even though the type cast is unchecked.
    public Map readStatMapFromFile() throws IOException, ClassNotFoundException {
        FileInputStream input = new FileInputStream("src/main/test-resources/mapObject.ser");
        ObjectInputStream ois = new ObjectInputStream(input);
        Map statMap = (Map) ois.readObject();
        ois.close();
        return statMap;
    }

    @SuppressWarnings("unchecked")
    //This method will always work even though the type cast is unchecked.
    public List<Game> readRecentGamesFromFile() throws IOException, ClassNotFoundException {
        FileInputStream input = new FileInputStream("src/main/test-resources/recentGames.ser");
        ObjectInputStream ois = new ObjectInputStream(input);
        List<Game> recentGames = (List<Game>) ois.readObject();
        ois.close();
        return recentGames;
    }
}


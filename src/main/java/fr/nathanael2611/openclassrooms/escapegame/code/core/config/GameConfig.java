package fr.nathanael2611.openclassrooms.escapegame.code.core.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import fr.nathanael2611.openclassrooms.escapegame.code.core.util.AppHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class will manage the game-config !
 */
public class GameConfig
{

    /* Just contains the game dir as File object */
    private File gameDir;
    /* Just contains the config file as File object */
    private File configFile;

    /* Contains all the config values that has been read from the json config file */
    private final HashMap<String, JsonElement> CONFIG_VALUES = new HashMap<>();

    /* The asked codes sizes */
    public static final ConfigProperty CODE_SIZE = new ConfigProperty("code-size", new JsonPrimitive(2));
    /* The number of different trials that the player will have to win */
    public static final ConfigProperty TRIALS = new ConfigProperty("trials", new JsonPrimitive(6));

    /**
     * Constructor
     */
    public GameConfig(File gameDir)
    {
        this.gameDir = gameDir;
        this.configFile = new File(this.gameDir, "config.json");
    }

    /**
     * Reading the config file to the Config System
     */
    public void read() throws IOException {
        /* if the config file is valid, read the config to the HashMap */
        if(isConfigValid())
        {
            CONFIG_VALUES.clear();
            JsonObject object = getConfigAsJsonObject();
            object.entrySet().forEach(entry -> CONFIG_VALUES.put(entry.getKey(), entry.getValue()));
        } else { /* else, just re-create the config-file by default, and try to re-read the config */
            configFile.delete();
            configFile.createNewFile();
            FileWriter writer = new FileWriter(configFile);
            writer.write("{}");
            writer.close();
            this.read();
        }

    }

    /**
     * Returns true if the config file is valid
     */
    private boolean isConfigValid()
    {
        return this.configFile.exists() && getConfigAsJsonObject() != null;
    }

    /**
     * Getting the config file as an JsonObject
     */
    private JsonObject getConfigAsJsonObject()
    {
        return new JsonParser().parse(AppHelper.readFileToString(this.configFile)).getAsJsonObject();
    }

    /**
     * Get a JsonElement from the loaded config, with a ConfigProperty, or returns the default ConfigProperty value
     * if the ConfigProperty was not set in the json config file before loading.
     */
    public JsonElement get(ConfigProperty prop)
    {
        return CONFIG_VALUES.getOrDefault(prop.getKey(), prop.getDefaultValue());
    }

}
